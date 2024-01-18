package Day1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicAPI {

    String place_id;
    String new_Address="main street, India";
    public static void main(String[] args)
    {

        BasicAPI basicAPI = new BasicAPI();
        basicAPI.post();
        basicAPI.put();
        basicAPI.get();
        basicAPI.delete();
    }

    public void post()
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
       this.place_id = jsonPath.get("place_id");
        System.out.println(this.place_id);
        String id = jsonPath.get("id");
        System.out.println(id);
    }

    public void put()
    {



        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+this.place_id+"\",\n" +
                        "\"address\":\""+this.new_Address+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200)
                .body("msg",equalTo("Address successfully updated"));
    }

    public void get()
    {
        String  res = given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",this.place_id)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
       JsonPath jsonPath = new JsonPath(res);
        String actualAddrss = jsonPath.get("address");
        System.out.println(actualAddrss);
        Assert.assertEquals(actualAddrss,this.new_Address);
    }

    public void delete()
    {
        String  res = given().log().all().queryParam("key","qaclick123")
                .body("{\n" +
                        "        \"place_id\":\""+this.place_id+"\"\n" +
                        "    }")
                .when().get("maps/api/place/delete/json")
                .then().log().all().assertThat().statusCode(200)
                .body("status",equalTo("OK"))
                .extract().response().asString();
        JsonPath jsonPath = new JsonPath(res);
        String status = jsonPath.get("status");
        System.out.println(status);
    }

}
