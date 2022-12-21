package APIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import utils.TestBase;

import java.io.File;

public class GenerateTokenAPIPage extends TestBase {

    File jsonDataInFile;
    RequestSpecification request;
    Response response;

    public String generateAccessToken(){
        RestAssured.baseURI = BASEURL;

        jsonDataInFile = new File("src/test/resources/requests/generateTokenAPI.json");
        request = RestAssured.given();

        request.header("content-type", "application/json");
        request.body(jsonDataInFile);

        response = request.post("/Account/v1/GenerateToken");

        Assertions.assertEquals("200", response.statusCode());
        String accessToken = response.getBody().jsonPath().getString("token");
        return accessToken;

    }

}
