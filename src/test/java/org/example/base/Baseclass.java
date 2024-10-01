package org.example.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.Assertaction.Assertaction;
import org.example.endpoints.APIConstants;
import org.example.modules.payloadsmanager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class Baseclass {

    public RequestSpecification requestSpecification;
    public Assertaction assertaction;
    public payloadsmanager payloadsmanager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setUp() {
        payloadsmanager = new payloadsmanager();
        assertaction = new Assertaction();
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();
    }

    public String getToken(){
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);

        // Setting the payload
        String payload = payloadsmanager.setAuthPayload();

        // get the token
        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();

        // String Extraction
        String token = payloadsmanager.getTokenfromJson(response.asString());

        return token;
    }
}
