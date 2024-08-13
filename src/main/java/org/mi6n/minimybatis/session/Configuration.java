package org.mi6n.minimybatis.session;

import org.mi6n.minimybatis.binding.MapperRegistry;
import org.mi6n.minimybatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mifengjun@gmail.com
 */
public class Configuration {

    protected MapperRegistry mapperRegistry = new MapperRegistry(this);
    protected Map<String, MappedStatement> mappedStatements = new HashMap<>();

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
}

