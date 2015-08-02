package com.pypmannetjies.findmesms;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {

    private TextView emailView;
    private TextView passphraseView;
    private Switch listenerSwitch;
    private SettingsStorage settingsStorage;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.changeBroadcastReceiverState(PackageManager.COMPONENT_ENABLED_STATE_DISABLED);
        setContentView(R.layout.activity_settings);
        emailView = (TextView) findViewById(R.id.emailAddress);
        passphraseView = (TextView) findViewById(R.id.passphrase);
        settingsStorage = new SettingsStorage(getBaseContext());
        listenerSwitch = (Switch) findViewById(R.id.listener_switch);
        mToast = Toast.makeText(getBaseContext(), "", Toast.LENGTH_SHORT);
        setValuesFromStorage();
    }

    private void setValuesFromStorage() {
        emailView.setText(settingsStorage.getNumberFromStorage());
        passphraseView.setText(settingsStorage.getPassphraseFromStorage());
    }

    public void storeValues(View view) {
        String email = emailView.getText().toString();
        String message = passphraseView.getText().toString();

        settingsStorage.writePhoneNumberToStorage(email);
        settingsStorage.writePassphraseToStorage(message);

        showConfirmation();
    }

    public void toggleListener(View view) {
        if (((Switch)view).isChecked()) {
            this.showQuickToast(getString(R.string.listener_on));
            this.changeBroadcastReceiverState(PackageManager.COMPONENT_ENABLED_STATE_ENABLED);
        } else {
            this.showQuickToast(getString(R.string.listener_off));
            this.changeBroadcastReceiverState(PackageManager.COMPONENT_ENABLED_STATE_DISABLED);

        }
    }

    private void changeBroadcastReceiverState(int state) {
        getPackageManager().setComponentEnabledSetting(
                new ComponentName(this.getBaseContext(), SmsListener.class),
                state,
                PackageManager.DONT_KILL_APP);
    }

    private void showConfirmation() {
        this.showQuickToast(getString(R.string.settings_saved));
    }

    private void showQuickToast(String toastText) {
       mToast.setText(toastText);
        mToast.show();
    }
}
