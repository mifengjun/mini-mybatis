package org.mi6n.minimybatis.transaction.jdbc;

import org.mi6n.minimybatis.transaction.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author mifengjun@gmail.com
 */
public class JdbcTransaction implements Transaction {
    protected  Connection connection;
    protected  DataSource dataSource;
    // 隔离级别；
    /**
     * @see Connection
     * A constant indicating that transactions are not supported.
     */
//    int TRANSACTION_NONE             = 0;
//
//    /**
//     * A constant indicating that
//     * dirty reads, non-repeatable reads and phantom reads can occur.
//     * This level allows a row changed by one transaction to be read
//     * by another transaction before any changes in that row have been
//     * committed (a "dirty read").  If any of the changes are rolled back,
//     * the second transaction will have retrieved an invalid row.
//     */
//    int TRANSACTION_READ_UNCOMMITTED = 1;
//
//    /**
//     * A constant indicating that
//     * dirty reads are prevented; non-repeatable reads and phantom
//     * reads can occur.  This level only prohibits a transaction
//     * from reading a row with uncommitted changes in it.
//     */
//    int TRANSACTION_READ_COMMITTED   = 2;
//
//    /**
//     * A constant indicating that
//     * dirty reads and non-repeatable reads are prevented; phantom
//     * reads can occur.  This level prohibits a transaction from
//     * reading a row with uncommitted changes in it, and it also
//     * prohibits the situation where one transaction reads a row,
//     * a second transaction alters the row, and the first transaction
//     * rereads the row, getting different values the second time
//     * (a "non-repeatable read").
//     */
//    int TRANSACTION_REPEATABLE_READ  = 4;
//
//    /**
//     * A constant indicating that
//     * dirty reads, non-repeatable reads and phantom reads are prevented.
//     * This level includes the prohibitions in
//     * {@code TRANSACTION_REPEATABLE_READ} and further prohibits the
//     * situation where one transaction reads all rows that satisfy
//     * a {@code WHERE} condition, a second transaction inserts a row that
//     * satisfies that {@code WHERE} condition, and the first transaction
//     * rereads for the same condition, retrieving the additional
//     * "phantom" row in the second read.
//     */
//    int TRANSACTION_SERIALIZABLE     = 8;

    protected  boolean autoCommit;

    public JdbcTransaction(Connection connection) {
        this.connection = connection;
    }

    public JdbcTransaction(DataSource dataSource, boolean autoCommit) {
        this.dataSource = dataSource;
        this.autoCommit = autoCommit;
    }

    @Override
    public Connection getConnection() throws SQLException {
        connection = dataSource.getConnection();
        connection.setAutoCommit(autoCommit);
        return connection;
    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void close() {

    }
}
