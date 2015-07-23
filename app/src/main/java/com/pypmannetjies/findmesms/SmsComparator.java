package com.pypmannetjies.findmesms;

import android.telephony.SmsMessage;

/**
 * Created by Christien on 2015-07-14.
 */
public class SmsComparator {

    public static boolean doesSmsMessageMatchExpectedNumberAndPassword(SmsMessage smsMessage, String expectedNumber, String expectedPassword) {
        String messageBody = smsMessage.getMessageBody();
        String number = smsMessage.getOriginatingAddress();
        number = sanitizeNumber(number);
        expectedNumber = sanitizeNumber(expectedNumber);
        return messageBody.equals(expectedPassword) && number.equalsIgnoreCase(expectedNumber);
    }

    private static String sanitizeNumber(String number) {
        number = removeCountryCode(number);
        number = removeLeadingZero(number);
        return number;
    }

    private static String removeCountryCode(String number) {
        if (number.charAt(0) == '+' && number.length() == 12) {
            return number.substring(3);
        } else {
            return number;
        }
    }

    private static String removeLeadingZero(String number) {
        if (number.charAt(0) == '0' && number.length() == 10) {
            return number.substring(1);
        } else {
            return number;
        }
    }
}
