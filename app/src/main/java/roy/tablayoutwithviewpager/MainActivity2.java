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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;


public class MainActivity2 extends AppCompatActivity /*implements NumberPicker.OnValueChangeListener*/{
    ViewPager  pager;
    TabLayout tabLayout;

    TextView bac;


    SeekBar custom_vol;
    SeekBar custom_alc;
    SessionManager session;

    //CustomFragment fragment_custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main2);

       session = new SessionManager(getApplicationContext());
       hour_dialog();


       /*Button b = (Button) findViewById(R.id.button);// on click of button display the dialog
       b.setOnClickListener(new View.OnClickListener()
       {

          @Override
          public void onClick(View v) {
             hour();
          }
       });


*/
        pager= (ViewPager) findViewById(R.id.view_pager);
        tabLayout= (TabLayout) findViewById(R.id.tab_layout);
        bac=(TextView) findViewById(R.id.BAC_calculator);
         String str2 = new BigDecimal(session.bac(0))
          .setScale(3, BigDecimal.ROUND_HALF_UP)
          .toString();
         bac.setText(str2+" %");



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

   private void goback(){ startActivity(new Intent(this, MainActivity.class));}


    //public void set(View view) {
    //    bac.setText("pressed");
   // }

    public void set_wine1l(View view) {
        session.alcoholInGrams(1000,13);
        String str2 = new BigDecimal(session.bac(0))
           .setScale(3, BigDecimal.ROUND_HALF_UP)
           .toString();
        bac.setText(str2+" %");
    }

    public void Apotelesma (String text){
        bac.setText(text);
    }


    public void set_wine750(View view) {
        session.alcoholInGrams(750,13);
        String str2 = new BigDecimal(session.bac(0))
           .setScale(3, BigDecimal.ROUND_HALF_UP)
           .toString();
        bac.setText(str2+" %");
    }

    public void set_wine250(View view) {
        session.alcoholInGrams(250,13);
        String str2 = new BigDecimal(session.bac(0))
           .setScale(3, BigDecimal.ROUND_HALF_UP)
           .toString();
        bac.setText(str2+" %");
    }

    public void set_beer250(View view) {
        session.alcoholInGrams(250,5);
        String str2 = new BigDecimal(session.bac(0))
           .setScale(3, BigDecimal.ROUND_HALF_UP)
           .toString();
        bac.setText(str2+" %");
    }

    public void set_beer330(View view) {
        session.alcoholInGrams(330,5);
        String str2 = new BigDecimal(session.bac(0))
           .setScale(3, BigDecimal.ROUND_HALF_UP)
           .toString();
        bac.setText(str2+" %");
    }

    public void set_beer500(View view) {
        session.alcoholInGrams(500,5);
        String str2 = new BigDecimal(session.bac(0))
           .setScale(3, BigDecimal.ROUND_HALF_UP)
           .toString();
        bac.setText(str2+" %");
    }

    public void set_beer1l(View view) {
        session.alcoholInGrams(1000,5);
        String str2 = new BigDecimal(session.bac(0))
           .setScale(3, BigDecimal.ROUND_HALF_UP)
           .toString();
        bac.setText(str2+" %");
    }



   public void set_drink330(View view) {
      session.alcoholInGrams(330,40);
      String str2 = new BigDecimal(session.bac(0))
         .setScale(3, BigDecimal.ROUND_HALF_UP)
         .toString();
      bac.setText(str2+" %");
   }

   public void set_drink250(View view) {
      session.alcoholInGrams(250,40);
      String str2 = new BigDecimal(session.bac(0))
         .setScale(3, BigDecimal.ROUND_HALF_UP)
         .toString();
      bac.setText(str2+" %");
   }

   public void set_drink125(View view) {
      session.alcoholInGrams(125,40);
      String str2 = new BigDecimal(session.bac(0))
         .setScale(3, BigDecimal.ROUND_HALF_UP)
         .toString();
      bac.setText(str2+" %");
   }



  /* public void show()
   {
      String[] nums = {"0","0.5","1","1.5","2","2.5","3","3.5","4","4.5","5","5.5","6","6.5","7","7.5","8","8.5","9","10","11","12"};
      final Dialog d = new Dialog(MainActivity2.this);
      d.setTitle("Time");
      d.setContentView(R.layout.dialog);
      Button b1 = (Button) d.findViewById(R.id.button1);
      Button b2 = (Button) d.findViewById(R.id.button2);
      final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
      np.setMaxValue(nums.length-1); // max value
      np.setMinValue(0);   // min value
      np.setWrapSelectorWheel(false);
      np.setDisplayedValues(nums);
      np.setOnValueChangedListener(this);
      b1.setOnClickListener(new View.OnClickListener()
      {
         @Override
         public void onClick(View v) {
            //tv.setText(String.valueOf(np.getValue())); //set the value to textview
            d.dismiss();
         }
      });
      b2.setOnClickListener(new View.OnClickListener()
      {
         @Override
         public void onClick(View v) {
            d.dismiss(); // dismiss the dialog
         }
      });
      d.show();


   }

   @Override
   public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

   }*/

   public void hour_dialog(){
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      LayoutInflater inflater = LayoutInflater.from(this);
      View theView = inflater.inflate(R.layout.picker, null);
//I define the dialog and I load the xml layout: number_picker_dialog.xml into the view

      final NumberPicker hours = (NumberPicker) theView.findViewById(R.id.euro_picker);

// I keep a reference to the 2 picker, in order to read their properties for later use

      builder.setView(theView)
         .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               Log.d("DBG","Price is: "+hours.getValue() );
               float dds = Float.parseFloat(String.valueOf(hours.getValue())) ;
            }
         })/*.setNegativeButton("No", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {

         }
      })*/;

      String[] nums = {"0","0.5","1","1.5","2","2.5","3","3.5","4","4.5","5","5.5","6","6.5","7","7.5","8","8.5","9","10","11","12"};
      hours.setMinValue(0);
      hours.setMaxValue(nums.length-1);
      hours.setDisplayedValues(nums);
      hours.setValue(0);


      builder.show();




   }
}

