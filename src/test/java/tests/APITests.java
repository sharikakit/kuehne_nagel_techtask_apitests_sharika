package tests;

import APIs.GenerateTokenAPIPage;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.TestBase;

import java.io.File;

public class APITests extends TestBase {

    File jsonDataInFile;
    RequestSpecification request;
    Response response;

    @Test
    public void TC_Post_01(){

        jsonDataInFile = new File("src/test/resources/requests/addBooksAPI.json");
        RestAssured.baseURI = BASEURL;
        request = RestAssured.given();

        GenerateTokenAPIPage generateTokenAPIPage = new GenerateTokenAPIPage();

        String authToken = generateTokenAPIPage.generateAccessToken();

        request.header("content-type", "application/json");
        request.header("Authorization", "Bearer " + authToken);
        request.body(jsonDataInFile);
        response = request.post("/BookStore/v1/Books");

        System.out.println("The status received: " + response.statusLine());
        System.out.println("The status received: " + response.getBody().asString());
        Assertions.assertEquals("201", response.statusCode());

    }

}
