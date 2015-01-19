package com.pypmannetjies.findme;

import java.util.ArrayList;

import android.telephony.SmsManager;
import android.util.Log;

public class SmsSender {

    private final SmsManager smsManager;
    private String phoneNumber;

    public SmsSender(String phoneNumber) {
        this.smsManager = SmsManager.getDefault();
        this.phoneNumber = phoneNumber;
    }

    public void sendSMS(String message) {
        if (phoneNumber.length() < 10 || !phoneNumber.matches("[0-9]+")) {
            Log.e("SMS_ERROR", "Phone number is invalid, message not sent");
        } else {
            ArrayList<String> splitMessage = smsManager.divideMessage(message);
            smsManager.sendMultipartTextMessage(phoneNumber, null, splitMessage, null, null);
        }
    }


}
