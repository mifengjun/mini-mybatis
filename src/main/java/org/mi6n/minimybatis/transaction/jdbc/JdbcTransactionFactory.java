package org.mi6n.minimybatis.transaction.jdbc;

import org.mi6n.minimybatis.transaction.Transaction;
import org.mi6n.minimybatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author mifengjun@gmail.com
 */
public class JdbcTransactionFactory implements TransactionFactory {


    @Override
    public Transaction newInstance(Connection connection) {
        return new JdbcTransaction(connection);
    }

    @Override
    public Transaction newInstance(DataSource dataSource, boolean autoCommit) {
        return new JdbcTransaction(dataSource, autoCommit);
    }
}
