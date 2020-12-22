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
 * @ClassName OrdersTuple
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/6
 */
public class OrdersTuple {
    // Primary Key
    public long o_orderkey;
    // Foreign Key to C_CUSTKEY
    public long o_custkey;
    public String o_orderstatus;
    public double o_totalprice;
    // DATE
    public String o_orderdate;
    public String o_orderpriority;
    public String o_clerk;
    public long o_shippriority;
    public String o_comment;

    public OrdersTuple() {
    }

    public long getO_orderkey() {
        return o_orderkey;
    }

    public void setO_orderkey(long o_orderkey) {
        this.o_orderkey = o_orderkey;
    }

    public long getO_custkey() {
        return o_custkey;
    }

    public void setO_custkey(long o_custkey) {
        this.o_custkey = o_custkey;
    }

    public String getO_orderstatus() {
        return o_orderstatus;
    }

    public void setO_orderstatus(String o_orderstatus) {
        this.o_orderstatus = o_orderstatus;
    }

    public double getO_totalprice() {
        return o_totalprice;
    }

    public void setO_totalprice(double o_totalprice) {
        this.o_totalprice = o_totalprice;
    }

    public String getO_orderdate() {
        return o_orderdate;
    }

    public void setO_orderdate(String o_orderdate) {
        this.o_orderdate = o_orderdate;
    }

    public String getO_orderpriority() {
        return o_orderpriority;
    }

    public void setO_orderpriority(String o_orderpriority) {
        this.o_orderpriority = o_orderpriority;
    }

    public String getO_clerk() {
        return o_clerk;
    }

    public void setO_clerk(String o_clerk) {
        this.o_clerk = o_clerk;
    }

    public long getO_shippriority() {
        return o_shippriority;
    }

    public void setO_shippriority(long o_shippriority) {
        this.o_shippriority = o_shippriority;
    }

    public String getO_comment() {
        return o_comment;
    }

    public void setO_comment(String o_comment) {
        this.o_comment = o_comment;
    }

    @Override
    public String toString() {
        return "OrdersTuple{" +
                "o_orderkey=" + o_orderkey +
                ", o_custkey=" + o_custkey +
                ", o_orderstatus='" + o_orderstatus + '\'' +
                ", o_totalprice=" + o_totalprice +
                ", o_orderdate='" + o_orderdate + '\'' +
                ", o_orderpriority='" + o_orderpriority + '\'' +
                ", o_clerk='" + o_clerk + '\'' +
                ", o_shippriority=" + o_shippriority +
                ", o_comment='" + o_comment + '\'' +
                '}';
    }
}

