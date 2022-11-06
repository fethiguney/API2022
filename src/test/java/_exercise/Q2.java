package _exercise;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.ReqresPojo;
import test_data.ReqresTestData;
import utils.ObjectMapperUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q2 extends ReqresBaseUrl {

    /*
    Map ve Pojo Class ile ayrı ayrı yapınız.
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }

*/

    @Test
    public void testPojo() {
        //set the url
        spec.pathParam("first", "users");

        //set the expected data
        ReqresPojo expectedData=new ReqresPojo("morpheus", "leader");

        //send the request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");

        //do assertion
        ReqresPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(), ReqresPojo.class);
        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(expectedData.getJob(), actualData.getJob());

    }

    @Test
    public void testMap() throws IOException {
        //set the url
        spec.pathParam("first", "users");

        //set the expected data
        ReqresTestData obj=new ReqresTestData();
        Map<String, String> expectedData=obj.expectedDataMethod("morpheus", "leader");



        //send the request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");


        //do assertion
        Map actualData=ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println(actualData);
        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"), actualData.get("job"));


    }
}
