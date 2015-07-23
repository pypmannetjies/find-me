package com.pypmannetjies.findmesms;

import android.telephony.SmsMessage;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class SmsComparatorTest {

    String number = "0123456789";
    String password = "password";

    @Test
    public void testDoesSmsMessageMatchExpectedNumberAndPassword() throws Exception {
        boolean returned = getResultOf(number, password, number, password);
        assertEquals( "Same number and password matches", returned, true);
    }

    @Test
    public void testDoesSmsMessageMatchCountryCodeNumberAndPassword() throws Exception {
        boolean returned = getResultOf("+27123456789", password, number, password);
        assertEquals( "Input number in different country code", returned, true);
    }

    @Test
    public void expectedNumberDifferentCountryCode() throws Exception {
        boolean returned = getResultOf(number, password, "+27123456789", password);
        assertEquals( "Expected number in different country code", returned, true);
    }

    @Test
    public void passwordWrong() throws Exception {
        boolean returned = getResultOf(number, "wrong", number, password);
        assertEquals( "Password wrong", returned, false);
    }

    @Test
    public void passwordWrongCase() throws Exception {
        boolean returned = getResultOf(number, "Password", number, password);
        assertEquals( "Password wrong case", returned, false);
    }

    @Test
    public void differentNumber() throws Exception {
        boolean returned = getResultOf("0124456789", password, number, password);
        assertEquals( "Different number", returned, false);
    }

    @Test
    public void differentNumberInLeadingZerio() throws Exception {
        boolean returned = getResultOf("1123456789", password, number, password);
        assertEquals( "Different number in leading zero", returned, false);
    }

    private boolean getResultOf(String inputNumber, String inputPassword, String expectedNumber, String expectedPassword) {
        SmsMessage smsMessage = createMockMessage(inputNumber, inputPassword);
        return SmsComparator.doesSmsMessageMatchExpectedNumberAndPassword(smsMessage, expectedNumber, expectedPassword);
    }

    private SmsMessage createMockMessage(String number, String password) {
        SmsMessage smsMessage = Mockito.mock(SmsMessage.class);
        Mockito.when(smsMessage.getOriginatingAddress()).thenReturn(number);
        Mockito.when(smsMessage.getMessageBody()).thenReturn(password);
        return smsMessage;
    }
}