package org.mi6n.minimybatis.builder;

import org.mi6n.minimybatis.session.Configuration;
import org.mi6n.minimybatis.type.TypeAliasRegistry;

/**
 * @author mifengjun@gmail.com
 */
public class BaseBuilder {
    protected Configuration configuration;
    protected TypeAliasRegistry typeAliasRegistry;
    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = configuration.getTypeAliasRegistry();
    }
}
