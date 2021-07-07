package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class getProperties {
    static Properties props = new Properties();

    public static Properties getProps(){

        FileReader reader = null;
        try {
            reader = new FileReader("src\\test\\resources\\system.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
// loading properties from file.
        try {
            props.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props;
    }

    public static String getSpecificProperty(String proName){
        getProps();
        return props.getProperty(proName);
    }

    public static void main(String args[]){
        System.out.println(getSpecificProperty("users"));
    }
}
