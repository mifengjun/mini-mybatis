package org.mi6n.minimybatis.session.defaults;

import org.mi6n.minimybatis.binding.MapperRegistry;
import org.mi6n.minimybatis.session.SqlSession;
import org.mi6n.minimybatis.session.SqlSessionFactory;

/**
 * @author mifengjun@gmail.com
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
