package org.mi6n.minimybatis.session.defaults;

import org.mi6n.minimybatis.mapping.BoundSql;
import org.mi6n.minimybatis.mapping.Environment;
import org.mi6n.minimybatis.mapping.MappedStatement;
import org.mi6n.minimybatis.session.Configuration;
import org.mi6n.minimybatis.session.SqlSession;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author mifengjun@gmail.com
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("default sql session 代理：" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        try {

            MappedStatement mappedStatement = configuration.getMappedStatement(statement);
            Environment environment = configuration.getEnvironment();

            DataSource dataSource = environment.getDataSource();

            Connection connection = dataSource.getConnection();

            BoundSql boundSql = mappedStatement.getBoundSql();

            PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
            preparedStatement.setLong(1, Long.parseLong(((Object[]) parameter)[0].toString()));

            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> objects = resultSet2Obj(resultSet, Class.forName(boundSql.getResultType()));
            System.out.println(("你被代理了！" + "\n方法：" + statement + "\n入参：" + parameter + "\n待执行SQL：" + boundSql.getSql()));
            return objects.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }
}
