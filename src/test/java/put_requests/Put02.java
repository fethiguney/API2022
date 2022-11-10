package put_requests;

import base_url.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02 extends DummyRestApiBaseUrl {
/*

     Given
       1- https://dummy.restapiexample.com/api/v1/update/21
       2- {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }

     When
        user sends put request
     Then
          Status code is 200
     And
        Response body should be like the following
     {
        "status": "success",
        "data": {
            "employee_name": "Ali Can",
            "employee_salary": 111111,
            "employee_age": 23,
            "profile_image": "Perfect image"
        },
        "message": "Successfully! Record has been updated."
    }

     */

    @Test
    public void put02() {

        spec.pathParams("first", "update", "second", "21");

        DummyRestApiDataPojo dummyRestApiDataPojo=new DummyRestApiDataPojo("Ali Can", 111111, 23, "Perfect image");
        DummyRestApiPojo expectedData=new DummyRestApiPojo("success", "Successfully! Record has been updated.", dummyRestApiDataPojo);

        Response response=given().spec(spec).contentType(ContentType.JSON).body(dummyRestApiDataPojo).when().put("/{first}/{second}");

        DummyRestApiPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiPojo.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getStatus(), actualData.getStatus());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(dummyRestApiDataPojo.getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(dummyRestApiDataPojo.getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(dummyRestApiDataPojo.getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(dummyRestApiDataPojo.getProfile_image(), actualData.getData().getProfile_image());

    }
}
