package Utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;

public class requestSpecification {

    public static RequestSpecification requestSpecification;
    public RequestSpecification getRequestSpecification() throws IOException {
        if(requestSpecification==null) {
            PrintStream printStream = new PrintStream(new FileOutputStream(new File("src/main/java/Logs/log.txt")));
            requestSpecification =
                    new RequestSpecBuilder()
                            .setBaseUri(UtilsMethods.getPropertiesValue("basicAPI", "baseURI"))
                            .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                            .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                            .addQueryParam("key", "qaclick123")
                            .setContentType(ContentType.JSON).build();
            return requestSpecification;
        }
        else {
            return requestSpecification;
        }
    }


}
