package Q4;
import relations.*;
import table.LineitemTuple;
import table.OrdersTuple;

import java.util.Hashtable;

public class UpdateAction {
    // a flag indicating whether it is an insertion or deletion
    public String actionFlag;

    // which relation this update is applied to
    public String relationName;

    // the primary key in case of a deletion
    public Long primaryKey;

    // the attributes of the tuple to be inserted
    public Object tupleData;

    public Integer orderpriority=1234;
    public Long orderkey;


    public UpdateAction() {
    }

    public UpdateAction(String actionFlag, String relationName, Long primaryKey, Object tupleData,Long orderkey) {
        this.actionFlag = actionFlag;
        this.relationName = relationName;
        this.primaryKey = primaryKey;
        this.tupleData = tupleData;
        this.orderkey = orderkey;

    }

//    public void setOrderpriority(S orderpriority) {
//        this.orderpriority = orderpriority;
//    }

    public String getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public Long getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Long primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Object getTupleData() {
        return tupleData;
    }

    public void setTupleData(Object tupleData) {
        this.tupleData = tupleData;
    }

    public Long getPrimaryKeyOfRc(int i){
        //i == 0 means orders
        LineitemTuple lineitem = (LineitemTuple)this.tupleData;
        if(i == 0) {
            return lineitem.getL_orderkey();
        }
        return lineitem.getL_orderkey();
    }

    @Override
    public String toString() {
        return "UpdateAction{" +
                "actionFlag='" + actionFlag + '\'' +
                ", orderkey='" + orderkey + '\'' +
                ", relationName='" + relationName + '\'' +
                ", order_priority='" + orderpriority + '\'' +
                ", tupleData=" + tupleData +
                '}';
    }

    @Override
    public int hashCode() {
//        return super.hashCode();
        return (int) (primaryKey*10);
    }
}
