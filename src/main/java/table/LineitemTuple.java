/*
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
package table;

/**
 * @ClassName LineitemTuple
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/6
 */
public class LineitemTuple {
    // Primary Key  &&  Foreign Key to O_ORDERKEY
    public long l_orderkey;
    // Foreign key to P_PARTKEY, first part of the compound Foreign Key to (PS_PARTKEY, PS_SUPPKEY) with L_SUPPKEY
    public long l_partkey;
    // Foreign key to S_SUPPKEY, second part of the compound Foreign Key to (PS_PARTKEY, PS_SUPPKEY) with L_PARTKEY
    public long l_suppkey;
    // Primary Key
    public long l_linenumber;
    public double l_quantity;
    public double l_extendedprice;
    public double l_discount;
    public double l_tax;
    public String l_returnflag;
    public String l_linestatus;
    // DATE
    public String l_shipdate;
    public String l_commitdate;
    public String l_receiptdate;
    public String l_shipinstruct;
    public String l_shipmode;
    public String l_comment;

    public LineitemTuple() {
    }

    public long get_primaryKey() {
//        return ((l_linenumber<<60)+(0x0FFFFFFFFFFFFFFFL&l_orderkey));
        return l_orderkey * 10 + l_linenumber;
    }

    public long getL_orderkey() {
        return l_orderkey;
    }

    public void setL_orderkey(long l_orderkey) {
        this.l_orderkey = l_orderkey;
    }

    public long getL_partkey() {
        return l_partkey;
    }

    public void setL_partkey(long l_partkey) {
        this.l_partkey = l_partkey;
    }

    public long getL_suppkey() {
        return l_suppkey;
    }

    public void setL_suppkey(long l_suppkey) {
        this.l_suppkey = l_suppkey;
    }

    public long getL_linenumber() {
        return l_linenumber;
    }

    public void setL_linenumber(long l_linenumber) {
        this.l_linenumber = l_linenumber;
    }

    public double getL_quantity() {
        return l_quantity;
    }

    public void setL_quantity(double l_quantity) {
        this.l_quantity = l_quantity;
    }

    public double getL_extendedprice() {
        return l_extendedprice;
    }

    public void setL_extendedprice(double l_extendedprice) {
        this.l_extendedprice = l_extendedprice;
    }

    public double getL_discount() {
        return l_discount;
    }

    public void setL_discount(double l_discount) {
        this.l_discount = l_discount;
    }

    public double getL_tax() {
        return l_tax;
    }

    public void setL_tax(double l_tax) {
        this.l_tax = l_tax;
    }

    public String getL_returnflag() {
        return l_returnflag;
    }

    public void setL_returnflag(String l_returnflag) {
        this.l_returnflag = l_returnflag;
    }

    public String getL_linestatus() {
        return l_linestatus;
    }

    public void setL_linestatus(String l_linestatus) {
        this.l_linestatus = l_linestatus;
    }

    public String getL_shipdate() {
        return l_shipdate;
    }

    public void setL_shipdate(String l_shipdate) {
        this.l_shipdate = l_shipdate;
    }

    public String getL_commitdate() {
        return l_commitdate;
    }

    public void setL_commitdate(String l_commitdate) {
        this.l_commitdate = l_commitdate;
    }

    public String getL_receiptdate() {
        return l_receiptdate;
    }

    public void setL_receiptdate(String l_receiptdate) {
        this.l_receiptdate = l_receiptdate;
    }

    public String getL_shipinstruct() {
        return l_shipinstruct;
    }

    public void setL_shipinstruct(String l_shipinstruct) {
        this.l_shipinstruct = l_shipinstruct;
    }

    public String getL_shipmode() {
        return l_shipmode;
    }

    public void setL_shipmode(String l_shipmode) {
        this.l_shipmode = l_shipmode;
    }

    public String getL_comment() {
        return l_comment;
    }

    public void setL_comment(String l_comment) {
        this.l_comment = l_comment;
    }

    @Override
    public String toString() {
        return "LineitemTuple{" +
                "l_orderkey=" + l_orderkey +
                ", l_partkey=" + l_partkey +
                ", l_suppkey=" + l_suppkey +
                ", l_linenumber=" + l_linenumber +
                ", l_quantity=" + l_quantity +
                ", l_extendedprice=" + l_extendedprice +
                ", l_discount=" + l_discount +
                ", l_tax=" + l_tax +
                ", l_returnflag='" + l_returnflag + '\'' +
                ", l_linestatus='" + l_linestatus + '\'' +
                ", l_shipdate='" + l_shipdate + '\'' +
                ", l_commitdate='" + l_commitdate + '\'' +
                ", l_receiptdate='" + l_receiptdate + '\'' +
                ", l_shipinstruct='" + l_shipinstruct + '\'' +
                ", l_shipmode='" + l_shipmode + '\'' +
                ", l_comment='" + l_comment + '\'' +
                '}';
    }
}
