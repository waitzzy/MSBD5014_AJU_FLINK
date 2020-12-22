package DataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.avro.Schema;
import org.apache.flink.api.common.eventtime.IngestionTimeAssigner;
import org.apache.flink.api.java.tuple.Tuple2;

class LineitemKey {
    private int orderkey;
    private int linenumer;

    LineitemKey(int orderkey, int linenumer){
        this.orderkey = orderkey;
        this.linenumer = linenumer;
    }
    public int get_orderkey(){
        return this.orderkey;
    }

    public int get_linenumer(){
        return this.linenumer;
    }

    public void set_orderkey(int orderkey){
        this.orderkey = orderkey;
    }

    public void set_linenumber(int linenumber){
        this.linenumer = linenumber;
    }
}


public class DB_Q4 {
    Map<LineitemKey,Lineitem> LiveLineitem;
    Map<Integer,Orders> LiveOrders;
    Map<Integer,Orders> NoLiveOrders;


    public DB_Q4(){
        Map<LineitemKey,Lineitem> LiveLineitem = new HashMap<LineitemKey,Lineitem>();
        Map<Integer,Orders> LiveOrders = new HashMap<Integer,Orders>();
        Map<Integer,Orders> NoLiveOrders = new HashMap<Integer,Orders>();
    }


    public void insert_order(Orders new_order){
        Set<LineitemKey> lineitemKeys = LiveLineitem.keySet();
        for(LineitemKey key : lineitemKeys){
            if(key.get_orderkey() == new_order.O_ORDERKEY) {
                new_order.s += 1;
            }
        }
        if(new_order.s >= 1) {
            insert_update_orders(new_order);
        }
        else
           NoLiveOrders.put(new_order.O_ORDERKEY, new_order);
    }


    public void insert_lineitems(Lineitem new_lineitem){
        LineitemKey lineitemkey = new LineitemKey(new_lineitem.L_ORDERKEY,new_lineitem.L_LINENUMBER);
        LiveLineitem.put(lineitemkey,new_lineitem);
        insert_update_lineitem(new_lineitem);
    }


    public void insert_update_orders(Orders new_order){
        LiveOrders.put(new_order.O_ORDERKEY, new_order);

    }


    public void delete_order(Orders delete_order){
        if(NoLiveOrders.containsKey(delete_order.O_ORDERKEY)){
            NoLiveOrders.remove(delete_order.O_ORDERKEY);
        }
    }


    public void delete_lineitem(Lineitem delete_item){
        delete_update_lineitem(delete_item);

    }


    public void delete_update_lineitem(Lineitem delete_item){

        Orders order = LiveOrders.get(delete_item.L_ORDERKEY);
        order.s -= 1;
        if(order.s < 1){
            LiveOrders.remove(delete_item.L_ORDERKEY);
            NoLiveOrders.put(delete_item.L_ORDERKEY,order);
        }
        LineitemKey lineitemkey = new LineitemKey(delete_item.L_ORDERKEY,delete_item.L_LINENUMBER);
        LiveLineitem.remove(lineitemkey);

    }


    public void insert_update_lineitem(Lineitem new_lineitem){
        Set<Integer> oderskeys = LiveOrders.keySet();
        for(Integer key : oderskeys){
            if(key == new_lineitem.L_ORDERKEY){
                Orders order = LiveOrders.get(key);
                order.s += 1;
            }
        }

        oderskeys = NoLiveOrders.keySet();
        for(Integer key : oderskeys){
            if(key == new_lineitem.L_ORDERKEY) {
                Orders order = LiveOrders.get(key);
                order.s += 1;
                LiveOrders.put(key, order);
                NoLiveOrders.remove(key);
            }
        }
    }

    public Tuple2<String, Integer> q4_order(Orders new_order){
        int count = 0;
        String ORDERPRIORITY = new_order.O_ORDERPRIORITY;

        Set<LineitemKey> lineitemKeys = LiveLineitem.keySet();

        for(Orders order : LiveOrders.values()){
            if(order.O_ORDERPRIORITY != ORDERPRIORITY)
                continue;
            if(order.O_ORDERDATE.compareTo("1993-07-01") < 0 || order.O_ORDERDATE.compareTo("1993-10-01") >= 0){
                continue;
            }
            for(LineitemKey key : lineitemKeys){
                if(key.get_orderkey() == order.O_ORDERKEY) {
                    Lineitem l = LiveLineitem.get(key);
                    if(l.L_COMMITDATE.compareTo(l.L_RECEIPTDATE) < 0){
                        count += 1;
                        break;
                    }
                }
            }
        }

        return new Tuple2<String, Integer>(ORDERPRIORITY, count);

    }

    public Tuple2<String,Integer> q4_lineitem(Lineitem new_lineitem){
        Orders new_order = LiveOrders.get(new_lineitem.L_ORDERKEY);
        int count = 0;
        String ORDERPRIORITY = new_order.O_ORDERPRIORITY;

        Set<LineitemKey> lineitemKeys = LiveLineitem.keySet();

        for(Orders order : LiveOrders.values()){
            if(order.O_ORDERPRIORITY != ORDERPRIORITY)
                continue;
            if(order.O_ORDERDATE.compareTo("1993-07-01") < 0 || order.O_ORDERDATE.compareTo("1993-10-01") >= 0){
                continue;
            }
            for(LineitemKey key : lineitemKeys){
                if(key.get_orderkey() == order.O_ORDERKEY) {
                    Lineitem l = LiveLineitem.get(key);
                    if(l.L_COMMITDATE.compareTo(l.L_RECEIPTDATE) < 0){
                        count += 1;
                        break;
                    }
                }
            }
        }

        return new Tuple2<String, Integer>(ORDERPRIORITY, count);

    }

}
