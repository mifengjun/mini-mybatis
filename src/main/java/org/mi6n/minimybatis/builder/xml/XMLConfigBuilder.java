package org.mi6n.minimybatis.builder.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.mi6n.minimybatis.builder.BaseBuilder;
import org.mi6n.minimybatis.io.Resource;
import org.mi6n.minimybatis.mapping.MappedStatement;
import org.mi6n.minimybatis.mapping.SqlCommandType;
import org.mi6n.minimybatis.session.Configuration;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mifengjun@gmail.com
 */
public class XMLConfigBuilder extends BaseBuilder {

    private static final Pattern pattern = Pattern.compile("(#\\{(.*?)})");
    private Element root;

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
            mapperElement(root.element("mappers"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return configuration;
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
