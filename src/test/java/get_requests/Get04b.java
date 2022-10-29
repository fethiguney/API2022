package get_requests;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get04b extends RestfulBaseUrl {
     /*
    Given
        https://restful-booker.herokuapp.com/booking?firstname=Almedin&lastname=Alikadic
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Almedin" and lastname is "Alikadic"

 */

    @Test
    public void get04() {

        //1-set the url
        spec.pathParam("first", "booking").
                queryParams("firstname", "Almedin",
                        "lastname", "Alikadic");

        //2-set the expected data

        //3-send the request and get the response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4-Do Assertion
        assertEquals(200, response.getStatusCode());
        assertTrue(response.asString().contains("bookingid"));







    }
}
