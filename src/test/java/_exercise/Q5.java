package _exercise;

import base_url.PetStoreSwaggerBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.PetStoreSwaggerPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Q5 extends PetStoreSwaggerBaseUrl {

//5:
    /*
    https://petstore.swagger.io/ documantation adresini kullanarak
    'user' oluşturan ve oluşsan bu user'ı silen bir otomasyon kodu yazınız.

    base url=petstore.swagger.io/v2

    Given
        1-https://petstore.swagger.io/v2/user
        2-{
            "id": 12,
            "username": "Joe",
            "firstName": "Joe",
            "lastName": "Black",
            "email": "jblack@gmail.com",
            "password": "1234",
            "phone": "+156689895",
            "userStatus": 0
  }
    When
       Post request to the url
    Then
           Status code is 200
    And
        Response body should be like the following

    */

    @Test
    public void postTest() {

        //set the url
        spec.pathParam("first", "user");

        //set the expected data
        PetStoreSwaggerPojo expectedData=new PetStoreSwaggerPojo("john", "john", "black", "johnb@gmail.com", "1234", "+1987452", 200);

        //send the request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //do assertion

        assertEquals(200, response.getStatusCode());

    }

    @Test
    public void getTest() {

        //set the url
        spec.pathParams("first", "user", "second", "john");

        //set the expected data
        PetStoreSwaggerPojo expectedData=new PetStoreSwaggerPojo("john", "john", "black", "johnb@gmail.com", "1234", "+1987452", 200);

        //send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        PetStoreSwaggerPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(), PetStoreSwaggerPojo.class);
        System.out.println(actualData);

        assertEquals(expectedData.getUsername(), actualData.getUsername());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        assertEquals(expectedData.getLastName(), actualData.getLastName());
        assertEquals(expectedData.getEmail(), actualData.getEmail());
        assertEquals(expectedData.getPassword(), actualData.getPassword());
        assertEquals(expectedData.getPhone(), actualData.getPhone());
        assertEquals(expectedData.getUserStatus(), actualData.getUserStatus());

    }
}
