package org.mi6n.minimybatis.session.defaults;

import org.mi6n.minimybatis.binding.MapperRegistry;
import org.mi6n.minimybatis.session.SqlSession;

/**
 * @author mifengjun@gmail.com
 */
public class DefaultSqlSession implements SqlSession {

    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("default sql session 代理：" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("default sql session 代理：" + statement + ", 入参：" + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
