package get_requests;

import base_url.GorestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertTrue;

public class Get12 extends GorestBaseUrl {

    /*
       Given
           https://gorest.co.in/public/v1/users
       When
           User send GET Request
       Then
           The value of "pagination limit" is 10
       And
           The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
       And
           The number of users should  be 10
       And
           We have at least one "active" status
       And
           Niranjan Gupta, Samir Namboothiri and Gandharva Kaul are among the users
       And
           The female users are less than or equals to male users
    */

    @Test
    public void test() {
        //1. Set The URL
        spec.pathParam("first", "users");

        // 2. Set The Expected Data ( put, post, patch)

        // 3. Send The Request And Get The Response
        Response response=given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        // 4. Do Assertion
        response.then().assertThat().statusCode(200).
                        body("meta.pagination.limit", equalTo(10),
                                "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                                "data", hasSize(10),
                                "data.status", hasItem("active"),
                                "data.name", hasItems("Pres. Amarnath Dhawan", "Sujata Chaturvedi", "Navin Panicker"));

        //Kadin ve erkek sayilarinin karsilastirilmasi
        //1.yol
       JsonPath jsonPath=response.jsonPath();
       int numFemale=0;
       List<String> genders=jsonPath.getList("data.gender");
        for (String w:genders) {
            if (w.equalsIgnoreCase("female")){
                numFemale++;
            }
        }
        assertTrue(numFemale<=genders.size()-numFemale);

        //2.yol: Kadin ve erkek sayilarini groovy ile bulalım
        List<String> femaleNames=response.jsonPath().getList("data.findAll{it.gender=='female'}.name");
        List<String> maleNames=response.jsonPath().getList("data.findAll{it.gender=='male'}.name");
        assertTrue(femaleNames.size()<=maleNames.size());

    }
}
