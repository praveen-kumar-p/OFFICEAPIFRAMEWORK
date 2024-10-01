package org.example.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bookingdates {

    @SerializedName("checkout")
    @Expose
    private String checkout;

    @SerializedName("checkin")
    @Expose
    private String checkin;

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }
}
