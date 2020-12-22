package Q4;

import java.util.Hashtable;

public class join_Operator {
    public Hashtable<Long, String> join_result;
    join_Operator(){
        this.join_result = new Hashtable<Long, String>();
    }

    public void put(Long orderkey, String priority){
        join_result.put(orderkey,priority);
    }

    public String get(Long orderkey){
        return join_result.get(orderkey);
    }

}
