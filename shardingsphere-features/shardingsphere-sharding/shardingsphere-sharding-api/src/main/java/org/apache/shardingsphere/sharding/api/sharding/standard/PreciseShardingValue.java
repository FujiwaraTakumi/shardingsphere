/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.sharding.api.sharding.standard;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.apache.shardingsphere.sharding.api.sharding.ShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.common.DataNodeInfo;

/**
 * Sharding value for precise.
 */
@RequiredArgsConstructor
@Getter
@ToString
public final class PreciseShardingValue<T extends Comparable<?>> implements ShardingValue {
    
    private final String logicTableName;
    
    private final String columnName;
    
    private final DataNodeInfo dataNodeInfo;
    
    private final T value;
}
