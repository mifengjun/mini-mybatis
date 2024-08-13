package org.mi6n.minimybatis.session.defaults;

import org.mi6n.minimybatis.binding.MapperRegistry;
import org.mi6n.minimybatis.session.Configuration;
import org.mi6n.minimybatis.session.SqlSession;
import org.mi6n.minimybatis.session.SqlSessionFactory;

/**
 * @author mifengjun@gmail.com
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration parse) {
        this.configuration = parse;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
