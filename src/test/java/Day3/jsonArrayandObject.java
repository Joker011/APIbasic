package Day3;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class jsonArrayandObject {


    public static void main(String[] args)
    {
        JsonPath jsonPath = new JsonPath(response);
        int totalPurchase = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(totalPurchase);
        int countOfCourses=	jsonPath.getInt("courses.size()");
        System.out.println(countOfCourses);
        String titleFirstCourse=jsonPath.get("courses[0].title");
        System.out.println(titleFirstCourse);
        for(int i=0;i<countOfCourses;i++)
        {
            String courseTitles=jsonPath.get("courses["+i+"].title");
            System.out.println(jsonPath.get("courses["+i+"].price").toString());

            System.out.println(courseTitles);

        }
        //Print no of copies sold by RPA Course

        System.out.println("Print no of copies sold by RPA Course");

        for(int i=0;i<countOfCourses;i++)
        {
            String courseTitles=jsonPath.get("courses["+i+"].title");
            if(courseTitles.equalsIgnoreCase("RPA"))
            {
                int copies=jsonPath.get("courses["+i+"].copies");
                System.out.println(copies);
                break;
            }


        }
        int sum = 0;
        for(int i=0;i<countOfCourses;i++)
        {
            int price=jsonPath.getInt("courses["+i+"].price");
            int copies=jsonPath.getInt("courses["+i+"].copies");
            int amount = price * copies;
            System.out.println(amount);
            sum = sum + amount;

        }
        System.out.println(sum);
        int purchaseAmount =jsonPath.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum, purchaseAmount);


    }
    public static String response = """
            {
            "dashboard": {
            "purchaseAmount": 910,"website": "rahulshettyacademy.com"
            },
            "courses": [
            {
            "title": "Selenium Python",
            "price": 50,
            "copies": 6
            },
            {
            "title": "Cypress",
            "price": 40,
            "copies": 4
            },
            {
            "title": "RPA",
            "price": 45,
            "copies": 10
            }
            ]
            }
            """;
}
