/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingjdbc.core.parsing.parser.sql.dal.use;

import io.shardingjdbc.core.constant.DatabaseType;
import io.shardingjdbc.core.parsing.lexer.LexerEngine;
import io.shardingjdbc.core.parsing.parser.dialect.mysql.sql.MySQLUseParser;
import io.shardingjdbc.core.rule.ShardingRule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Use parser factory.
 *
 * @author zhangliang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UseParserFactory {
    
    /**
     * Create use parser instance.
     *
     * @param dbType database type
     * @param shardingRule databases and tables sharding rule
     * @param lexerEngine lexical analysis engine.
     * @return use parser instance
     */
    public static AbstractUseParser newInstance(final DatabaseType dbType, final ShardingRule shardingRule, final LexerEngine lexerEngine) {
        switch (dbType) {
            case H2:
            case MySQL:
                return new MySQLUseParser();
            default:
                throw new UnsupportedOperationException(String.format("Cannot support database [%s].", dbType));
        }
    }
}
