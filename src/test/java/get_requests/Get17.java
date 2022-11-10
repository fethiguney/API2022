package get_requests;

import base_url.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get17 extends DummyRestApiBaseUrl {
    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends GET Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */

    @Test
    public void get17() {

        spec.pathParams("first", "employee", "second", 1);

        DummyRestApiDataPojo dummyRestApiDataPojo=new DummyRestApiDataPojo("Tiger Nixon",320800,61,"" );
        DummyRestApiPojo expectedData=new DummyRestApiPojo("success", "Successfully! Record has been fetched.", dummyRestApiDataPojo);

        Response response=given().spec(spec).when().get("/{first}/{second}");
        DummyRestApiPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiPojo.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getStatus(), actualData.getStatus());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(expectedData.getData().getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getProfile_image(), actualData.getData().getProfile_image());




    }
}
