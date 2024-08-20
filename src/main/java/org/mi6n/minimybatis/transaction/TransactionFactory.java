package org.mi6n.minimybatis.transaction;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author mifengjun@gmail.com
 */
public interface TransactionFactory {
    Transaction newInstance(Connection connection);

    Transaction newInstance(DataSource dataSource, boolean autoCommit);
}
