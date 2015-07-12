package com.pypmannetjies.findme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by Christien on 2014-11-26.
 */
public class SmsListener extends BroadcastReceiver {

    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        Toast toast = Toast.makeText(context, "BROADCAST RECEIVER", Toast.LENGTH_LONG);
        toast.show();
//        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
//            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
//                if (isForUs(smsMessage)) {
//                    new GPSFinder(context, new SmsSender(getStoredNumber()));
//                }
//            }
//        }
    }

    protected boolean isForUs(SmsMessage smsMessage) {
        String messageBody = smsMessage.getMessageBody();
        String number = smsMessage.getOriginatingAddress();
        String storedNumber = getStoredNumber();
        number = sanitizeNumber(number);
        storedNumber = sanitizeNumber(storedNumber);
        return messageBody.equalsIgnoreCase("coordinates") && number.equalsIgnoreCase(storedNumber);
    }

    private String sanitizeNumber(String number) {
        number = removeCountryCode(number);
        number = removeLeadingZero(number);
        return number;
    }

    private String removeCountryCode(String number) {
        if (number.charAt(0) == '+' && number.length() == 12) {
            return number.substring(3);
        } else {
            return number;
        }
    }

    private String removeLeadingZero(String number) {
        if (number.charAt(0) == '0' && number.length() == 10) {
            return number.substring(1);
        } else {
            return number;
        }
    }


    private String getStoredNumber() {
        SettingsStorage settingsStorage = new SettingsStorage(context);
        return settingsStorage.getNumberFromStorage();
    }
}
