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
 * @ClassName RegionTuple
 * @Description TODO
 * @Author Chaoqi ZHANG
 * @Date 2020/6/6
 */
public class RegionTuple {
    // Primary Key
    public long r_regionkey;
    public String r_name;
    public String r_comment;

    public RegionTuple() {
    }

    public long getR_regionkey() {
        return r_regionkey;
    }

    public void setR_regionkey(long r_regionkey) {
        this.r_regionkey = r_regionkey;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_comment() {
        return r_comment;
    }

    public void setR_comment(String r_comment) {
        this.r_comment = r_comment;
    }

    @Override
    public String toString() {
        return "RegionTuple{" +
                "r_regionkey=" + r_regionkey +
                ", r_name='" + r_name + '\'' +
                ", r_comment='" + r_comment + '\'' +
                '}';
    }
}
