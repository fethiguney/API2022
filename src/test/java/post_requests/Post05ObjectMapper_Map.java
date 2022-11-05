package post_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post05ObjectMapper_Map extends JsonplaceholderBaseUrl {

    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
               }
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post05ObjectMapper() throws IOException {
        //set the url
        spec.pathParam("first", "todos");

        //set the expected data

        JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
        String jsonInString=obj.expectedDataInString(55, "Tidy your room", false);

        Map<String, Object> expectedData=new ObjectMapper().readValue(jsonInString, HashMap.class);
        System.out.println("expected : " +expectedData);

        //send the request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");


        //do assertion
        HashMap actualData=new ObjectMapper().readValue(response.asString(), HashMap.class);
        System.out.println(actualData);

        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));



    }
}
