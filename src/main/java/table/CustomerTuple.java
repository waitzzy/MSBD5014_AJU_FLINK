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


public class CustomerTuple {

    // Primary Key
    public long c_custkey;
    public String c_name;
    public String c_address;
    // Foreign Key to N_NATIONKEY
    public long c_nationkey;
    public String c_phone;
    public double c_acctbal;
    public String c_mktsegment;
    public String c_comment;

    public CustomerTuple() {
    }

    public long getC_custkey() {
        return c_custkey;
    }

    public void setC_custkey(long c_custkey) {
        this.c_custkey = c_custkey;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_address() {
        return c_address;
    }

    public void setC_address(String c_address) {
        this.c_address = c_address;
    }

    public long getC_nationkey() {
        return c_nationkey;
    }

    public void setC_nationkey(long c_nationkey) {
        this.c_nationkey = c_nationkey;
    }

    public String getC_phone() {
        return c_phone;
    }

    public void setC_phone(String c_phone) {
        this.c_phone = c_phone;
    }

    public double getC_acctbal() {
        return c_acctbal;
    }

    public void setC_acctbal(double c_acctbal) {
        this.c_acctbal = c_acctbal;
    }

    public String getC_mktsegment() {
        return c_mktsegment;
    }

    public void setC_mktsegment(String c_mktsegment) {
        this.c_mktsegment = c_mktsegment;
    }

    public String getC_comment() {
        return c_comment;
    }

    public void setC_comment(String c_comment) {
        this.c_comment = c_comment;
    }

    @Override
    public String toString() {
        return "CustomerTuple{" +
                "c_custkey=" + c_custkey +
                ", c_name='" + c_name + '\'' +
                ", c_address='" + c_address + '\'' +
                ", c_nationkey=" + c_nationkey +
                ", c_phone='" + c_phone + '\'' +
                ", c_acctbal=" + c_acctbal +
                ", c_mktsegment='" + c_mktsegment + '\'' +
                ", c_comment='" + c_comment + '\'' +
                '}';
    }
}
