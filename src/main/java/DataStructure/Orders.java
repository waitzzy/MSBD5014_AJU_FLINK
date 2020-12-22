package DataStructure;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.core.fs.Path;
import org.apache.flink.formats.avro.AvroInputFormat;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.bridge.java.BatchTableEnvironment;

public class Orders {


    public Integer O_ORDERKEY;
    public Integer O_CUSTKEY;
    public String O_ORDERSTATUS	;
    public Float O_TOTALPRICE;
    public String O_ORDERDATE;
    public String O_ORDERPRIORITY;
    public String O_CLERK;
    public Integer O_SHIPPRIORITY;
    public String O_COMMENT;
    public Integer NumOfAlive;
    public Integer s;  //子表对应的数量

    public Orders() {}

    public Integer get_O_ORDERKEY() {
        return O_ORDERKEY;
    }

    public void set_O_ORDERKEY(Integer o_ORDERKEY) {
        O_ORDERKEY = o_ORDERKEY;
    }

    public Integer get_O_CUSTKEY() {
        return O_CUSTKEY;
    }

    public void set_O_CUSTKEY(Integer o_CUSTKEY) {
        O_CUSTKEY = o_CUSTKEY;
    }

    public String get_O_ORDERSTATUS() {
        return O_ORDERSTATUS;
    }

    public void set_O_ORDERSTATUS(String o_ORDERSTATUS) {
        O_ORDERSTATUS = o_ORDERSTATUS;
    }

    public Float get_O_TOTALPRICE() {
        return O_TOTALPRICE;
    }

    public void set_O_TOTALPRICE(Float o_TOTALPRICE) {
        O_TOTALPRICE = o_TOTALPRICE;
    }

    public String get_O_ORDERDATE() {
        return O_ORDERDATE;
    }

    public void set_O_ORDERDATE(String o_ORDERDATE) {
        O_ORDERDATE = o_ORDERDATE;
    }

    public String get_O_ORDERPRIORITY() {
        return O_ORDERPRIORITY;
    }

    public void set_O_ORDERPRIORITY(String o_ORDERPRIORITY) {
        O_ORDERPRIORITY = o_ORDERPRIORITY;
    }

    public String get_O_CLERK() {
        return O_CLERK;
    }

    public void set_O_CLERK(String o_CLERK) {
        O_CLERK = o_CLERK;
    }

    public Integer get_O_SHIPPRIORITY() {
        return O_SHIPPRIORITY;
    }

    public void set_O_SHIPPRIORITY(Integer o_SHIPPRIORITY) {
        O_SHIPPRIORITY = o_SHIPPRIORITY;
    }

    public String get_O_COMMENT() {
        return O_COMMENT;
    }

    public void set_O_COMMENT(String o_COMMENT) {
        O_COMMENT = o_COMMENT;
    }

//    public static Table get_Table(org.apache.flink.api.java.ExecutionEnvironment env,org.apache.flink.table.api.java.BatchTableEnvironment tEnv,String address)
//    {
//        Path path = new Path(address + "orders.avro");
//        AvroInputFormat<Orders> format = new AvroInputFormat<Orders>(path, Orders.class);
//        final DataSet<Orders> ordersDataSet = env.createInput(format);
//        Table orders = tEnv.fromDataSet(ordersDataSet);
//        return orders;
//    }
}