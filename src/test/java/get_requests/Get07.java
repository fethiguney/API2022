package get_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get07 extends JsonplaceholderBaseUrl {

     /*
      Given
       https://jsonplaceholder.typicode.com/todos
When
    I send GET Request to the URL == > URL'e Get Request gonderin
Then
    1)Status code is 200 == > Status kodu 200 olmali
    2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
      Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
    3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
      Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
    4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
      Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
      basliginin "delectus aut autem" icerdigini dogrulayin
   */

    @Test
    public void get01() {

        //1-set the url
        spec.pathParam("first", "todos");

        //2-set the Expected Data

        //3-Send The Request
        Response response=given().spec(spec).when().get("/{first}");

        //4-Do assertion

        //i-Status code is 200
        response.then().assertThat().statusCode(200);
        assertEquals(200, response.getStatusCode());

        //ii-Print all ids greater than 190 on the console
        JsonPath json=response.jsonPath();
        List<Integer> idListGreater190=json.getList("findAll{it.id>190}.id");
        System.out.println("IDs greater than 190 : "+idListGreater190);

        //Assert that there are 10 ids greater than 190
        assertEquals("Ids greater than 190 are not 10", 10, idListGreater190.size());

        //iii-Print all userIds whose ids are less than 5 on the console
        List<Integer> idListLess5=json.getList("findAll{it.id<5}.userId");
        System.out.println("Ids less than 5"+idListLess5);

        //Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals("UserIds whose ids are less than 5 are not 4", 4, idListLess5.size());

        //iiii-Print all titles whose ids are less than 5
        List<String> titles=json.getList("findAll{it.id<5}.title");
        System.out.println("Titles whose ids are less than 5 : "+titles);

        //Assert that "delectus aut autem" is one of the titles whose id is less than 5
        assertTrue("Titles doesn't contains this",titles.contains("delectus aut autem"));

        assertTrue("Titles doesn't contains this",
                        titles.
                        stream().
                        anyMatch(t->t.contains("delectus aut autem")));


    }
}
