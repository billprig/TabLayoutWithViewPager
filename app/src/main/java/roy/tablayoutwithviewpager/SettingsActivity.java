package roy.tablayoutwithviewpager;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import java.util.HashMap;

public class SettingsActivity extends PreferenceActivity {

   private EditTextPreference username;
   private String name;
   SessionManager session;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      session = new SessionManager(getApplicationContext());
      HashMap<String, String> user = session.getUserDetails();

      // name
      name = user.get(SessionManager.KEY_NAME);

      addPreferencesFromResource(R.xml.settings);
      username = (EditTextPreference) findPreference("prefUsername");
      username.setTitle("Username: "+name);
      username.setText(name);

      final Preference pref = getPreferenceManager().findPreference("prefUsername");
      pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

         @Override
         public boolean onPreferenceChange(Preference preference, Object newValue) {
            if (newValue.toString().length()!=0){
               pref.setTitle("Username: "+ newValue.toString());
               name=newValue.toString();
               session.changeName(name);
            }


            return true;
         }
      });



   }
}