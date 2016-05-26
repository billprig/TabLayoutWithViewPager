package roy.tablayoutwithviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * Created by billprig on 23/05/16.
 */
public class CustomFragment  extends Fragment {

   private TextView posotita;
   private TextView Alcohol;
   private ImageButton imagePosotita;
   private View myFragmentView;

   public CustomFragment() {
      // Required empty public constructor
   }


   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      myFragmentView = inflater.inflate(R.layout.fragment_custom, container, false);
      posotita = (TextView) myFragmentView.findViewById(R.id.setVolume);
      Alcohol = (TextView) myFragmentView.findViewById(R.id.setAlcohol);
      imagePosotita = (ImageButton) myFragmentView.findViewById(R.id.bottle);

      // set PosotitaSeekBar's OnSeekBarChangeListener
      SeekBar seekBarPosotita =
         (SeekBar) myFragmentView.findViewById(R.id.seekBarPosotita);
      seekBarPosotita.setOnSeekBarChangeListener(PosotitaSeekBarListener);

      //set AlcoholSeekBar's OnSeekBarChangeListener
      SeekBar seekBarAlcohol =
         (SeekBar) myFragmentView.findViewById(R.id.seekBarAlcohol);
      seekBarAlcohol.setOnSeekBarChangeListener(AlcoholSeekBarListener);

      // Inflate the layout for this fragment
      return myFragmentView;

   }





   private void updateCustom(int progress)
   {
      //progress is the volume(ml) of the bottle

      posotita.setText("Volume(ml) : "+String.valueOf(progress));

      if(progress==0)imagePosotita.setBackgroundResource(R.drawable.waterbottle);
      else if (progress<=93) imagePosotita.setBackgroundResource(R.drawable.waterbottle62_5ml);
      else if (progress<=188 && progress>93) imagePosotita.setBackgroundResource(R.drawable.waterbottle125ml);
      else if (progress<=375 && progress>188)imagePosotita.setBackgroundResource(R.drawable.waterbottle250ml);
      else if (progress<=625 && progress>375)imagePosotita.setBackgroundResource(R.drawable.waterbottle500ml);
      else if (progress<=875 && progress>625)imagePosotita.setBackgroundResource(R.drawable.waterbottle750ml);
      else imagePosotita.setBackgroundResource(R.drawable.waterbottlefull);



   } // end method updateCustom

   //PosotikaSeekBar's OnSeekBarChangeListener
   private OnSeekBarChangeListener PosotitaSeekBarListener =
      new OnSeekBarChangeListener()
      {
         @Override
         public void onProgressChanged(SeekBar seekBar, int progress,
                                       boolean fromUser)
         {
            progress=10*progress;
            updateCustom(progress); // update the bottle image
         }
         // end method onProgressChanged

         @Override
         public void onStartTrackingTouch(SeekBar seekBar)
         {
         } // end method onStartTrackingTouch

         @Override
         public void onStopTrackingTouch(SeekBar seekBar)
         {
         } // end method onStopTrackingTouch
      }; // end OnSeekBarChangeListener

   //AlcoholSeekBar's OnSeekBarChangeListener
   private OnSeekBarChangeListener AlcoholSeekBarListener =
      new OnSeekBarChangeListener()
      {
         // update customPercent, then call updateCustom
         @Override
         public void onProgressChanged(SeekBar seekBar, int progress,
                                       boolean fromUser)
         {
            Alcohol.setText("Alcohol : "+String.valueOf(progress)+"%");
         } // end method onProgressChanged

         @Override
         public void onStartTrackingTouch(SeekBar seekBar)
         {
         } // end method onStartTrackingTouch

         @Override
         public void onStopTrackingTouch(SeekBar seekBar)
         {
         } // end method onStopTrackingTouch
      }; // end OnSeekBarChangeListener


}
