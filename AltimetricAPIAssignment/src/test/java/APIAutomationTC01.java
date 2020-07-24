import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;
import org.testng.annotations.Test;
import util.APITestBase;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

/***
 * Created by: Pretty.Sanwale
 *
 * TestScenario:
 *
 * Beginner Requirements:
 * 1.  Manage dependencies between API calls.
 * 2.  Verify the response status code.
 * 3.  Verify the response contents (Minimum 2).
 *
 *
 * ****/

public class APIAutomationTC01 extends APITestBase {

    @Test
    public void Test01() {

        /*
          Manage dependencies between API calls.
        */

        Response response = RestAssured.get(API_URL);
        System.out.println("the status code  is : " + response.getStatusCode());
        System.out.println("--------------------------");

        given().delete(DELETE_API_URL)
                .then().statusCode(200)
                .log();

        given().get(GET_HEADERS_API_URL)
                .then().statusCode(200)
                .log();

        given().post(POST_HEADERS_API_URL)
                .then()
                .contentType("text/plain; charset=utf-8")
                .statusCode(500)
                .log();

    }

    @Test
    public void Test02() {

        /*
          2.  Verify the response status code.
          3.  Verify the response contents (Minimum 2).
        */

        Response response = RestAssured.get(API_URL);
        System.out.println("the status code  is : " + response.getStatusCode());
        System.out.println("--------------------------");

        ResponseBody body = response.getBody();
        System.out.println("the json response is : " + body.prettyPrint());
        System.out.println("--------------------------");

        try {
            JSONObject jsonObject = new JSONObject(body.asString());
            if(jsonObject.has("args")){
                String value = jsonObject.get("args").toString();
                System.out.println("the value of args jsonObject is : " + value);
                System.out.println("--------------------------");
            }

            if(jsonObject.has("headers")){
                String value = jsonObject.get("headers").toString();
                System.out.println("the value of header jsonObject is : " + value);
                System.out.println("--------------------------");
                JSONObject jsonObject1 = jsonObject.getJSONObject("headers");
                String value2 = jsonObject1.get("x-amzn-trace-id").toString();
                System.out.println("the value of x-amzn-trace-id is : " + value2);
                System.out.println("--------------------------");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
