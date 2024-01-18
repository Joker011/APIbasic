package Day2;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class specBiilder {

    public static void main(String[] srgd)
    {

    // System.out.println("Hii");
        specBiilder specBiilder = new specBiilder();
        specBiilder.APIWithRequestSpecificationBuilder();


}

public void APIWithRequestSpecificationBuilder()
{
 // buidling with required data
    RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
            .addQueryParam("key","qaclick123")
            .setContentType(ContentType.JSON).build();

    RequestSpecification givenRequest = given().spec(reqSpec)
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
                        """);

    String resp= givenRequest
            .when().post("maps/api/place/add/json")
            .then().log().all().assertThat().statusCode(200)
            .body("scope", equalTo("APP"))
            .header("server","Apache/2.4.52 (Ubuntu)")
            .extract().response().asString();
    System.out.println("with req builder ==: " + resp);
}
public void simpleAPI()
{
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
