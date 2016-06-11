package roy.tablayoutwithviewpager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


   // Alert Dialog Manager
   //AlertDialogManager alert = new AlertDialogManager();

   // Session Manager Class
   SessionManager session;
   private TextView username_main1;

   private static final int RESULT_SETTINGS = 1;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      username_main1 = (TextView) findViewById(R.id.username_main1);


      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);
      getSupportActionBar().setIcon(R.drawable.logo);

      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            alcohol_dialog();
         }
      });

      // Session class instance
      session = new SessionManager(getApplicationContext());

      /**
       * Call this function whenever you want to check user login
       * This will redirect user to LoginActivity if he is not
       * logged in
       * */
      session.checkLogin();

      // get user data from session
      HashMap<String, String> user = session.getUserDetails();
      username_main1.setText(user.get(SessionManager.KEY_NAME));

   }

   //Adding Settings to the Toolbar
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {

      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.toolbar_menu, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {

         case R.id.settings:
            Intent i = new Intent(this, SettingsActivity.class);
            startActivityForResult(i, RESULT_SETTINGS);
            break;

      }

      return true;
   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);

      switch (requestCode) {
         case RESULT_SETTINGS:
            showUserSettings();
            break;

      }

   }

   private void showUserSettings() {
      HashMap<String, String> user = session.getUserDetails();
      HashMap<String, Float> user1 = session.getUserAlcohol();
      SharedPreferences sharedPrefs = PreferenceManager
         .getDefaultSharedPreferences(this);
      username_main1.setText(user.get(SessionManager.KEY_NAME));

      StringBuilder builder = new StringBuilder();

      builder.append("\n Username: "
         + user.get(SessionManager.KEY_NAME));

      builder.append("\n Weight: "
         + user1.get(SessionManager.KEY_WEIGHT)+"kg");

      builder.append("\n Sex: "
         + sharedPrefs.getString("sex", "NULL"));


      TextView settingsTextView = (TextView) findViewById(R.id.textUserSettings);

      settingsTextView.setText(builder.toString());
   }

   public void click(View view){
      startActivity(new Intent(this, MainActivity2.class));
   }

   public void alcohol_dialog(){
      // Alert Dialog Manager
      String oria="‑Επιτρεπτά Όρια Κατανάλωσης Αλκοόλ: 0-0.05%\n";
      String prostima_title="\n‑Πρόστιμα:";
      String prostimo1="\nΣυγκέντρωση οινοπνεύματος στο αίμα από 0,05% έως 0,08%\nΠρόστιμο 200,00€\n";
      String prostimo2="\nΣυγκέντρωση οινοπνεύματος στο αίμα από 0,08% μέχρι 0.11%\nΠρόστιμο 700,00€ και αφαίρεση, επιτόπου, της άδειας ικανότητας οδηγού για 90 ημέρες\n";
      String prostimo3="\nΣυγκέντρωση οινοπνεύματος στο αίμα άνω του 0.11%\nΦυλάκιση τουλάχιστον δύο μηνών, πρόστιμο 1.200,00€  και αφαίρεση, επιτόπου, της άδειας ικανότητας οδηγού για εκατόν 180 ημέρες";
      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
      alertDialogBuilder.setTitle("Όρια Αλκοόλ για τους Οδηγούς");
      alertDialogBuilder.setMessage(oria+prostima_title+prostimo1+prostimo2+prostimo3);
      AlertDialog alertDialog = alertDialogBuilder.create();
      alertDialog.show();
   }
}
