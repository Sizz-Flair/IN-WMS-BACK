package com.wms;

import java.util.Properties;

public class Prop {
    private static Properties properties = new Properties();

    public static String getProp(String propKey) {
        String result = "";
        try {
            result = String.valueOf(properties.getProperty(propKey));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
