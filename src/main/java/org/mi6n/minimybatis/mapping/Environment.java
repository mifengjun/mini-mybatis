package org.mi6n.minimybatis.mapping;

import org.mi6n.minimybatis.transaction.TransactionFactory;

import javax.sql.DataSource;

/**
 * @author mifengjun@gmail.com
 */
public class Environment {
    private final String id;
    private final TransactionFactory transactionFactory;

    private final DataSource dataSource;

    public String getId() {
        return id;
    }

    public TransactionFactory getTransactionFactory() {
        return transactionFactory;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public Environment(final String id, final TransactionFactory transactionFactory, final DataSource dataSource) {
        this.id = id;
        this.transactionFactory = transactionFactory;
        this.dataSource = dataSource;
    }


    public static class Builder {
        private final String id;
        private TransactionFactory transactionFactory;

        private DataSource dataSource;

        public Builder(final String id) {
            this.id = id;
        }

        public Builder transactionFactory(final TransactionFactory transactionFactory) {
            this.transactionFactory = transactionFactory;
            return this;
        }

        public Builder dataSource(final DataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public Environment build() {
            return new Environment(id, transactionFactory, dataSource);
        }
    }
}
