package stepDefinition;


import EnumData.APIResource;
import Pojo.SetData;
import Utilities.UtilsMethods;
import Utilities.extentreport;
import Utilities.requestSpecification;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
public class basicAPI implements En {
    requestSpecification requestSpecification = new requestSpecification();
    RequestSpecification givenRequest;
    Response response;
    static HashMap<String,String> responseData = new HashMap<>();
    SetData setData = new SetData();
    ExtentTest test = extentreport.getTest();
        public basicAPI()
        {
            When("^User load the payload$",()->{
                test.info("User load the payload");
               givenRequest= given().spec(requestSpecification.getRequestSpecification())
                        .body(setData.getGmaps());
            });
            When("^User post the call$", () -> {
                test.info("User post the call");
               response= givenRequest.when().post("maps/api/place/add/json")
                       .then().extract().response();
            });
            Then("^User validate the status as (\\d+)$", (Integer status) -> {
                Assert.assertEquals(response.getStatusCode(),status);
            });
            And("^User validate the \"([^\"]*)\" with \"([^\"]*)\"$", (String key, String value) -> {
                Assert.assertEquals(UtilsMethods.getJsonValue(response,key),value);
            });
            Given("^User load the payload with \"([^\"]*)\" , \"([^\"]*)\" and \"([^\"]*)\"$", (String name, String phone, String address) -> {
                givenRequest= given().spec(requestSpecification.getRequestSpecification())
                        .body(setData.getGmaps(name,phone,address));
            });
            When("^User post the call for \"([^\"]*)\"$", (String call) -> {
                response= givenRequest.when().post(APIResource.valueOf(call).getResource())
                        .then().extract().response();
            });
            When("^User \"([^\"]*)\" the call for \"([^\"]*)\"$", (String APIMethod, String call) -> {
                if(APIMethod.equalsIgnoreCase("POST"))
                    response= givenRequest.when().post(APIResource.valueOf(call).getResource())
                            .then().extract().response();
                else if (APIMethod.equalsIgnoreCase("GET"))
                    response = givenRequest.when().get(APIResource.valueOf(call).getResource())
                            .then().extract().response();
                else if(APIMethod.equalsIgnoreCase("DELETE"))
                    response= givenRequest.when().delete(APIResource.valueOf(call).getResource())
                            .then().extract().response();
                else if(APIMethod.equalsIgnoreCase("PUT"))
                    response= givenRequest.when().put(APIResource.valueOf(call).getResource())
                            .then().extract().response();
            });
            Given("^User \"([^\"]*)\" the payload with \"([^\"]*)\" , \"([^\"]*)\" and \"([^\"]*)\"$",
                    (String method, String name, String phone, String address) -> {

                if(method.equalsIgnoreCase("POST"))
                    givenRequest= given().spec(requestSpecification.getRequestSpecification())
                            .body(setData.getGmaps(name,phone,address));
                else if(method.equalsIgnoreCase("PUT"))
                    givenRequest= given().spec(requestSpecification.getRequestSpecification())
                            .body(setData.updateAddress("qaclick123",responseData.get("place_id")));
            });
            And("^User save the \"([^\"]*)\" from response$", (String key) -> {
               responseData.put(key,UtilsMethods.getJsonValue(response,key));
               System.out.println(responseData.get(key));
            });
            Given("^User load the payload for \"([^\"]*)\"$", (String APIMethod) -> {
                if(APIMethod.equalsIgnoreCase("POST"))
                    givenRequest= given().spec(requestSpecification.getRequestSpecification())
                            .body(setData.getGmaps());
                else if(APIMethod.equalsIgnoreCase("DELETE"))
                    givenRequest= given().spec(requestSpecification.getRequestSpecification())
                            .body(setData.getGmaps());
                else if(APIMethod.equalsIgnoreCase("PUT"))
                    givenRequest = given().spec(requestSpecification.getRequestSpecification())
                            .body(setData.updateAddress("qaclick123", responseData.get("place_id")));
                else if(APIMethod.equalsIgnoreCase("GET"))
                    givenRequest = given().spec(requestSpecification.getRequestSpecification())
                                          .queryParam("key","qaclick123")
                                          .queryParam("place_id",responseData.get("place_id"));
            });
        }
}
