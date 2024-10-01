package org.example.Assertaction;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

public class Assertaction {

    public void verifyResponseBody(String actual, String expected, String description) {
        assertEquals(actual, expected, description);
    }

    public void verifyResponseBody(int actual, int expected, String description) {
        assertEquals(actual, expected, description);
    }

    public void verifyStatusCode(Response response, Integer expected) {
        assertEquals(response.getStatusCode(), expected);
    }

}
