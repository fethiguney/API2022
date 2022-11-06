package post_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post05ObjectMapper_Pojo extends JsonplaceholderBaseUrl {

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
            response body is like  {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post05() {

        //set the url
        spec.pathParam("first", "todos");

        //set the expected data
        JsonPlaceHolderPojo expectedData=new JsonPlaceHolderPojo(55, "Tidy your room", false);

        //send the request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");

        //do assertion
       JsonPlaceHolderPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);
       System.out.println(actualData);

        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), expectedData.getTitle());
        assertEquals(expectedData.getCompleted(), expectedData.getCompleted());


    }
}
