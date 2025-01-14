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

package org.apache.shardingsphere.sharding.route.strategy;

import com.google.common.collect.Range;
import org.apache.shardingsphere.infra.config.props.ConfigurationProperties;
import org.apache.shardingsphere.sharding.api.sharding.common.DataNodeInfo;
import org.apache.shardingsphere.sharding.route.engine.condition.value.ListShardingConditionValue;
import org.apache.shardingsphere.sharding.route.engine.condition.value.RangeShardingConditionValue;
import org.apache.shardingsphere.sharding.route.engine.condition.value.ShardingConditionValue;
import org.apache.shardingsphere.sharding.route.strategy.fixture.ComplexKeysShardingAlgorithmFixture;
import org.apache.shardingsphere.sharding.route.strategy.fixture.StandardShardingAlgorithmFixture;
import org.apache.shardingsphere.sharding.route.strategy.type.complex.ComplexShardingStrategy;
import org.apache.shardingsphere.sharding.route.strategy.type.none.NoneShardingStrategy;
import org.apache.shardingsphere.sharding.route.strategy.type.standard.StandardShardingStrategy;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class ShardingStrategyTest {
    
    private static final DataNodeInfo DATA_NODE_INFO = new DataNodeInfo("logicTable_", 1);
    
    private final Collection<String> targets = Arrays.asList("1", "2", "3");
    
    @Test
    public void assertDoShardingWithoutShardingColumns() {
        NoneShardingStrategy strategy = new NoneShardingStrategy();
        assertThat(strategy.doSharding(targets, Collections.emptySet(), DATA_NODE_INFO, new ConfigurationProperties(new Properties())), is(targets));
    }
    
    @Test
    public void assertDoShardingForBetweenSingleKey() {
        StandardShardingStrategy strategy = new StandardShardingStrategy("column", new StandardShardingAlgorithmFixture());
        Collection<ShardingConditionValue> shardingConditionValues = Collections.singleton(new RangeShardingConditionValue<>("column", "logicTable", Range.open(1, 3)));
        assertThat(strategy.doSharding(targets, shardingConditionValues, DATA_NODE_INFO, new ConfigurationProperties(new Properties())), is(Collections.singleton("1")));
    }
    
    @Test
    public void assertDoShardingForMultipleKeys() {
        Collection<String> expected = new HashSet<>(3, 1);
        expected.add("1");
        expected.add("2");
        expected.add("3");
        ComplexShardingStrategy strategy = new ComplexShardingStrategy("column1, column2", new ComplexKeysShardingAlgorithmFixture());
        Collection<ShardingConditionValue> shardingConditionValues = Arrays.asList(
                new ListShardingConditionValue<>("column1", "logicTable", Collections.singletonList(1)),
                new RangeShardingConditionValue<>("column2", "logicTable", Range.open(1, 3)));
        assertThat(strategy.doSharding(targets, shardingConditionValues, DATA_NODE_INFO, new ConfigurationProperties(new Properties())), is(expected));
    }
}
