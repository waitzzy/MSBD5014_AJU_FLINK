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
 * @ClassName PartTuple
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/6
 */
public class PartTuple {
    // Primary Key
    public long p_partkey;
    public String p_name;
    public String p_mfgr;
    public String p_brand;
    public String p_type;
    public long p_size;
    public String p_container;
    public double p_retailprice;
    public String p_comment;

    public PartTuple() {
    }

    public long getP_partkey() {
        return p_partkey;
    }

    public void setP_partkey(long p_partkey) {
        this.p_partkey = p_partkey;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_mfgr() {
        return p_mfgr;
    }

    public void setP_mfgr(String p_mfgr) {
        this.p_mfgr = p_mfgr;
    }

    public String getP_brand() {
        return p_brand;
    }

    public void setP_brand(String p_brand) {
        this.p_brand = p_brand;
    }

    public String getP_type() {
        return p_type;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }

    public long getP_size() {
        return p_size;
    }

    public void setP_size(long p_size) {
        this.p_size = p_size;
    }

    public String getP_container() {
        return p_container;
    }

    public void setP_container(String p_container) {
        this.p_container = p_container;
    }

    public double getP_retailprice() {
        return p_retailprice;
    }

    public void setP_retailprice(double p_retailprice) {
        this.p_retailprice = p_retailprice;
    }

    public String getP_comment() {
        return p_comment;
    }

    public void setP_comment(String p_comment) {
        this.p_comment = p_comment;
    }

    @Override
    public String toString() {
        return "PartTuple{" +
                "p_partkey=" + p_partkey +
                ", p_name='" + p_name + '\'' +
                ", p_mfgr='" + p_mfgr + '\'' +
                ", p_brand='" + p_brand + '\'' +
                ", p_type='" + p_type + '\'' +
                ", p_size=" + p_size +
                ", p_container='" + p_container + '\'' +
                ", p_retailprice=" + p_retailprice +
                ", p_comment='" + p_comment + '\'' +
                '}';
    }
}
