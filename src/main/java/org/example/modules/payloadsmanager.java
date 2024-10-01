package org.example.modules;

import com.google.gson.Gson;
import org.example.POJO.*;

public class payloadsmanager {

    public String CreatePayloadBookingAsString(){
        Booking booking = new Booking();
        booking.setFirstname("James");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setDepositepaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);
        // Java Object -> JSON String (byteStream) - Serlization
        Gson gson = new Gson();
        String jsonStringpayload = gson.toJson(booking);
        System.out.println(jsonStringpayload);
        return jsonStringpayload;
    }

    public BookingResponse bookingResponseJava(String responseString) {
        Gson gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }

    // get Token
    // get Booking Id
    public String setAuthPayload(){
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        Gson gson = new Gson();
        String jsonpayloadString = gson.toJson(auth);
        System.out.println("payload set to the --> " + jsonpayloadString);
        return jsonpayloadString;
    }

    public String getTokenfromJson(String tokenResponse){
        Gson gson = new Gson();
        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse, TokenResponse.class);
        return tokenResponse1.getToken();
    }
}
