package Day1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.lang.*;
public class Testing {

    public static void main(String[] args)
    {
       // System.out.println("Hii");
        RestAssured.baseURI= "https://rahulshettyacademy.com";
       String response =  given().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("""
                        {
                          "location": {
                            "lat": -38.383494,
                            "lng": 33.427362
                          },
                          "accuracy": 50,
                          "name": "Frontline house",
                          "phone_number": "(+91) 983 893 3937",
                          "address": "29, side layout, cohen 09",
                          "types": [
                            "shoe park",
                            "shop"
                          ],
                          "website": "http://google.com",
                          "language": "French-IN"
                        }
                        """)
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server","Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();

        JsonPath jsonPath = new JsonPath(response);
        String placeID = jsonPath.get("place_id");
        System.out.println(placeID);
        String id = jsonPath.get("id");
        System.out.println(id);
        String new_Address = "main street, India";

        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                      "\"place_id\":\""+placeID+"\",\n" +
                      "\"address\":\""+new_Address+"\",\n" +
                      "\"key\":\"qaclick123\"\n" +
                      "}\n")
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200)
                .body("msg",equalTo("Address successfully updated"));

        String  res = given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeID)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        jsonPath = new JsonPath(res);
        String actualAddrss = jsonPath.get("address");
        System.out.println(actualAddrss);
        Assert.assertEquals(actualAddrss,new_Address);
       
    }
}