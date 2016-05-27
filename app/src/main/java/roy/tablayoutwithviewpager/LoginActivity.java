package roy.tablayoutwithviewpager;

/**
 * Created by billprig on 25/05/16.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

   // Email, password edittext
   EditText txtUsername, txtWeight;

   // login button
   Button btnLogin;



   // Session Manager Class
   SessionManager session;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_login);

      // Session Manager
      session = new SessionManager(getApplicationContext());

      // Email, Password input text
      txtUsername = (EditText) findViewById(R.id.txtUsername);
      txtWeight = (EditText) findViewById(R.id.txtPassword);
      txtWeight.setRawInputType(InputType.TYPE_CLASS_NUMBER
         | InputType.TYPE_NUMBER_FLAG_DECIMAL);

      txtWeight.addTextChangedListener(new CustomTextWatcher(
         txtWeight));


      Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();


      // Login button
      btnLogin = (Button) findViewById(R.id.btnLogin);


      // Login button click event
      btnLogin.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View arg0) {
            // Get username, password from EditText
            String username = txtUsername.getText().toString();
            String weight = txtWeight.getText().toString();
            //String firstletter = String.valueOf(weight.charAt(0));
            // Check if username, password is filled
            if(username.trim().length() > 0 && weight.length() >1  ){
               // For testing puspose username, password is checked with sample data
               // username = test
               // password = test
               //if(username.equals("test") && password.equals("test")){

                  // Creating user login session
                  // For testing i am stroing name, email as follow
                  // Use user real data
                  session.createLoginSession(username, weight);

                  // Staring MainActivity
                  Intent i = new Intent(getApplicationContext(), MainActivity.class);
                  startActivity(i);
                  finish();

               }
            else{
                  // username / password doesn't match
                      opendialog();
               }
            /*}else{
               // user didn't entered username or password
               // Show alert asking him to enter the details
               //      alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please enter username and password", false);
            }*/

         }
      });
   }
   public void opendialog(){
      // Alert Dialog Manager
      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
      alertDialogBuilder.setTitle("Login failed...");
      alertDialogBuilder.setMessage("Username/Weight is incorrect");
      AlertDialog alertDialog = alertDialogBuilder.create();
      alertDialog.show();

      //alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        // @Override
         //public void onClick(DialogInterface arg0, int arg1) {

    //     }
      //});
   }
}