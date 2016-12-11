package eu.laramartin.weather.ui.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import eu.laramartin.weather.R;

/**
 * Created by Lara on 03/12/2016.
 */

public class Settings {

    private static final int DEFAULT_ID = 0;
    private static final String DEFAULT_CITY_NAME = "berlin";
    SharedPreferences preferences;
    Context context;

    public Settings(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getFavName() {
        return preferences.getString(context.getString(R.string.favoriteCityName), DEFAULT_CITY_NAME);
    }

    public int getFavId() {
        return preferences.getInt(context.getString(R.string.favoriteCityId), DEFAULT_ID);
    }

    public void setFavNameAndId(String name, int id) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(context.getString(R.string.favoriteCityId), id);
        editor.putString(context.getString(R.string.favoriteCityName), name);
        editor.apply();
    }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(context.getString(R.string.temp_unit_shared_prefs), temperatureUnit);
        editor.apply();
    }

        return preferences.getString(
    }
}
