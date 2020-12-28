package Q4;
import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import table.*;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import util.TPCHDataSources;
import relations.*;
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.util.Hashtable;


//---- QUERY: TPCH-Q4
//        # Q4 - Order Priority Checking Query
//        select
//        o_orderpriority,
//        count(*) as order_count
//        from
//        orders
//        where
//        o_orderdate >= '1993-07-01'
//        and o_orderdate < '1993-10-01'
//        and exists (
//        select
//        *
//        from
//        lineitem
//        where
//        l_orderkey = o_orderkey
//        and l_commitdate < l_receiptdate
//    )
//            group by
//            o_orderpriority
//            order by
//            o_orderpriority
//            ---- RESULTS
//            '1-URGENT',10594
//            '2-HIGH',10476
//            '3-MEDIUM',10410
//            '4-NOT SPECIFIED',10556
//            '5-LOW',10487
//            ---- TYPES
//            string, bigint

public class query4 {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment sEnv = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<LineitemTuple> lineitem = TPCHDataSources.getLineitemTupleDataStream(sEnv);
        DataStream<OrdersTuple> orders = TPCHDataSources.getOrdersTupleDataStream(sEnv);
//        insert
        DataStream<UpdateAction> q4UpdateSeq1 = orders.map(
                new MapFunction<OrdersTuple, UpdateAction>() {
                    @Override
                    public UpdateAction map(OrdersTuple ordersTuple) throws Exception {
                        UpdateAction orderaction = new UpdateAction("insert",
                                "orders",
                                ordersTuple.o_orderkey,
                                (Object) ordersTuple,
                                ordersTuple.o_orderkey);
//                        orderaction.setOrderpriority(ordersTuple.o_orderpriority);
                        return orderaction;
                    }
                }
        );
        DataStream<UpdateAction> q4UpdateSeq2 = lineitem.map(
                new MapFunction<LineitemTuple, UpdateAction>() {
                    @Override
                    public UpdateAction map(LineitemTuple lineitemTuple) throws Exception {
                        UpdateAction lineitemaction =  new UpdateAction("insert",
                                "lineitem",
                                lineitemTuple.get_primaryKey(),
                                (Object) lineitemTuple,
                                lineitemTuple.l_orderkey);
                        return lineitemaction;
                    }
                }
        );


//        delete
        DataStream<UpdateAction> q4UpdateSeq3 = orders.map(
                new MapFunction<OrdersTuple, UpdateAction>() {
                    @Override
                    public UpdateAction map(OrdersTuple ordersTuple) throws Exception {
                        UpdateAction orderaction = new UpdateAction("delete",
                                "orders",
                                ordersTuple.o_orderkey,
                                (Object) ordersTuple,
                                ordersTuple.o_orderkey);
//                        orderaction.setOrderpriority(ordersTuple.o_orderpriority);
                        return orderaction;
                    }
                }
        );
        DataStream<UpdateAction> q4UpdateSeq4 = lineitem.map(
                new MapFunction<LineitemTuple, UpdateAction>() {
                    @Override
                    public UpdateAction map(LineitemTuple lineitemTuple) throws Exception {
                        UpdateAction lineitemaction =  new UpdateAction("delete",
                                "lineitem",
                                lineitemTuple.get_primaryKey(),
                                (Object) lineitemTuple,
                                lineitemTuple.l_orderkey);
                        return lineitemaction;
                    }
                }
        );
//        DataStream<UpdateAction> q4UpdateSeq3 = q4UpdateSeq2.join(q4UpdateSeq1).where(value -> value.getOrderkey()).equalTo(value -> value.getOrderkey()).window(EventTimeSessionWindows.withGap(Time.seconds(5)))
//                .apply(new JoinFunction<UpdateAction, UpdateAction, UpdateAction>() {
//                    @Override
//                    public UpdateAction join(UpdateAction lineitem, UpdateAction order) {
//                        UpdateAction lineitemaction =  new UpdateAction("insert",
//                                "lineitem",
//                                lineitem.primaryKey,
//                                lineitem.tupleData,
//                                lineitem.orderkey);
//                        lineitemaction.setOrderpriority(order.orderpriority);
//                        return lineitemaction;
//                    }
//                });
//        q4UpdateSeq3.print();
//        q4UpdateSeq3.print();
        DataStream<UpdateAction> q4UpdateSeq_insert = q4UpdateSeq1.union(q4UpdateSeq2);
        DataStream<UpdateAction> q4UpdateSeq_delete = q4UpdateSeq3.union(q4UpdateSeq4);

        DataStream<UpdateAction> q4UpdateSeq_final= q4UpdateSeq_insert.union(q4UpdateSeq_delete);
//        q4UpdateSeq.print();
        KeyedStream<UpdateAction,String> insert_stream = q4UpdateSeq_insert.keyBy(value -> value.getSomeKey());
        KeyedStream<UpdateAction,String> delete_stream = q4UpdateSeq_delete.keyBy(value -> value.getSomeKey());
        q4UpdateSeq_final.keyBy(value -> value.getSomeKey()).process(new Q4AjuAlgo()).print();
//        delete_stream.print();
//        insert_stream.process(new Q4AjuAlgo()).print();
//        delete_stream.process(new Q4AjuAlgo()).print();

//        q4UpdateSeq.keyBy(new KeySelector<UpdateAction, Integer>() {
//            @Override
//            public Integer getKey(UpdateAction value){
//                return value.orderpriority;
//            }
//        }).process(new Q4AjuAlgo()).print();

        sEnv.execute();

    }


    public static class Q4AjuAlgo extends KeyedProcessFunction<String, UpdateAction, Q4SelectResultTuple> {

        private ValueState<Q4Result> resultState;
        private ValueState<RelationsManager> relationState;

        @Override
        public void open(Configuration parameters) throws Exception {

            resultState = getRuntimeContext().getState(
                    new ValueStateDescriptor<Q4Result>("resultState", Q4Result.class));
            relationState = getRuntimeContext().getState(
                    new ValueStateDescriptor<RelationsManager>("relationState", RelationsManager.class));
        }

        @Override
        public void processElement(UpdateAction updateAction,
                                   Context context,
                                   Collector<Q4SelectResultTuple> collector) throws Exception {

            // init the result state
            Q4Result curResult = resultState.value();
            if (curResult == null) {
                curResult = new Q4Result();
            }

            // init relations manager
            RelationsManager curRelationsManager = relationState.value();
            if (curRelationsManager == null) {
                curRelationsManager = new RelationsManager();
                // select Query4
                curRelationsManager.setQueryNum(4);
            }


            // get the relation unit
            RelationUnit curRelationUnit =
                    curRelationsManager.getRelationMapPool().get(updateAction.getRelationName());


            if (updateAction.actionFlag.compareTo("insert") == 0) {
                curRelationUnit.tuplesIndex.put(updateAction.getPrimaryKey(),updateAction.tupleData);
//                System.out.println(curRelationUnit.getRelationName()+curRelationUnit.tuplesIndex.size());
                // insert algo
                if (!curRelationUnit.isLeaf) {
                    // s <- 0
                    curRelationUnit.s_counter.put(updateAction.getPrimaryKey(), 0);

                    // foreach Rc ∈ C(R) do
                    for (int i = 0; i < curRelationUnit.childRelationsNum; i++) {
                        // I(R, Rc ) ← I(R, Rc ) + (πPK(Rc )t → πPK(R),PK(Rc )t)
                        curRelationUnit.indexOfRandRc.get(i).indexRAndRc.put(
                                updateAction.getPrimaryKey(), updateAction.getPrimaryKeyOfRc(i));
                        // if πPK(Rc )t ∈ I(Rc ) then s(t) ← s(t) + 1
                        if(curRelationUnit.childRelations.get(i).tuplesIndex.containsKey(updateAction.getPrimaryKeyOfRc(i))) {
                            int tmp = curRelationUnit.s_counter.get(updateAction.getPrimaryKey());
                            curRelationUnit.s_counter.put(updateAction.getPrimaryKey(), tmp+1);
                        }
                    }

                    // if s(t) = |C(R)| then
                    if(curRelationUnit.s_counter.get(updateAction.getPrimaryKey()) == curRelationUnit.childRelationsNum) {
                        // foreach Rc ∈ C(R) do
                        for (int i = 0; i < curRelationUnit.childRelationsNum; i++) {
                            // tc ← look up I(Rc ) with key πPK(Rc )t
                            Object tc = curRelationUnit.childRelations.get(i).tuplesIndex.get(updateAction.getPrimaryKeyOfRc(i));
                            // foreach assertion key x of R do
                            for(int j = 0; j < curRelationUnit.assertionKeyNum; j++) {
                                // if x ∈ Rc then
                                if(curRelationUnit.childRelationAndAssertionKeyPool.get(i) == j) {
                                    // if πx t = NULL then πx t ← πx tc
                                    if(curRelationUnit.assertionKey.get(j).get(updateAction.getPrimaryKey()) == null) {
                                        curRelationUnit.assertionKey.get(j).put(
                                                updateAction.getPrimaryKey(),
                                                curRelationUnit.assertionKey.get(j).get(updateAction.getPrimaryKeyOfRc(i)));
                                    }
                                    // else if πx t !=  πx tc, or πx t = ⊥
                                    else if ( (curRelationUnit.assertionKey.get(j).get(updateAction.getPrimaryKey()) !=
                                            curRelationUnit.assertionKey.get(j).get(updateAction.getPrimaryKeyOfRc(i)))
                                            || (curRelationUnit.assertionKey.get(j).get(updateAction.getPrimaryKey()).isFALSUM)){
                                        // then πx t ← ⊥
                                        curRelationUnit.assertionKey.get(j).put(updateAction.getPrimaryKey(),
                                                new AssertionKeyValue(false, true, null));
                                    }
                                }
                            }
                        }
                    }

                }
                boolean allAssertionKeysAreNotFalsum = true;
                if(curRelationUnit.assertionKey != null){
                    for (int i = 0; i < curRelationUnit.assertionKey.size(); i++) {
                        for(AssertionKeyValue akv : curRelationUnit.assertionKey.get(i).values()) {
                            if(akv.isFALSUM) {
                                allAssertionKeysAreNotFalsum = false;
                            }
                        }
                    }
                }

                // if R is a leaf or (s(t) = |C(R)| and all assertion keys are not ⊥) then
                if(curRelationUnit.isLeaf ||
                        (curRelationUnit.s_counter.get(updateAction.getPrimaryKey()) == curRelationUnit.childRelationsNum )
                                && allAssertionKeysAreNotFalsum) {
                    // Insert-Update Algo
                    // (L(R)) ← I(L(R)) + (πPK(R)t → t)
                    curRelationUnit.liveTuplesIndex.put(updateAction.getPrimaryKey(), updateAction.tupleData);
                    // if R is the root of T then
                    if(curRelationUnit.isRoot) {
                        // ∆Q ← ∆Q ∪ {join_result }
                        Object t = updateAction.tupleData;
                        LineitemTuple t_L = (LineitemTuple)t;
                        Q4SelectResultTuple tmpResultTuple;
                        if(curResult.result.isEmpty()){
                            tmpResultTuple = new Q4SelectResultTuple();
                            if(t_L.getL_commitdate().compareTo(t_L.getL_receiptdate()) < 0){
                                //get orders
                                Object tc = curRelationUnit.childRelations.get(0).tuplesIndex.get(updateAction.getPrimaryKeyOfRc(0));
                                OrdersTuple t_O =  (OrdersTuple) tc;
                                if(t_O.getO_orderdate().compareTo("1993-07-01") >= 0 && t_O.getO_orderdate().compareTo("1993-10-01") < 0){
                                    tmpResultTuple.o_orderpriority = t_O.getO_orderpriority();
                                    tmpResultTuple.order_count = 1;
                                    if(tmpResultTuple.o_orderpriority != null && tmpResultTuple.o_orderpriority.length() != 0){
                                        tmpResultTuple.resultMap.put(tmpResultTuple.o_orderpriority,tmpResultTuple.order_count);
                                    }
                                    curResult.addTuple(tmpResultTuple);
                                }
                            }
                        }
                        else{
                            tmpResultTuple = new Q4SelectResultTuple();
                            if(t_L.getL_commitdate().compareTo(t_L.getL_receiptdate()) < 0) {
                                //get orders
                                Object tc = curRelationUnit.childRelations.get(0).tuplesIndex.get(updateAction.getPrimaryKeyOfRc(0));
                                OrdersTuple t_O = (OrdersTuple) tc;
                                if (t_O.getO_orderdate().compareTo("1993-07-01") >= 0 && t_O.getO_orderdate().compareTo("1993-10-01") < 0) {
                                    Q4SelectResultTuple lastResultTuple = curResult.result.get(curResult.result.size() - 1);

                                    tmpResultTuple.o_orderpriority = t_O.getO_orderpriority();
                                    if(lastResultTuple.resultMap.containsKey(t_O.getO_orderpriority()))
                                        tmpResultTuple.order_count = lastResultTuple.resultMap.get(t_O.getO_orderpriority()) + 1;
                                    else
                                        tmpResultTuple.order_count = 1;
                                    if(tmpResultTuple.o_orderpriority != null)
                                        tmpResultTuple.resultMap.put(tmpResultTuple.o_orderpriority,tmpResultTuple.order_count);
                                    curResult.addTuple(tmpResultTuple);
                                }
                            }
                        }
                        collector.collect(tmpResultTuple);
                    }
                    else {
//                        System.out.println(curRelationUnit.parentRelation.tuplesIndex.size());
                        for (Object value : curRelationUnit.parentRelation.tuplesIndex.values()) {
                            LineitemTuple t_L = (LineitemTuple)value;
                            long PK4lineitem = t_L.get_primaryKey();
                            if(t_L.getL_orderkey() == updateAction.getPrimaryKey()){
                                int tmp = curRelationUnit.parentRelation.s_counter.get(PK4lineitem);
                                curRelationUnit.parentRelation.s_counter.put(PK4lineitem,tmp+1);
                                if(tmp + 1 == curRelationUnit.parentRelation.getChildRelationsNum()){
                                    curRelationUnit.parentRelation.nonliveTuplesIndex.remove(PK4lineitem);
                                    curRelationUnit.parentRelation.liveTuplesIndex.put(PK4lineitem,value);
                                    Q4SelectResultTuple tmpResultTuple;
                                    if(curResult.result.isEmpty()){
                                        tmpResultTuple = new Q4SelectResultTuple();
                                        if(t_L.getL_commitdate().compareTo(t_L.getL_receiptdate()) < 0){
                                            //get orders
                                            Object tc = curRelationUnit.tuplesIndex.get(t_L.getL_orderkey());
                                            OrdersTuple t_O =  (OrdersTuple)tc;
                                            if(t_O.getO_orderdate().compareTo("1993-07-01") >= 0 && t_O.getO_orderdate().compareTo("1993-10-01") < 0){
                                                tmpResultTuple.o_orderpriority = t_O.getO_orderpriority();
                                                tmpResultTuple.order_count = 1;
                                                if(tmpResultTuple.o_orderpriority != null)
                                                    tmpResultTuple.resultMap.put(tmpResultTuple.o_orderpriority,tmpResultTuple.order_count);
                                                curResult.addTuple(tmpResultTuple);
                                            }
                                        }
                                    }
                                    else{
                                        tmpResultTuple = new Q4SelectResultTuple();
                                        if(t_L.getL_commitdate().compareTo(t_L.getL_receiptdate()) < 0) {
                                            //get orders
                                            Object tc = curRelationUnit.tuplesIndex.get(t_L.getL_orderkey());
                                            OrdersTuple t_O = (OrdersTuple) tc;
                                            if (t_O.getO_orderdate().compareTo("1993-07-01") >= 0 && t_O.getO_orderdate().compareTo("1993-10-01") < 0) {
                                                Q4SelectResultTuple lastResultTuple = curResult.result.get(curResult.result.size() - 1);
                                                tmpResultTuple.o_orderpriority = t_O.getO_orderpriority();
                                                if(lastResultTuple.resultMap.containsKey(t_O.getO_orderpriority()))
                                                    tmpResultTuple.order_count = lastResultTuple.resultMap.get(t_O.getO_orderpriority()) + 1;
                                                else
                                                    tmpResultTuple.order_count = 1;
                                                if(tmpResultTuple.o_orderpriority != null)
                                                    tmpResultTuple.resultMap.put(tmpResultTuple.o_orderpriority,tmpResultTuple.order_count);
                                                curResult.addTuple(tmpResultTuple);
                                            }
                                        }
                                    }
                                    collector.collect(tmpResultTuple);

                                }
                            }
                        }
                    }
                    resultState.update(curResult);
                    relationState.update(curRelationsManager);

                }
                else{
                    //I(N(R))←I(N(R))+(πPK(R)t →t)
                    curRelationUnit.nonliveTuplesIndex.put(updateAction.primaryKey,updateAction.tupleData);
                    resultState.update(curResult);
                    relationState.update(curRelationsManager);
                }

            } else if (updateAction.actionFlag.compareTo("delete") == 0) {
//                System.out.println("delete");
                // delete algo
                curRelationUnit.tuplesIndex.remove(updateAction.getPrimaryKey());

                if(curRelationUnit.liveTuplesIndex.containsKey(updateAction.getPrimaryKey())) {
                    curRelationUnit.liveTuplesIndex.remove(updateAction.getPrimaryKey());

                    if (curRelationUnit.isRoot()) {


                        Object t = updateAction.tupleData;
                        LineitemTuple t_L = (LineitemTuple) t;

                        Q4SelectResultTuple tmpResultTuple = new Q4SelectResultTuple();
                        if (t_L.getL_commitdate().compareTo(t_L.getL_receiptdate()) < 0) {
                            //get orders
                            Object tc = curRelationUnit.childRelations.get(0).tuplesIndex.get(updateAction.getPrimaryKeyOfRc(0));
                            OrdersTuple t_O = (OrdersTuple) tc;
                            if (t_O.getO_orderdate().compareTo("1993-07-01") >= 0 && t_O.getO_orderdate().compareTo("1993-10-01") < 0) {
                                Q4SelectResultTuple lastResultTuple = curResult.result.get(curResult.result.size() - 1);

                                tmpResultTuple.o_orderpriority = t_O.getO_orderpriority();
                                if (lastResultTuple.resultMap.containsKey(t_O.getO_orderpriority()))
                                    tmpResultTuple.order_count = lastResultTuple.resultMap.get(t_O.getO_orderpriority()) - 1;
                                else
                                    tmpResultTuple.order_count = 0;
                                if (tmpResultTuple.o_orderpriority != null)
                                    tmpResultTuple.resultMap.put(tmpResultTuple.o_orderpriority, tmpResultTuple.order_count);
                                curResult.addTuple(tmpResultTuple);
                            }
                        }
                        collector.collect(tmpResultTuple);
                    } else {

                        for (Object value : curRelationUnit.parentRelation.tuplesIndex.values()) {
                            LineitemTuple t_L = (LineitemTuple) value;
                            long PK4lineitem = t_L.get_primaryKey();
                            if (t_L.getL_orderkey() == updateAction.getPrimaryKey()) {
                                int tmp = curRelationUnit.parentRelation.s_counter.get(PK4lineitem);
                                curRelationUnit.parentRelation.s_counter.put(PK4lineitem, tmp - 1);
                                if(tmp - 1 != curRelationUnit.parentRelation.getChildRelationsNum()){
                                    curRelationUnit.parentRelation.liveTuplesIndex.remove(PK4lineitem);
                                    curRelationUnit.parentRelation.nonliveTuplesIndex.put(PK4lineitem,value);

                                    Q4SelectResultTuple tmpResultTuple = new Q4SelectResultTuple();
                                    if(t_L.getL_commitdate().compareTo(t_L.getL_receiptdate()) < 0) {
                                        //get orders
                                        Object tc = curRelationUnit.tuplesIndex.get(t_L.getL_orderkey());
                                        OrdersTuple t_O = (OrdersTuple) tc;
                                        if (t_O.getO_orderdate().compareTo("1993-07-01") >= 0 && t_O.getO_orderdate().compareTo("1993-10-01") < 0) {
                                            Q4SelectResultTuple lastResultTuple = curResult.result.get(curResult.result.size() - 1);
                                            tmpResultTuple.o_orderpriority = t_O.getO_orderpriority();
                                            if(lastResultTuple.resultMap.containsKey(t_O.getO_orderpriority()))
                                                tmpResultTuple.order_count = lastResultTuple.resultMap.get(t_O.getO_orderpriority()) - 1;
                                            else
                                                tmpResultTuple.order_count = 0;
                                            if(tmpResultTuple.o_orderpriority != null)
                                                tmpResultTuple.resultMap.put(tmpResultTuple.o_orderpriority,tmpResultTuple.order_count);
                                            curResult.addTuple(tmpResultTuple);
                                        }
                                    }

                                    collector.collect(tmpResultTuple);
                                }
                            }

                        }
                    }
                }
                else{
                    curRelationUnit.nonliveTuplesIndex.remove(updateAction.getPrimaryKey());
                }
                if(!curRelationUnit.isRoot()){
                    curRelationUnit.getParentRelation().indexOfRandRc.remove(updateAction.getPrimaryKey());
                }
                resultState.update(curResult);
                relationState.update(curRelationsManager);

            }
            else {
                throw new RuntimeException("update flag does not exist!");
            }


        }
    }
}
