package org.mi6n.minimybatis.session;

/**
 * @author mifengjun@gmail.com
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
