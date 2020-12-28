package Q4;

import org.apache.flink.table.expressions.In;
import scala.tools.nsc.transform.patmat.Logic;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;;
public class Q4SelectResultTuple {
    public String o_orderpriority;
    public long order_count;

    public Q4SelectResultTuple() {
    }
    public HashMap<String, Long> resultMap = new HashMap<>();

    public long getOrder_count(String orderpriority) {
        return resultMap.get(orderpriority);
    }

    public long putOrder_count(String orderpriority, Long count) {
        return resultMap.put(orderpriority,count);
    }

    @Override
    public String toString() {
        return "Q4SelectResultTuple{" +
                "o_orderpriority='" + o_orderpriority + '\'' +
                ", order_count='" + order_count + '\'' +
                '}';
//        StringBuffer res = new StringBuffer();
//        res.append("Q4 result :");
//        if(resultMap.isEmpty() != false){
//            for( Entry<String, Long> entry: resultMap.entrySet()){
//                res.append( "o_orderpriority=" + entry.getKey() + ", order_count=" + entry.getValue());
//            }
//        }
//        return res.toString();
    }
}
