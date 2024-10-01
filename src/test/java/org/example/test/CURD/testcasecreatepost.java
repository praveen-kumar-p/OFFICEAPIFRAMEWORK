package org.example.test.CURD;

import groovy.beans.PropertyReader;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import jdk.jfr.Description;
import org.example.POJO.BookingResponse;
import org.example.base.Baseclass;
import org.example.endpoints.APIConstants;
import org.example.utility.propertyReader;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.*;

import java.util.PropertyPermission;

import static org.assertj.core.api.Assertions.*;

public class testcasecreatepost extends Baseclass {

    @Owner("Praveen")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that post request is working fine.")
    @Test
    public void TestCreatePost(){
        requestSpecification
                .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured.given(requestSpecification)
                .when().body(payloadsmanager.CreatePayloadBookingAsString()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(Integer.parseInt(propertyReader.readkey("booking.post.statuscode.success")));

        // Default Assertion
        validatableResponse.body("booking.firstname", Matchers.equalTo(propertyReader.readkey("booking.post.firstname")));


        BookingResponse bookingResponse = payloadsmanager.bookingResponseJava(response.asString());

        // AssertJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo(propertyReader.readkey("booking.post.firstname"));

        // TestNG Assertion
        assertaction.verifyStatusCode(response, 200);
    }
}
