/*
 * Copyright 2016-2018 Leon Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moilioncircle.examples.huge;

import com.moilioncircle.redis.replicator.RedisReplicator;
import com.moilioncircle.redis.replicator.Replicator;
import com.moilioncircle.redis.replicator.rdb.datatype.KeyValuePair;
import com.moilioncircle.redis.replicator.rdb.datatype.Module;
import com.moilioncircle.redis.replicator.rdb.datatype.ZSetEntry;
import com.moilioncircle.redis.replicator.rdb.iterable.ValueIterableRdbListener;
import com.moilioncircle.redis.replicator.rdb.iterable.ValueIterableRdbVisitor;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Leon Chen
 * @since 2.4.4
 */
public class HugeKVFileExample {

    public static void main(String[] args) throws Exception {
        Replicator r = new RedisReplicator("redis:///path/to/dump.rdb");
        r.setRdbVisitor(new ValueIterableRdbVisitor(r));
        r.addRdbListener(new ValueIterableRdbListener(128) {
            @Override
            public void handleString(KeyValuePair<byte[]> kv, int batch, boolean last) {
                // your business code goes here.
            }

            @Override
            public void handleList(KeyValuePair<List<byte[]>> kv, int batch, boolean last) {
                // your business code goes here.
            }

            @Override
            public void handleSet(KeyValuePair<Set<byte[]>> kv, int batch, boolean last) {
                // your business code goes here.
            }

            @Override
            public void handleMap(KeyValuePair<Map<byte[], byte[]>> kv, int batch, boolean last) {
                // your business code goes here.
            }

            @Override
            public void handleZSetEntry(KeyValuePair<Set<ZSetEntry>> kv, int batch, boolean last) {
                // your business code goes here.
            }

            @Override
            public void handleModule(KeyValuePair<Module> kv, int batch, boolean last) {
                // your business code goes here.
            }
        });
        r.open();
    }
}
