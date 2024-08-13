package org.mi6n.minimybatis.builder;

import org.mi6n.minimybatis.session.Configuration;

/**
 * @author mifengjun@gmail.com
 */
public class BaseBuilder {
    protected Configuration configuration;
    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }
}
