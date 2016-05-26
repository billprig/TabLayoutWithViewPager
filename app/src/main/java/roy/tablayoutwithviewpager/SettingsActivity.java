package roy.tablayoutwithviewpager;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;

import java.util.HashMap;

public class SettingsActivity extends PreferenceActivity {

   private EditTextPreference username;
   SessionManager session;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      session = new SessionManager(getApplicationContext());
      HashMap<String, String> user = session.getUserDetails();

      // name
      String name = user.get(SessionManager.KEY_NAME);

      addPreferencesFromResource(R.xml.settings);
      username = (EditTextPreference) findPreference("prefUsername");
      username.setTitle("Username: "+name);
      username.setText(name);
   }
}