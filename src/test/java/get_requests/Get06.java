package get_requests;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Get06 extends RestfulBaseUrl {
    /*
       Given
           https://restful-booker.herokuapp.com/booking/2325
       When
           User send a GET request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response content type is "application/json"
       And
           Response body should be like;
        {
           "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-10-27",
        "checkout": "2022-11-07"
    },
    "additionalneeds": "None"

     */

    @Test
    public void get01() {

        //1-set the url

        spec.pathParams("first", "booking", "second", "2325");

        //2-set the expected data

        //3-send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4-Do assertion

        //1.yol
        response.
                then().
                assertThat().statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Bradley"),
                        "lastname", equalTo("Pearson"),
                        "totalprice",equalTo(132),
                        "depositpaid", equalTo(false),
                        "bookingdates.checkin", equalTo("2022-10-27"),
                        "bookingdates.checkout", equalTo("2022-11-07"),
                        "additionalneeds", equalTo("None"));


        //2.yol JsonPath class ın kullanımı
        JsonPath json=response.jsonPath();

        assertEquals("Bradley", json.getString("firstname"));
        assertEquals("Pearson", json.getString("lastname"));
        assertEquals(132, json.getInt("totalprice"));
        assertFalse(json.getBoolean("depositpaid"));
        assertEquals("2022-10-27", json.getString("bookingdates.checkin"));
        assertEquals("2022-11-07", json.getString("bookingdates.checkout"));
        assertEquals("None", json.getString("additionalneeds"));


       //3.yol Soft Assertion
        //softassert class ı 3 adimda kullanilir

        //i-obje olusturma
        SoftAssert softAssert=new SoftAssert();

        //ii-Do assertion
        softAssert.assertEquals(json.getString("firstname"), "Bradley","firstname hatali");
        softAssert.assertEquals(json.getString("lastname"), "Pearson", "lastname hatali");
        softAssert.assertEquals(json.getInt("totalprice"), 132, "totalprice hatali");
        softAssert.assertEquals(json.getBoolean("depositpaid"), false, "deposit hatali");
        softAssert.assertEquals(json.getString("bookingdates.checkin"), "2022-10-27", "checkin hatali");
        softAssert.assertEquals(json.getString("bookingdates.checkout"), "2022-11-07", "checkout hatali");
        softAssert.assertEquals(json.getString("additionalneeds"), "None", "additional hatali");
        //iii-en son assertAll yazilmali
        softAssert.assertAll();


    }
}
