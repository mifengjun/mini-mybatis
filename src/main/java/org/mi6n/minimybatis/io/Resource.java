package org.mi6n.minimybatis.io;

import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @author mifengjun@gmail.com
 */
public class Resource {


    public static Reader getResourceAsReader(String path) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return new InputStreamReader(classLoader.getResourceAsStream(path));
    }
}
