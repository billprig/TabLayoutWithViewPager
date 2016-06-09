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
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class LoginActivity extends Activity {

   // Email, password edittext
   EditText txtUsername, txtWeight;
   RadioGroup sex;
   RadioButton selected_sex;

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

      // Username, Weight input text
      txtUsername = (EditText) findViewById(R.id.txtUsername);
      txtWeight = (EditText) findViewById(R.id.txtWeight);
      txtWeight.setRawInputType(InputType.TYPE_CLASS_NUMBER
         | InputType.TYPE_NUMBER_FLAG_DECIMAL);

      txtWeight.addTextChangedListener(new CustomTextWatcher(
         txtWeight));

      //radio button, sex
      sex = (RadioGroup) findViewById(R.id.sex);





      //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();


      // Login button
      btnLogin = (Button) findViewById(R.id.btnLogin);


      // Login button click event
      btnLogin.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View arg0) {
            // Get username, password from EditText
            String username = txtUsername.getText().toString();
            String weight = txtWeight.getText().toString();
            Float wght;
            String sexAnswer;

            //String firstletter = String.valueOf(weight.charAt(0));
            // Check if username, weight is filled
            if(username.trim().length() > 0 && weight.length() >1 ){

               selected_sex = (RadioButton) findViewById(sex.getCheckedRadioButtonId());
               sexAnswer =  selected_sex.getText().toString();
               // Creating user login session
               wght= Float.parseFloat(weight);
               // Use user real data
               session.createLoginSession(username, wght,sexAnswer);

               // Staring MainActivity
               Intent i = new Intent(getApplicationContext(), MainActivity.class);
               startActivity(i);
               finish();

               }
            else if(username.trim().length()== 0 && weight.length() <2)
               opendialog("Login failed...","Username/weight is incorrect");

            else if(username.trim().length()== 0)
               opendialog("Login failed...","Username is empty");

            else if(weight.length() <2)
               opendialog("Login failed...","Weight is incorrect");

         }
      });
   }

   public void opendialog(String Title, String Messagge){
      // Alert Dialog Manager
      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
      alertDialogBuilder.setTitle(Title);
      alertDialogBuilder.setMessage(Messagge);
      AlertDialog alertDialog = alertDialogBuilder.create();
      alertDialog.show();
   }
}