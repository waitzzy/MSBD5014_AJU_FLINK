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

import java.io.Serializable;

/**
 * @ClassName SupplierTuple
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/6
 */
public class SupplierTuple {
    // Primary Key
    public long s_suppkey;
    public String s_name;
    public String s_address;
    // Foreign Key to N_NATIONKEY
    public long s_nationkey;
    public String s_phone;
    public double s_acctbal;
    public String s_comment;

    public SupplierTuple() {
    }

    public long getS_suppkey() {
        return s_suppkey;
    }

    public void setS_suppkey(long s_suppkey) {
        this.s_suppkey = s_suppkey;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_address() {
        return s_address;
    }

    public void setS_address(String s_address) {
        this.s_address = s_address;
    }

    public long getS_nationkey() {
        return s_nationkey;
    }

    public void setS_nationkey(long s_nationkey) {
        this.s_nationkey = s_nationkey;
    }

    public String getS_phone() {
        return s_phone;
    }

    public void setS_phone(String s_phone) {
        this.s_phone = s_phone;
    }

    public double getS_acctbal() {
        return s_acctbal;
    }

    public void setS_acctbal(double s_acctbal) {
        this.s_acctbal = s_acctbal;
    }

    public String getS_comment() {
        return s_comment;
    }

    public void setS_comment(String s_comment) {
        this.s_comment = s_comment;
    }

    @Override
    public String toString() {
        return "SupplierTuple{" +
                "s_suppkey=" + s_suppkey +
                ", s_name='" + s_name + '\'' +
                ", s_address='" + s_address + '\'' +
                ", s_nationkey=" + s_nationkey +
                ", s_phone='" + s_phone + '\'' +
                ", s_acctbal=" + s_acctbal +
                ", s_comment='" + s_comment + '\'' +
                '}';
    }
}
