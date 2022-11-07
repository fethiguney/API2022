package post_requests;

import base_url.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06 extends DummyRestApiBaseUrl {

    /*

          Given
               https://dummy.restapiexample.com/api/v1/create
          And
               {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image",
                        "id": 4891
                     }
          When
              User sends POST Request to the url

          Then
              Status code is 200
          And
              Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }

     */

    @Test
    public void post06() {
       //set the url
       spec.pathParam("first", "create");

       //set the expected data
        DummyRestApiDataPojo expectedData=new DummyRestApiDataPojo("Tom Hanks",111111, 23, "Perfect image");

        //send the request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{first}");


        //do assertion
        DummyRestApiPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiPojo.class);
        System.out.println("actual data : "+actualData);
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(), actualData.getData().getProfile_image());




    }
}
