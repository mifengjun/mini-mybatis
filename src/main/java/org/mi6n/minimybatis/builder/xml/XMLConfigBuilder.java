package org.mi6n.minimybatis.builder.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.mi6n.minimybatis.builder.BaseBuilder;
import org.mi6n.minimybatis.datasource.DataSourceFactory;
import org.mi6n.minimybatis.io.Resource;
import org.mi6n.minimybatis.mapping.Environment;
import org.mi6n.minimybatis.mapping.MappedStatement;
import org.mi6n.minimybatis.mapping.SqlCommandType;
import org.mi6n.minimybatis.session.Configuration;
import org.mi6n.minimybatis.transaction.TransactionFactory;
import org.xml.sax.InputSource;

import javax.sql.DataSource;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mifengjun@gmail.com
 */
public class XMLConfigBuilder extends BaseBuilder {

    private static final Pattern pattern = Pattern.compile("(#\\{(.*?)})");
    private Element root;
    private String environment;

    public XMLConfigBuilder(Reader reader) {
        super(new Configuration());

        root = readXMLElement(reader);

    }

    private Element readXMLElement(Reader reader) {
        SAXReader saxReader = new SAXReader();
        try {
            Document read = saxReader.read(new InputSource(reader));
            return read.getRootElement();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    public Configuration parse() {
        try {
            // 解析环境
            environmentsElement(root.element("environments"));
            // 解析 mapper
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    private void environmentsElement(Element context) throws Exception {
        if (context != null) {
            if (environment == null) {
                environment = context.attributeValue("default");
            }
            List<Element> environment = context.elements("environment");
            for (Element element : environment) {
                String id = element.attributeValue("id");
                if (isSpecifiedEnvironment(id)) {
                    // 事务管理
                    TransactionFactory transactionFactory = (TransactionFactory) typeAliasRegistry.resolveAlias(element.element("transactionManager").attributeValue("type")).newInstance();
                    // 数据源
                    DataSourceFactory dataSourceFactory = (DataSourceFactory) typeAliasRegistry.resolveAlias(element.element("dataSource").attributeValue("type")).newInstance();

                    List<Element> propertiesElements = element.elements("property");
                    Properties properties = new Properties();
                    for (Element propertyElement : propertiesElements) {
                        properties.setProperty(propertyElement.attributeValue("name"), propertyElement.attributeValue("value"));
                    }
                    dataSourceFactory.setProperties(properties);

                    DataSource dataSource = dataSourceFactory.getDataSource();

                    Environment.Builder environmentBuilder = new Environment.Builder(id);
                    environmentBuilder.transactionFactory(transactionFactory);
                    environmentBuilder.dataSource(dataSource);
                    environmentBuilder.build();

                    configuration.setEnvironment(environmentBuilder.build());
                }
            }
        }
    }

    private boolean isSpecifiedEnvironment(String id) {
        if (environment == null) {
            throw new RuntimeException("No environment specified.");
        } else if (id == null) {
            throw new RuntimeException("Environment requires an id attribute.");
        } else if (environment.equals(id)) {
            return true;
        }
        return false;
    }

    private void mapperElement(Element mappers) throws ClassNotFoundException {
        List<Element> mapperList = mappers.elements("mapper");
        for (Element mapperElement : mapperList) {//命名空间

            String resource = mapperElement.attributeValue("resource");
            Element mapper = readXMLElement(Resource.getResourceAsReader(resource));
            String namespace = mapper.attributeValue("namespace");


            List<Element> selectList = mapper.elements("select");
            for (Element selectElement : selectList) {

                String selectId = selectElement.attributeValue("id");

                String parameterType = selectElement.attributeValue("parameterType");
                String resultType = selectElement.attributeValue("resultType");
                String sql = selectElement.getText();

                Matcher matcher = pattern.matcher(sql);
                Map<Integer, String> parameter = new HashMap<>();

                for (int i = 1; matcher.find(); i++) {
                    String group1 = matcher.group(1);
                    String group2 = matcher.group(2);
                    parameter.put(i, group2);
                    sql = sql.replace(group1, "?");
                }

                MappedStatement mappedStatement = new MappedStatement.Builder(configuration, selectId, SqlCommandType.SELECT,
                        sql, parameterType, resultType, parameter).build();

                configuration.addMappedStatement(mappedStatement);
            }
            configuration.addMapper(Class.forName(namespace));
        }

    }

}
