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
 * @ClassName PartsuppTuple
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/6
 */
public class PartsuppTuple {
    // Primary Key  &&  Foreign Key to P_PARTKEY
    public long ps_partkey;
    // Primary Key  &&  Foreign Key to S_SUPPKEY
    public long ps_suppkey;
    public long ps_availqty;
    public double ps_supplycost;
    public String ps_comment;

    public PartsuppTuple() {
    }

    public long getPs_partkey() {
        return ps_partkey;
    }

    public void setPs_partkey(long ps_partkey) {
        this.ps_partkey = ps_partkey;
    }

    public long getPs_suppkey() {
        return ps_suppkey;
    }

    public void setPs_suppkey(long ps_suppkey) {
        this.ps_suppkey = ps_suppkey;
    }

    public long getPs_availqty() {
        return ps_availqty;
    }

    public void setPs_availqty(long ps_availqty) {
        this.ps_availqty = ps_availqty;
    }

    public double getPs_supplycost() {
        return ps_supplycost;
    }

    public void setPs_supplycost(double ps_supplycost) {
        this.ps_supplycost = ps_supplycost;
    }

    public String getPs_comment() {
        return ps_comment;
    }

    public void setPs_comment(String ps_comment) {
        this.ps_comment = ps_comment;
    }

    @Override
    public String toString() {
        return "PartsuppTuple{" +
                "ps_partkey=" + ps_partkey +
                ", ps_suppkey=" + ps_suppkey +
                ", ps_availqty=" + ps_availqty +
                ", ps_supplycost=" + ps_supplycost +
                ", ps_comment='" + ps_comment + '\'' +
                '}';
    }
}
