package com.pypmannetjies.findme;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	private TextView emailView;
	private TextView messageView;
	private SettingsStorage settingsStorage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		emailView = (TextView) findViewById(R.id.emailAddress);
		messageView = (TextView) findViewById(R.id.passphrase);
		settingsStorage = new SettingsStorage(getBaseContext());
		setValuesFromStorage();
	}
	
	private void setValuesFromStorage() {
		emailView.setText(settingsStorage.getNumberFromStorage());
		messageView.setText(settingsStorage.getPassphraseFromStorage());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void storeValues(View view) {
		String email = emailView.getText().toString();
		String message = messageView.getText().toString();
		
		settingsStorage.writePhoneNumberToStorage(email);
		settingsStorage.writePassphraseToStorage(message);
		
		showConfirmation();
		this.finish();
	}
	
	private void showConfirmation() {
		Toast toast = Toast.makeText(getBaseContext(), "Settings saved.", Toast.LENGTH_LONG);
		toast.show();
	}
}
