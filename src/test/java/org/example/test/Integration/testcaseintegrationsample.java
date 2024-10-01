package org.example.test.Integration;

import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import jdk.jfr.Description;
import org.example.POJO.BookingResponse;
import org.example.base.Baseclass;
import org.example.endpoints.APIConstants;
import org.example.utility.propertyReader;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.Assert;

import static org.assertj.core.api.Assertions.assertThat;

public class testcaseintegrationsample extends Baseclass {

    @Test(groups = "integration", priority = 1)
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext) {
        iTestContext.setAttribute("token", getToken());
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


        iTestContext.setAttribute("bookingid", bookingResponse.getBooking());

    }

    @Test(groups = "integration", priority = 2)
    @Owner("Promode")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext){

        // bookingId -
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

    }

    @Test(groups = "integration", priority = 3)
    @Owner("Promode")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext){

    }

    @Test(groups = "integration", priority = 4)
    @Owner("Promode")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext){

    }

}
