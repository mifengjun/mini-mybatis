package org.mi6n.minimybatis.mapping;

import org.mi6n.minimybatis.session.Configuration;

import java.util.Map;

/**
 * @author mifengjun@gmail.com
 */
public class MappedStatement {
    private Configuration configuration;
    private String id;
    private SqlCommandType sqlCommandType;

    private BoundSql boundSql;

    public BoundSql getBoundSql() {
        return boundSql;
    }

    public void setBoundSql(BoundSql boundSql) {
        this.boundSql = boundSql;
    }

    public MappedStatement() {
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }


    public static class Builder {
        private MappedStatement mappedStatement = new MappedStatement();
        private BoundSql boundSql;

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, String sql, String parameterType, String resultType, Map<Integer, String> parameterMap) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            boundSql = new BoundSql(sql, parameterType, resultType, parameterMap);
        }

        public MappedStatement build() {
            return mappedStatement;
        }
    }
}
