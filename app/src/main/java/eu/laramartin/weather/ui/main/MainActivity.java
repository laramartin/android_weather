package eu.laramartin.weather.ui.main;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.laramartin.weather.R;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getCanonicalName();
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        // TODO create sharedpreferences file
        PreferenceManager.getDefaultSharedPreferences(this);
        viewPager.setAdapter(new CustomPagerAdapter(this));
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorite_city:
                                Log.v(LOG_TAG, "favorite city");
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.action_select_cities:
                                Log.v(LOG_TAG, "select cities");
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.action_settings:
                                Log.v(LOG_TAG, "settings");
                                viewPager.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int menuItemId;
                switch (position) {
                    default:
                    case 0:
                        menuItemId = R.id.action_favorite_city;
                        break;
                    case 1:
                        menuItemId = R.id.action_select_cities;
                        break;
                    case 2:
                        menuItemId = R.id.action_settings;
                }
                View view = bottomNavigationView.findViewById(menuItemId);
                view.performClick();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
