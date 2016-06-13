package roy.tablayoutwithviewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by kundan on 10/16/2015.
 */
public class PagerAdapter  extends FragmentStatePagerAdapter{
    private Context _context;
    public PagerAdapter(FragmentManager fm, Context c) {
        super(fm);
        _context = c;
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
                title=_context.getResources().getString(R.string.drink_title);
                break;
            case 1:
                title=_context.getResources().getString(R.string.beer_title);
                break;
            case 2:
                title=_context.getResources().getString(R.string.wine_title);
                break;
            case 3:
                title=_context.getResources().getString(R.string.custom_title);
                break;
        }

        return title;
    }
}
