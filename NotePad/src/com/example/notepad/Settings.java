package com.example.notepad;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.app.Activity;
import android.app.NotificationManager;
import android.view.Menu;

public class Settings extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.settings);
		PreferenceScreen prefSet=getPreferenceScreen();
		 NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		 nm.cancel(R.string.notice_title);
		 CheckBoxPreference synCheckBoxPreference=(CheckBoxPreference)findPreference("sync_config_checkbox");
		 ListPreference syncListPreference =(ListPreference)findPreference("sync_interval_list");
		 
		
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

}
