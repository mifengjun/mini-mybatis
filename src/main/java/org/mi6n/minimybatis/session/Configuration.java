package org.mi6n.minimybatis.session;

import org.mi6n.minimybatis.binding.MapperRegistry;
import org.mi6n.minimybatis.datasource.druid.DruidDataSourceFactory;
import org.mi6n.minimybatis.mapping.Environment;
import org.mi6n.minimybatis.mapping.MappedStatement;
import org.mi6n.minimybatis.transaction.jdbc.JdbcTransactionFactory;
import org.mi6n.minimybatis.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mifengjun@gmail.com
 */
public class Configuration {


    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    // key 为方法名，对应 mapper xml 中的 id 属性
    protected Map<String, MappedStatement> mappedStatements = new HashMap<>();

    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    //环境
    protected Environment environment;

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMapper(packageName);
    }

    public void addMapper(Class<?> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public void addMappedStatement(MappedStatement mappedStatement) {
        mappedStatements.put(mappedStatement.getId(), mappedStatement);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}

