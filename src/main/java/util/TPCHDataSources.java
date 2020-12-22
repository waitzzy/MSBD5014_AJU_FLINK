package util;/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;

import java.io.File;
import java.lang.reflect.Field;

import table.*;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @ClassName util.TPCHDataSources
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/6
 */
public class TPCHDataSources {

    public static final String commonDataFilePath = "/Users/zhaozhongyu/Desktop/MSBD5014/2.18.0_rc2/ref_data/1/";
    public static final String customerFilePath = commonDataFilePath + "customer.tbl";
    public static final String lineitemFilePath = commonDataFilePath + "lineitem.tbl.u1.43";
    public static final String nationFilePath = commonDataFilePath + "nation.tbl";
    public static final String ordersFilePath = commonDataFilePath + "orders.tbl.u1.43";
    public static final String partFilePath = commonDataFilePath + "part.tbl";
    public static final String partsuppFilePath = commonDataFilePath + "partsupp.tbl";
    public static final String regionFilePath = commonDataFilePath + "region.tbl";
    public static final String supplierFilePath = commonDataFilePath + "supplier.tbl";


    /**
     * DataSet Sources
     */
    public static DataSet<CustomerTuple> getCustomerDataSet(ExecutionEnvironment env) {
        DataSet<CustomerTuple> data = env.readCsvFile(customerFilePath)
                .lineDelimiter("|\n")
                .fieldDelimiter("|")
                .pojoType(CustomerTuple.class,
                        "c_custkey", "c_name", "c_address", "c_nationkey", "c_phone",
                        "c_acctbal", "c_mktsegment", "c_comment");
        return data;
    }

    public static DataSet<LineitemTuple> getLineitemDataSet(ExecutionEnvironment env) {
        DataSet<LineitemTuple> data = env.readCsvFile(lineitemFilePath)
                .lineDelimiter("|\n")
                .fieldDelimiter("|")
                .pojoType(LineitemTuple.class,
                        "l_orderkey", "l_partkey", "l_suppkey", "l_linenumber", "l_quantity",
                        "l_extendedprice", "l_discount", "l_tax", "l_returnflag", "l_linestatus", "l_shipdate",
                        "l_commitdate", "l_receiptdate", "l_shipinstruct", "l_shipmode", "l_comment");
        return data;
    }

    public static DataSet<NationTuple> getNationDataSet(ExecutionEnvironment env) {
        DataSet<NationTuple> data = env.readCsvFile(nationFilePath)
                .lineDelimiter("|\n")
                .fieldDelimiter("|")
                .pojoType(NationTuple.class,
                        "n_nationkey", "n_name", "n_regionkey", "n_comment");
        return data;
    }

    public static DataSet<OrdersTuple> getOrdersDataSet(ExecutionEnvironment env) {
        DataSet<OrdersTuple> data = env.readCsvFile(ordersFilePath)
                .lineDelimiter("|\n")
                .fieldDelimiter("|")
                .pojoType(OrdersTuple.class,
                        "o_orderkey", "o_custkey", "o_orderstatus", "o_totalprice", "o_orderdate",
                        "o_orderpriority", "o_clerk", "o_shippriority", "o_comment");
        return data;
    }

    public static DataSet<PartsuppTuple> getPartsuppDataSet(ExecutionEnvironment env) {
        DataSet<PartsuppTuple> data = env.readCsvFile(partsuppFilePath)
                .lineDelimiter("|\n")
                .fieldDelimiter("|")
                .pojoType(PartsuppTuple.class,
                        "ps_partkey", "ps_suppkey", "ps_availqty", "ps_supplycost", "ps_comment");
        return data;
    }

    public static DataSet<PartTuple> getPartDataSet(ExecutionEnvironment env) {
        DataSet<PartTuple> data = env.readCsvFile(partFilePath)
                .lineDelimiter("|\n")
                .fieldDelimiter("|")
                .pojoType(PartTuple.class,
                        "p_partkey", "p_name", "p_mfgr", "p_brand", "p_type",
                        "p_size", "p_container", "p_retailprice", "p_comment");
        return data;
    }

    public static DataSet<RegionTuple> getRegionDataSet(ExecutionEnvironment env) {
        DataSet<RegionTuple> data = env.readCsvFile(regionFilePath)
                .lineDelimiter("|\n")
                .fieldDelimiter("|")
                .pojoType(RegionTuple.class,
                        "r_regionkey", "r_name", "r_comment");
        return data;
    }

    public static DataSet<SupplierTuple> getSupplierDataSet(ExecutionEnvironment env) {
        DataSet<SupplierTuple> data = env.readCsvFile(supplierFilePath)
                .lineDelimiter("|\n")
                .fieldDelimiter("|")
                .pojoType(SupplierTuple.class,
                        "s_suppkey", "s_name", "s_address", "s_nationkey", "s_phone",
                        "s_acctbal", "s_comment");
        return data;
    }


    /**
     * DataStream Sources
     */

//    public static <T> DataStream<T> getTpchDataStream(StreamExecutionEnvironment sEnv, T) {
//
//        DataStream<String> rawData = sEnv.readTextFile(supplierFilePath);
//        DataStream<T> data = rawData.map(new MapFunction<String, T>() {
//            @Override
//            public T map(String line) throws Exception {
//                Class c = T.class;
//                Field[] fields = c.getDeclaredFields();
//                String[] tokens = line.split("\\|");
//                if (tokens.length != fields.length) {
//                    throw new RuntimeException("Invalid line: " + line);
//                }
//                T st = new T();
//                for(int i = 0; i < tokens.length; i++) {
//                    fields[i].setAccessible(true);
//                    if (fields[i].getType() == String.class) {
//                        fields[i].set(st, tokens[i]);
//                    } else if (fields[i].getType() == long.class) {
//                        fields[i].set(st, Long.parseLong(tokens[i]));
//                    } else if (fields[i].getType() == double.class) {
//                        fields[i].set(st, Double.parseDouble(tokens[i]));
//                    } else {
//                        throw new RuntimeException("Invalid type: " + fields[i].getType());
//                    }
//                }
//                return st;
//            }
//        });
//        return data;
//    }
    public static DataStream<CustomerTuple> getCustomerTupleDataStream(StreamExecutionEnvironment sEnv) {

        DataStream<String> rawData = sEnv.readTextFile(customerFilePath);
        DataStream<CustomerTuple> data = rawData.map(new MapFunction<String, CustomerTuple>() {
            @Override
            public CustomerTuple map(String line) throws Exception {
                Class c = CustomerTuple.class;
                Field[] fields = c.getDeclaredFields();
                String[] tokens = line.split("\\|");
                if (tokens.length != fields.length) {
                    throw new RuntimeException("Invalid line: " + line);
                }
                CustomerTuple tmpTuple = new CustomerTuple();
                for (int i = 0; i < tokens.length; i++) {
                    fields[i].setAccessible(true);
                    if (fields[i].getType() == String.class) {
                        fields[i].set(tmpTuple, tokens[i]);
                    } else if (fields[i].getType() == long.class) {
                        fields[i].set(tmpTuple, Long.parseLong(tokens[i]));
                    } else if (fields[i].getType() == double.class) {
                        fields[i].set(tmpTuple, Double.parseDouble(tokens[i]));
                    } else {
                        throw new RuntimeException("Invalid type: " + fields[i].getType());
                    }
                }
                return tmpTuple;
            }
        });
        return data;
    }

    public static DataStream<LineitemTuple> getLineitemTupleDataStream(StreamExecutionEnvironment sEnv) {

        DataStream<String> rawData = sEnv.readTextFile(lineitemFilePath);
        DataStream<LineitemTuple> data = rawData.map(new MapFunction<String, LineitemTuple>() {
            @Override
            public LineitemTuple map(String line) throws Exception {
                Class c = LineitemTuple.class;
                Field[] fields = c.getDeclaredFields();
                String[] tokens = line.split("\\|");
                if (tokens.length != fields.length) {
                    throw new RuntimeException("Invalid line: " + line);
                }
                LineitemTuple tmpTuple = new LineitemTuple();
                for (int i = 0; i < tokens.length; i++) {
                    fields[i].setAccessible(true);
                    if (fields[i].getType() == String.class) {
                        fields[i].set(tmpTuple, tokens[i]);
                    } else if (fields[i].getType() == long.class) {
                        fields[i].set(tmpTuple, Long.parseLong(tokens[i]));
                    } else if (fields[i].getType() == double.class) {
                        fields[i].set(tmpTuple, Double.parseDouble(tokens[i]));
                    } else {
                        throw new RuntimeException("Invalid type: " + fields[i].getType());
                    }
                }
                return tmpTuple;
            }
        });
        return data;
    }

    public static DataStream<NationTuple> getNationTupleDataStream(StreamExecutionEnvironment sEnv) {

        DataStream<String> rawData = sEnv.readTextFile(nationFilePath);
        DataStream<NationTuple> data = rawData.map(new MapFunction<String, NationTuple>() {
            @Override
            public NationTuple map(String line) throws Exception {
                Class c = NationTuple.class;
                Field[] fields = c.getDeclaredFields();
                String[] tokens = line.split("\\|");
                if (tokens.length != fields.length) {
                    throw new RuntimeException("Invalid line: " + line);
                }
                NationTuple tmpTuple = new NationTuple();
                for (int i = 0; i < tokens.length; i++) {
                    fields[i].setAccessible(true);
                    if (fields[i].getType() == String.class) {
                        fields[i].set(tmpTuple, tokens[i]);
                    } else if (fields[i].getType() == long.class) {
                        fields[i].set(tmpTuple, Long.parseLong(tokens[i]));
                    } else if (fields[i].getType() == double.class) {
                        fields[i].set(tmpTuple, Double.parseDouble(tokens[i]));
                    } else {
                        throw new RuntimeException("Invalid type: " + fields[i].getType());
                    }
                }
                return tmpTuple;
            }
        });
        return data;
    }

    public static DataStream<OrdersTuple> getOrdersTupleDataStream(StreamExecutionEnvironment sEnv) {

        DataStream<String> rawData = sEnv.readTextFile(ordersFilePath);
        DataStream<OrdersTuple> data = rawData.map(new MapFunction<String, OrdersTuple>() {
            @Override
            public OrdersTuple map(String line) throws Exception {
                Class c = OrdersTuple.class;
                Field[] fields = c.getDeclaredFields();
                String[] tokens = line.split("\\|");
                if (tokens.length != fields.length) {
                    throw new RuntimeException("Invalid line: " + line);
                }
                OrdersTuple tmpTuple = new OrdersTuple();
                for (int i = 0; i < tokens.length; i++) {
                    fields[i].setAccessible(true);
                    if (fields[i].getType() == String.class) {
                        fields[i].set(tmpTuple, tokens[i]);
                    } else if (fields[i].getType() == long.class) {
                        fields[i].set(tmpTuple, Long.parseLong(tokens[i]));
                    } else if (fields[i].getType() == double.class) {
                        fields[i].set(tmpTuple, Double.parseDouble(tokens[i]));
                    } else {
                        throw new RuntimeException("Invalid type: " + fields[i].getType());
                    }
                }
                return tmpTuple;
            }
        });
        return data;
    }

    public static DataStream<PartsuppTuple> getPartsuppTupleDataStream(StreamExecutionEnvironment sEnv) {

        DataStream<String> rawData = sEnv.readTextFile(partsuppFilePath);
        DataStream<PartsuppTuple> data = rawData.map(new MapFunction<String, PartsuppTuple>() {
            @Override
            public PartsuppTuple map(String line) throws Exception {
                Class c = PartsuppTuple.class;
                Field[] fields = c.getDeclaredFields();
                String[] tokens = line.split("\\|");
                if (tokens.length != fields.length) {
                    throw new RuntimeException("Invalid line: " + line);
                }
                PartsuppTuple tmpTuple = new PartsuppTuple();
                for (int i = 0; i < tokens.length; i++) {
                    fields[i].setAccessible(true);
                    if (fields[i].getType() == String.class) {
                        fields[i].set(tmpTuple, tokens[i]);
                    } else if (fields[i].getType() == long.class) {
                        fields[i].set(tmpTuple, Long.parseLong(tokens[i]));
                    } else if (fields[i].getType() == double.class) {
                        fields[i].set(tmpTuple, Double.parseDouble(tokens[i]));
                    } else {
                        throw new RuntimeException("Invalid type: " + fields[i].getType());
                    }
                }
                return tmpTuple;
            }
        });
        return data;
    }

    public static DataStream<PartTuple> getPartTupleDataStream(StreamExecutionEnvironment sEnv) {

        DataStream<String> rawData = sEnv.readTextFile(partFilePath);
        DataStream<PartTuple> data = rawData.map(new MapFunction<String, PartTuple>() {
            @Override
            public PartTuple map(String line) throws Exception {
                Class c = PartTuple.class;
                Field[] fields = c.getDeclaredFields();
                String[] tokens = line.split("\\|");
                if (tokens.length != fields.length) {
                    throw new RuntimeException("Invalid line: " + line);
                }
                PartTuple tmpTuple = new PartTuple();
                for (int i = 0; i < tokens.length; i++) {
                    fields[i].setAccessible(true);
                    if (fields[i].getType() == String.class) {
                        fields[i].set(tmpTuple, tokens[i]);
                    } else if (fields[i].getType() == long.class) {
                        fields[i].set(tmpTuple, Long.parseLong(tokens[i]));
                    } else if (fields[i].getType() == double.class) {
                        fields[i].set(tmpTuple, Double.parseDouble(tokens[i]));
                    } else {
                        throw new RuntimeException("Invalid type: " + fields[i].getType());
                    }
                }
                return tmpTuple;
            }
        });
        return data;
    }

    public static DataStream<RegionTuple> getRegionTupleDataStream(StreamExecutionEnvironment sEnv) {

        DataStream<String> rawData = sEnv.readTextFile(regionFilePath);
        DataStream<RegionTuple> data = rawData.map(new MapFunction<String, RegionTuple>() {
            @Override
            public RegionTuple map(String line) throws Exception {
                Class c = RegionTuple.class;
                Field[] fields = c.getDeclaredFields();
                String[] tokens = line.split("\\|");
                if (tokens.length != fields.length) {
                    throw new RuntimeException("Invalid line: " + line);
                }
                RegionTuple tmpTuple = new RegionTuple();
                for (int i = 0; i < tokens.length; i++) {
                    fields[i].setAccessible(true);
                    if (fields[i].getType() == String.class) {
                        fields[i].set(tmpTuple, tokens[i]);
                    } else if (fields[i].getType() == long.class) {
                        fields[i].set(tmpTuple, Long.parseLong(tokens[i]));
                    } else if (fields[i].getType() == double.class) {
                        fields[i].set(tmpTuple, Double.parseDouble(tokens[i]));
                    } else {
                        throw new RuntimeException("Invalid type: " + fields[i].getType());
                    }
                }
                return tmpTuple;
            }
        });
        return data;
    }


    public static DataStream<SupplierTuple> getSupplierDataStream(StreamExecutionEnvironment sEnv) {

        DataStream<String> rawData = sEnv.readTextFile(supplierFilePath);
        DataStream<SupplierTuple> data = rawData.map(new MapFunction<String, SupplierTuple>() {
            @Override
            public SupplierTuple map(String line) throws Exception {
                Class c = SupplierTuple.class;
                Field[] fields = c.getDeclaredFields();
                String[] tokens = line.split("\\|");
                if (tokens.length != fields.length) {
                    throw new RuntimeException("Invalid line: " + line);
                }
                SupplierTuple tmpTuple = new SupplierTuple();
                for (int i = 0; i < tokens.length; i++) {
                    fields[i].setAccessible(true);
                    if (fields[i].getType() == String.class) {
                        fields[i].set(tmpTuple, tokens[i]);
                    } else if (fields[i].getType() == long.class) {
                        fields[i].set(tmpTuple, Long.parseLong(tokens[i]));
                    } else if (fields[i].getType() == double.class) {
                        fields[i].set(tmpTuple, Double.parseDouble(tokens[i]));
                    } else {
                        throw new RuntimeException("Invalid type: " + fields[i].getType());
                    }
                }
                return tmpTuple;
            }
        });
        return data;
    }


}
