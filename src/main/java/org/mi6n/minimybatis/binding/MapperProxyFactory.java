package org.mi6n.minimybatis.binding;

import org.mi6n.minimybatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * @author mifengjun@gmail.com
 */
public class MapperProxyFactory<T> {
    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession) {
        MapperProxy<T> proxy = new MapperProxy<>(mapperInterface, sqlSession);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, proxy);
    }
}
