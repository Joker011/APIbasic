package OAuth;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class ClientCredential {


    public static void main(String[] args)
    {
        ClientCredential clientCredential = new ClientCredential();
       String  accessToken = clientCredential.post();
       clientCredential.get(accessToken);
    }

    public String post()
    {
     String res = given().log().all()
              .formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
              .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
              .formParam("grant_type","client_credentials")
              .formParam("scope","trust")
              .when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
              .asString();
    JsonPath jsonPath = new JsonPath(res);

     return jsonPath.get("access_token").toString();
    }
    public void get(String token)
    {
      String response = given().log().all()
              .queryParam("access_token",token)
              .when().log().all()
              .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
             .then().assertThat().statusCode(401)
              .extract().response().asString();
      System.out.println(response);
    }
}
