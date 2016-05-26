package roy.tablayoutwithviewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


   // Alert Dialog Manager
   //AlertDialogManager alert = new AlertDialogManager();

   // Session Manager Class
   SessionManager session;

   // Button Logout
   Button btnLogout;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);



      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               .setAction("Action", null).show();
         }
      });




      // Session class instance
      session = new SessionManager(getApplicationContext());

      TextView lblName = (TextView) findViewById(R.id.lblName);
      TextView lblEmail = (TextView) findViewById(R.id.lblEmail);

      // Button logout
      btnLogout = (Button) findViewById(R.id.btnLogout);

      Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();


      /**
       * Call this function whenever you want to check user login
       * This will redirect user to LoginActivity is he is not
       * logged in
       * */
      session.checkLogin();

      // get user data from session
      HashMap<String, String> user = session.getUserDetails();

      // name
      String name = user.get(SessionManager.KEY_NAME);

      // email
      String email = user.get(SessionManager.KEY_EMAIL);

      // displaying user data
      lblName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
      lblEmail.setText(Html.fromHtml("Email: <b>" + email + "</b>"));


      /**
       * Logout button click event
       * */
      btnLogout.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View arg0) {
            // Clear the session data
            // This will clear all session data and
            // redirect user to LoginActivity
            session.logoutUser();
         }
      });
   }



   //Adding Settings to the Toolbar
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {

      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.toolbar_menu, menu);
      return true;
   }



   public void click(View view){
      startActivity(new Intent(this, MainActivity2.class));
   }
}
