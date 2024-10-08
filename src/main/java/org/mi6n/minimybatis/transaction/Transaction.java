package org.mi6n.minimybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author mifengjun@gmail.com
 */
public interface Transaction {
    Connection getConnection() throws SQLException;
    void commit() throws SQLException;
    void rollback() throws SQLException;
    void close() throws SQLException;

}
