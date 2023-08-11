package utils;
// this class to read the data from the config-properties
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties prop;
    public static Properties readProperties(String filePath){
        try {
            FileInputStream fis=new FileInputStream(filePath);
            prop=new Properties();
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }
    //in SDETjavaBAtch16 projrct this method name is getProperty so we need to change it cucumber to getPropertyValue
    public static String getPropertyValue(String key){
        return prop.getProperty(key);
    }
}
