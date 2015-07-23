package com.pypmannetjies.findmesms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;

/**
 * Created by Christien on 2014-11-26.
 */
public class SmsListener extends BroadcastReceiver {

    private Context context;
    SettingsStorage settingsStorage;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                settingsStorage = new SettingsStorage(context);
                if (SmsComparator.doesSmsMessageMatchExpectedNumberAndPassword(smsMessage, settingsStorage.getNumberFromStorage(), settingsStorage.getPassphraseFromStorage())) {
                    GPSFinder finder = new GPSFinder(context, new SmsSender(settingsStorage.getNumberFromStorage()));
                    finder.startLocationUpdate();
                }
            }
        }
    }



}
