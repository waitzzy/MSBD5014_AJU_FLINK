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
 * @ClassName NationTuple
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/6
 */
public class NationTuple {
    // Primary Key
    public long n_nationkey;
    public String n_name;
    // Foreign Key to R_REGIONKEY
    public long n_regionkey;
    public String n_comment;

    public NationTuple() {
    }

    public long getN_nationkey() {
        return n_nationkey;
    }

    public void setN_nationkey(long n_nationkey) {
        this.n_nationkey = n_nationkey;
    }

    public String getN_name() {
        return n_name;
    }

    public void setN_name(String n_name) {
        this.n_name = n_name;
    }

    public long getN_regionkey() {
        return n_regionkey;
    }

    public void setN_regionkey(long n_regionkey) {
        this.n_regionkey = n_regionkey;
    }

    public String getN_comment() {
        return n_comment;
    }

    public void setN_comment(String n_comment) {
        this.n_comment = n_comment;
    }

    @Override
    public String toString() {
        return "NationTuple{" +
                "n_nationkey=" + n_nationkey +
                ", n_name='" + n_name + '\'' +
                ", n_regionkey=" + n_regionkey +
                ", n_comment='" + n_comment + '\'' +
                '}';
    }
}
