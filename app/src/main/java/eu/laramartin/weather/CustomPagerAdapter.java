package eu.laramartin.weather;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lara on 30/10/2016.
 */
public class CustomPagerAdapter extends PagerAdapter {
    private Context context;

    public CustomPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View view = null;
        switch (position) {
            case 0:
                view = new FavoriteCityLayout(context);
                break;
            case 1:
                view = new CitiesListLayout(context);
                break;
            case 2:
                view = new PreferencesLayout(context);
                break;
        }
        collection.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
//        return mContext.getString(customPagerEnum.getTitleResId());
//    }
}
