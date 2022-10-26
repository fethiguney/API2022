package get_request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get02 {

    /* Given
    https://restful-booker.herokuapp.com/booking/1
    When
    User send a GET Request to the url
    Then
    HTTP Status code should be 404
    And
    Status Line should be HTTP/1.1 404 Not Found
    And
    Response body contains "Not Found"
    And
    Response body does not contain "TechProEd"
    And
    Server is "Cowboy"
    */


    @Test
    public void get02() {

        //  i)  Set the URL,
        String url="https://restful-booker.herokuapp.com/booking/1";

        // ii) Set the expected Data
        // iii) Type code to send request
        Response response=given().when().get(url);
        response.prettyPrint();

        // iv) Do Assertion
        //Status Line should be HTTP/1.1 404 Not Found
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        //Response body contains "Not Found"
        assertTrue(response.asString().contains("Not Found"));

        //Response body does not contain "TechProEd"
        assertFalse(response.asString().contains("TechProEd"));

        //Server is "Cowboy"
        assertEquals("Cowboy", response.getHeader("Server"));






    }
}
