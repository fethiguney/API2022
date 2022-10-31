package get_requests;

import base_url.AutomationExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get08c extends AutomationExerciseBaseUrl {

    /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be "text/html; charset=utf-8"
    And
        Status Line should be HTTP/1.1 200 OK
    And
         There must be 12 Women, 9 Men, 13 Kids usertype in products
      */


    @Test
    public void get01() {

        //set the url
        spec.pathParams("first", "api", "second", "productsList");

        //set the expected data

        //send the request and get response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        JsonPath jsonPath=response.jsonPath();
        List<String> categoryList=jsonPath.getList("products.category.category");
        System.out.println(categoryList);

        //Do assertion
        assertEquals(200,response.statusCode());
        assertEquals("text/html; charset=utf-8", response.contentType());
        assertEquals("HTTP/1.1 200 OK", response.getStatusLine());






    }
}
