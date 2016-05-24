package roy.tablayoutwithviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by kundan on 10/16/2015.
 */
public class PagerAdapter  extends FragmentStatePagerAdapter{
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag=null;
        switch (position){
            case 0:
                frag=new DrinksFragment();
                break;
            case 1:
                frag=new BeersFragment();
                break;
            case 2:
                frag=new WineFragment();
                break;
            case 3:
                frag=new CustomFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=" ";
        switch (position){
            case 0:
                title="Drinks";
                break;
            case 1:
                title="Beers";
                break;
            case 2:
                title="Wine";
                break;
            case 3:
                title="Custom";
                break;
        }

        return title;
    }
}
