package get_requests;

import base_url.AutomationExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get08b extends AutomationExerciseBaseUrl {

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

        //send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        JsonPath jsonPath=response.jsonPath();

        //Do Assertion
        assertEquals(200,response.statusCode());
        assertEquals("text/html; charset=utf-8", response.contentType());
        assertEquals("HTTP/1.1 200 OK", response.statusLine());


       //There must be 12 Women, 9 Men, 13 Kids usertype in products
        List<String> womenCategory=jsonPath.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        List<String> menCategory=jsonPath.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        List<String> kidsCategory=jsonPath.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");

        assertEquals(12, womenCategory.size());
        assertEquals(9, menCategory.size());
        assertEquals(13, kidsCategory.size());

        //2.yol
        List<String> category=jsonPath.getList("products.category.usertype.usertype");
        assertEquals(12, category.stream().filter(t->t.contains("Women")).count());
        assertEquals(9, category.stream().filter(t->t.contains("Men")).count());
        assertEquals(13, category.stream().filter(t->t.contains("Kids")).count());





    }
}
