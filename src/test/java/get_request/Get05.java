package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get05 extends RestfulBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends get request to the URL
    Then
        Status code is 200
      And
    Among the data there should be someone whose firstname is "Ali" and lastname is "Cengiz"
 */

    @Test
    public void get01() {
        //1-set the Url
        spec.pathParam("first", "booking").
                queryParams("firstname", "Kimie",
                        "lastname", "Jackie");


        //2-set the expected data

        //3-send the request and get the response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4-Do Assertion
        assertEquals(200, response.getStatusCode());
        assertTrue(response.asString().contains("bookingid"));


    }
}
