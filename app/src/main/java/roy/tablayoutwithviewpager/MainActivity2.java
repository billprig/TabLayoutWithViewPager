package roy.tablayoutwithviewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;


public class MainActivity2 extends AppCompatActivity {
    ViewPager  pager;
    TabLayout tabLayout;

    TextView bac;


    SeekBar custom_vol;
    SeekBar custom_alc;
    SessionManager session;
    CustomFragment fragment_custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        session = new SessionManager(getApplicationContext());


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

        fragment_custom = (CustomFragment) manager.findFragmentById(R.id.fragment_custom);
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
}

