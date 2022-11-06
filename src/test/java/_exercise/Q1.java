package _exercise;

import base_url.AutomationExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Q1 extends AutomationExerciseBaseUrl {

    //1:
 /*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
*/

    @Test
    public void test01() {
        //set the url
        spec.pathParams("first", "api", "second", "brandsList");

        //set the expected data

        //send the request and get the response
       Response response= given().spec(spec).when().get("/{first}/{second}");

       //do assertion
       JsonPath jsonPath=response.jsonPath();
       List<String> brands=jsonPath.getList("brands.brand");
       long hM=brands.stream().filter(t->t.equals("H&M")).count();
       long polo=brands.stream().filter(t->t.equals("Polo")).count();

        assertEquals(200, response.getStatusCode());
        assertEquals("HTTP/1.1 200 OK", response.getStatusLine());
        assertEquals("text/html; charset=utf-8", response.getContentType());
        assertTrue(polo>hM);



    }
}
