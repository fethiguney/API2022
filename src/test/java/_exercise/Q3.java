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
import static org.junit.Assert.assertEquals;

public class Q3 extends ReqresBaseUrl {
    //3: Map ile ve Pojo Class ile ayrı ayrı Gson kullanarak yapınız.

/*
        Given
            1) https://reqres.in/api/users/2
            2) {
                "name": "morpheus",
                "job": "zion president"
                }
        When
            I send Put Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "zion president",
                                                "updatedAt": "2022-10-02T11:35:05.693Z"
                                            }
*/

    @Test
    public void testMap() {

        //set the url
        spec.pathParams("first", "api", "second", "users", "third", "2");

        //set the expected data
        ReqresTestData obj=new ReqresTestData();
        Map<String, String> expectedData=obj.expectedDataMethod("morpheus", "zion president");

        //send the request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}/{third}");

        //do assertion
        Map<String, String> actualData=response.as(HashMap.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("name"), actualData.get("name"));
        assertEquals(expectedData.get("job"), actualData.get("job"));

    }

    @Test
    public void testPojo() {
        //set the url
        spec.pathParams("first", "api", "second", "users", "third", "2");

        //set the expected data
        ReqresPojo expectedData=new ReqresPojo("morpheus", "zion president");

        //send the request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}/{third}");

        //do assertion
        ReqresPojo actualData=response.as(ReqresPojo.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(expectedData.getJob(), actualData.getJob());
    }
}
