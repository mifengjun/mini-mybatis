package org.mi6n.minimybatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author mifengjun@gmail.com
 */
public interface DataSourceFactory {
    void setProperties(Properties properties);
    DataSource getDataSource();
}
