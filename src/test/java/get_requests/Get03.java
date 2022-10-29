package get_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get03 extends JsonplaceholderBaseUrl {

    /*
      Given
          https://jsonplaceholder.typicode.com/todos/23
      When
          User send GET Request to the URL
      Then
          HTTP Status Code should be 200
        And
            Response format should be "application/json"
        And
            "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
        And
            "completed" is false
        And
            "userId" is 2
           */

    @Test
    public void get03() {
        //set the URL
        spec.pathParams("first", "todos", "second", 23);

        // ii) Set the expected Data
        // iii) Type code to send request and get response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // iv) Do Assertion

        //1.yol Hard Assert

        //HTTP Status Code should be 200
        response.then().assertThat().statusCode(200);

        //Response format should be "application/json"
        response.
                then().
                contentType("application/json").
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed", equalTo(false)).
                body("userId", equalTo(2));

        //2.yol
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),
                        "userId", equalTo(2));


        }
}
