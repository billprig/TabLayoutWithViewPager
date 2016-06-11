package roy.tablayoutwithviewpager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;


public class MainActivity2 extends AppCompatActivity {
    ViewPager  pager;
    TabLayout tabLayout;

   TextView bac;
   TextView hours_text;
   SeekBar custom_vol;
   SeekBar custom_alc;
   private float time;
   SessionManager session;

    @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main2);

       session = new SessionManager(getApplicationContext());
       hour_dialog();


        pager= (ViewPager) findViewById(R.id.view_pager);
        tabLayout= (TabLayout) findViewById(R.id.tab_layout);

        hours_text =(TextView) findViewById(R.id.text_hours);
        bac=(TextView) findViewById(R.id.BAC_calculator);
         String str2 = new BigDecimal(session.bac(0))
          .setScale(3, BigDecimal.ROUND_HALF_UP)
          .toString();
         bac.setText(str2+" %");

       session.reset();
       Apotelesma("0%");

         //set up seekbars
        custom_vol = (SeekBar) findViewById(R.id.seekBarPosotita);
        custom_alc = (SeekBar) findViewById(R.id.seekBarAlcohol);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goback();
            }
        });

        FragmentManager manager=getSupportFragmentManager();

        //fragment.specific_function_name();
        PagerAdapter adapter=new PagerAdapter(manager);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        // mTabLayout.setupWithViewPager(mPager1);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //fragment_custom = (CustomFragment) manager.findFragmentById(R.id.fragment_custom);
        tabLayout.setTabsFromPagerAdapter(adapter);

    }

    @Override
   public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu2, menu);
        return true;
    }

    @Override
   public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset:
               session.reset();
                Apotelesma("0%");
                break;
        }
        return true;
    }


   //set text to Bac_result textview
   public void Apotelesma (String text){
      bac.setText(text);
   }

   //On_Click Events for buttons
   public void set_wine1l(View view) {
        session.alcoholInGrams(1000,13);
        String str2 = new BigDecimal(session.bac(time))
           .setScale(3, BigDecimal.ROUND_HALF_UP)
           .toString();
        bac.setText(str2+" %");
    }

   public void set_wine750(View view) {
        session.alcoholInGrams(750,13);
        String str2 = new BigDecimal(session.bac(time))
           .setScale(3, BigDecimal.ROUND_HALF_UP)
           .toString();
        bac.setText(str2+" %");
    }

   public void set_wine250(View view) {
        session.alcoholInGrams(250,13);
        String str2 = new BigDecimal(session.bac(time))
           .setScale(3, BigDecimal.ROUND_HALF_UP)
           .toString();
        bac.setText(str2+" %");
    }

   public void set_beer250(View view) {
        session.alcoholInGrams(250,5);
        String str2 = new BigDecimal(session.bac(time))
           .setScale(3, BigDecimal.ROUND_HALF_UP)
           .toString();
        bac.setText(str2+" %");
    }

   public void set_beer330(View view) {
        session.alcoholInGrams(330,5);
        String str2 = new BigDecimal(session.bac(time))
           .setScale(3, BigDecimal.ROUND_HALF_UP)
           .toString();
        bac.setText(str2+" %");
    }

   public void set_beer500(View view) {
        session.alcoholInGrams(500,5);
        String str2 = new BigDecimal(session.bac(time))
           .setScale(3, BigDecimal.ROUND_HALF_UP)
           .toString();
        bac.setText(str2+" %");
    }

   public void set_beer1l(View view) {
        session.alcoholInGrams(1000,5);
        String str2 = new BigDecimal(session.bac(time))
           .setScale(3, BigDecimal.ROUND_HALF_UP)
           .toString();
        bac.setText(str2+" %");
    }

   public void set_drink330(View view) {
      session.alcoholInGrams(330,40);
      String str2 = new BigDecimal(session.bac(time))
         .setScale(3, BigDecimal.ROUND_HALF_UP)
         .toString();
      bac.setText(str2+" %");
   }

   public void set_drink250(View view) {
      session.alcoholInGrams(250,40);
      String str2 = new BigDecimal(session.bac(time))
         .setScale(3, BigDecimal.ROUND_HALF_UP)
         .toString();
      bac.setText(str2+" %");
   }

   public void set_drink125(View view) {
      session.alcoholInGrams(125,40);
      String str2 = new BigDecimal(session.bac(time))
         .setScale(3, BigDecimal.ROUND_HALF_UP)
         .toString();
      bac.setText(str2+" %");
   }
   //End of On_Click Events

   private void goback() {
      startActivity(new Intent(this, MainActivity.class));
      finish();
   }

   public void hour_dialog(){
      final String hours_txt = "Time elapsed since drinking began: ";
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      LayoutInflater inflater = LayoutInflater.from(this);
      View theView = inflater.inflate(R.layout.picker, null);


      final NumberPicker hours = (NumberPicker) theView.findViewById(R.id.hour_picker);

      builder.setCancelable(false);
      builder.setView(theView)
         .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               float float_h = Float.parseFloat(String.valueOf(hours.getValue())) /2.0f;
               hours_text.setText(hours_txt+Float.toString(float_h)+"h");
               time = float_h;
            }
         }).setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
            goback();
            finish();
         }
      });

      String[] nums = {"0","0.5","1","1.5","2","2.5","3","3.5","4","4.5","5","5.5","6","6.5","7","7.5","8","8.5","9","9.5","10","10.5","11","11.5","12"};
      hours.setMinValue(0);
      hours.setMaxValue(nums.length-1);
      hours.setDisplayedValues(nums);
      hours.setValue(0);

      builder.show();

   }

   //get method which we use in CustomFragment
   public float get_time() {
      return time;
   }
}

