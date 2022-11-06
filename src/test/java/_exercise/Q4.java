package _exercise;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ReqresPojo;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Q4 extends ReqresBaseUrl {
    //4: Map ile ve Pojo Class ile ayrı ayrı Object Mapper kullanarak yapınız.
  /*
       Given
           1) https://reqres.in/api/users/2
           2) {
                "name": "neo"
               }
       When
            I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like   {
                                                "name": "neo",
                                                "updatedAt": "2022-10-02T12:53:21.675Z"
                                         }
    */

    @Test
    public void testMap() {
        //set the url
        spec.pathParams("first", "api", "second", "users", "third", "2");

        //set the expected data
        ReqresTestData obj=new ReqresTestData();
        Map<String, String> expectedData=obj.expectedDataMethod("neo",null);

        //send the request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}/{third}");
        response.prettyPrint();

        //do assertion
        Map actualData=response.as(HashMap.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("name"), actualData.get("name"));


    }
}
