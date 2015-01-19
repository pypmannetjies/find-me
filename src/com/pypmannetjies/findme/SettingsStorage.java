package com.pypmannetjies.findme;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsStorage {

    private static final String PHONE_NUMBER_KEY = "phoneNumberKey";
    private static final String PASSPHRASE_KEY = "passphraseKey";
    private static final String SHARED_PREFS_KEY = "FindMeSharedPreferences";
    private static final String HISTORY = "history";
    private static final String HISTORY_COUNT = "history_count";

    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private Context context;

    public SettingsStorage(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(SHARED_PREFS_KEY, context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void writePhoneNumberToStorage(String email) {
        editor.putString(PHONE_NUMBER_KEY, email);
        editor.commit();
    }

    public void writePassphraseToStorage(String email) {
        editor.putString(PASSPHRASE_KEY, email);
        editor.commit();
    }

    public String getNumberFromStorage() {
        return preferences.getString(PHONE_NUMBER_KEY, "");
    }

    public String getPassphraseFromStorage() {
        return preferences.getString(PASSPHRASE_KEY, "coordinates");
    }

}
