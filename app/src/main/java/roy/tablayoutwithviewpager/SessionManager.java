package roy.tablayoutwithviewpager;

/**
 * Created by billprig on 25/05/16.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

public class SessionManager {
   // Shared Preferences
   SharedPreferences pref;

   // Editor for Shared preferences
   Editor editor;

   // Context
   Context _context;

   // Shared pref mode
   int PRIVATE_MODE = 0;

   // Sharedpref file name
   private static final String PREF_NAME = "AndroidPref";

   // All Shared Preferences Keys
   private static final String IS_LOGIN = "IsLoggedIn";

   // User name (make variable public to access from outside)
   public static final String KEY_NAME = "name";

   // Weight (make variable public to access from outside)
   public static final String KEY_WEIGHT = "weight";

   // SEX (make variable public to access from outside)
   public static final String KEY_SEX = "sex";


   // ALCOHOL (make variable public to access from outside)
   public static final String KEY_ALCOHOL = "alcohol";

   //real_alcohol
   private Float real_alcohol = 0.0f;

   // Constructor
   public SessionManager(Context context){
      this._context = context;
      pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
      editor = pref.edit();
   }

   /**
    * Create login session
    * */
   public void createLoginSession(String name, Float weight , String sex){
      // Storing login value as TRUE
      editor.putBoolean(IS_LOGIN, true);

      // Storing name in pref
      editor.putString(KEY_NAME, name);

      // Storing weight in pref
      editor.putFloat(KEY_WEIGHT, weight);

      //
      editor.putString(KEY_SEX,sex);


      editor.putFloat(KEY_ALCOHOL,(float)real_alcohol);

      // commit changes
      editor.commit();
   }

   /**
    * Check login method wil check user login status
    * If false it will redirect user to login page
    * Else won't do anything
    * */
   public void checkLogin(){
      // Check login status
      if(!this.isLoggedIn()){
         // user is not logged in redirect him to Login Activity
         Intent i = new Intent(_context, LoginActivity.class);
         // Closing all the Activities
         i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

         // Add new Flag to start new Activity
         i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

         // Staring Login Activity
         _context.startActivity(i);
      }

   }

   //change the name of user
   public void changeName(String string){
      editor.remove(KEY_NAME);
      editor.putString(KEY_NAME, string);
      editor.commit();
}


   private Float sex_number() {
      if(getUserDetails().get(KEY_SEX).equals("Male")) return (float) 0.68;
      else return  0.55f;
   }


   public Float bac(int time){
      float bac1 = (float) (getUserAlcohol().get(KEY_ALCOHOL)*0.789);
      float weight;
      weight = (float) getUserAlcohol().get(KEY_WEIGHT) * sex_number() * 10;
      bac1 = bac1/weight;
      bac1 = bac1 - time * 0.015f;
      return bac1;
   }

   //change  sex region of user
   public void changeSex(String string){
      editor.remove(KEY_SEX);
      editor.putString(KEY_SEX, string);
      editor.commit();
   }

   public float alcoholInGrams(int Volume ,int Alcoholpercent){
      real_alcohol = getUserAlcohol().get(KEY_ALCOHOL)+ Volume * Alcoholpercent/100.0f;
      editor.remove(KEY_ALCOHOL);
      editor.putFloat(KEY_ALCOHOL, (float) real_alcohol);
      editor.commit();
      return real_alcohol;
   }

   public void reset(){
      editor.remove(KEY_ALCOHOL);
      editor.putFloat(KEY_ALCOHOL, (float) 0);
      editor.commit();
   }



   public HashMap<String, Float> getUserAlcohol(){
      HashMap<String, Float> user1 = new HashMap<String, Float>();
      // user name
      user1.put(KEY_ALCOHOL, pref.getFloat(KEY_ALCOHOL, 0.0f));
      user1.put(KEY_WEIGHT,pref.getFloat(KEY_WEIGHT,0.0f));
      // user email id
      //user.put(KEY_WEIGHT, pref.getString(KEY_WEIGHT, null));

      // return user
      return user1;
   }



   /**
    * Get stored session data
    * */
   public HashMap<String, String> getUserDetails(){
      HashMap<String, String> user = new HashMap<String, String>();
      // user name
      user.put(KEY_NAME, pref.getString(KEY_NAME, null));

      // user weight
      //user.put(KEY_WEIGHT, pref.getString(KEY_WEIGHT, null));

      user.put(KEY_SEX, pref.getString(KEY_SEX, null));
      // return user
      return user;
   }

   /**
    * Clear session details
    * */
   public void logoutUser(){
      // Clearing all data from Shared Preferences
      editor.clear();
      editor.commit();
      //real_alcohol = 0;

      // After logout redirect user to Loing Activity
      Intent i = new Intent(_context, LoginActivity.class);
      // Closing all the Activities
      i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

      // Add new Flag to start new Activity
      i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

      // Staring Login Activity
      _context.startActivity(i);
   }

   /**
    * Quick check for login
    * **/
   // Get Login State
   public boolean isLoggedIn(){
      return pref.getBoolean(IS_LOGIN, false);
   }
}
