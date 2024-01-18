package Day4;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class APIusingPOJO {

    public static void main(String[] args)
    {
        // System.out.println("Hii");
        RestAssured.baseURI= "https://rahulshettyacademy.com";
        Gmaps gmaps = new Gmaps();
        gmaps.setAccuracy(50);
        gmaps.setName("Frontline house");
        gmaps.setPhone_number("(+91) 983 893 3937");
        gmaps.setAddress("29, side layout, cohen 09");
        gmaps.setWebsite("http://google.com");
        gmaps.setLanguage("French-IN");
        List<String> listOFTypes = new ArrayList<>();
        listOFTypes.add("shoe park");
        listOFTypes.add("shop");
        gmaps.setTypes(listOFTypes);
        location location = new location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        gmaps.setlocation(location);

       Response res = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
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
               .extract().response();
       String respo = res.asString();
       System.out.println("response : "+respo);
        String r = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(gmaps)
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        System.out.println("fdgd="+r);
      /*  String response =  given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(gmaps)
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                //.body("scope", equalTo("APP"))
                .header("server","Apache/2.4.52 (Ubuntu)").log().all()
                .extract().response().asString();*/
       /* String response =  given().queryParam("key","qaclick123").header("Content-Type","application/json")
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
                .extract().response().asString();*/
     /*   System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);
        String placeID = jsonPath.get("place_id");
        System.out.println(placeID);
        String id = jsonPath.get("id");
        System.out.println(id);

        String  res = given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeID)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();*/

        /*Gmaps gmaps = given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeID)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().as(Gmaps.class);

        System.out.println(gmaps.getLanguage());*/
       /* System.out.println(gmaps.getlocation().getLatitude());
        System.out.println(gmaps.getlocation().getLongitude());*/

    }
}
