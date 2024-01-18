package Utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilsMethods {


    public static String getPropertiesValue(String fileName, String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\"
                                                              +fileName+".properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }

    public static String getJsonValue(Response response,String key)
    {
        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath.getString(key);
    }
}
