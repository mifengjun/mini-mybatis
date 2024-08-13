package org.mi6n.minimybatis.session;

import org.mi6n.minimybatis.builder.xml.XMLConfigBuilder;
import org.mi6n.minimybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * @author mifengjun@gmail.com
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());

    }

    private SqlSessionFactory build(Configuration parse) {
        return new DefaultSqlSessionFactory(parse);
    }
}
