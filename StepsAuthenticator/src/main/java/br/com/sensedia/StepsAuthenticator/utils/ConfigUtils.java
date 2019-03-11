package br.com.sensedia.StepsAuthenticator.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {

    public static String getProperty(String propertyName) throws IOException {

        Properties prop = new Properties();
        InputStream input = null;

        String filename = "config.properties";
        input = ConfigUtils.class.getClassLoader().getResourceAsStream(filename);

        prop.load(input);

        return prop.getProperty(propertyName);

    }

}
