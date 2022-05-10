package src.Maptest;

import java.util.Properties;

public class propertiestest {
    //15*2
    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.setProperty("100","hello");
        String value = properties.getProperty("100");

        System.out.println(value);
    }
}
