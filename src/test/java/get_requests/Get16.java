package get_requests;

import base_url.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyRestApiBaseUrl {

    /*

   Given
       https://dummy.restapiexample.com/api/v1/employees
   When
       user sends get request
   Then
        i) Status code is 200
   And
        ii) There are 24 employees
   And
        iii) "Tiger Nixon" and "Garrett Winters" are among the employees
   And
        iv) The greatest age is 66
   And
        v) The name of the lowest age is "Tatyana Fitzpatrick"
   And
       vi) Total salary of all employees is 6,644,770

    */

    @Test
    public void get16() {
        spec.pathParam("first", "employees");

        Response response=given().spec(spec).when().get("/{first}");

        //Status code is 200
        response.then().statusCode(200);

        //There are 24 employees
        //"Tiger Nixon" and "Garrett Winters" are among the employees
        response.then().assertThat().body("data", hasSize(24),
                "data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"));

        //The greatest age is 66
        List<Integer> ages= response.jsonPath().getList("data.employee_age");
        Collections.sort(ages);
        assertEquals(66, (int)ages.get(ages.size()-1));

        //The name of the lowest age is "Tatyana Fitzpatrick"
        String lowestAgeEmployee=response.jsonPath().getString("data.findAll{it.employee_age== "+ages.get(0)+" }.employee_name");
        assertEquals("[Tatyana Fitzpatrick]", lowestAgeEmployee );

        //Total salary of all employees is 6,644,770
        List<Integer> salaries=response.jsonPath().getList("data.employee_salary");
        //1.yol
        int sum=0;
        for (Integer w:salaries) {
            sum+=w;
        }
        assertEquals(6644770, sum);

        //2.yol
        int sumOfSalaries=salaries.stream().reduce(Math::addExact).get();
        assertEquals(6644770, sumOfSalaries);


    }
}
