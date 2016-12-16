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
    private static final String DEFAULT_UNITS_SYSTEM = "metric";
    SharedPreferences preferences;
    Context context;

    public Settings(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getFavName() {
        return preferences.getString(context.getString(R.string.favorite_city_name), DEFAULT_CITY_NAME);
    }

    public int getFavId() {
        return preferences.getInt(context.getString(R.string.favorite_city_id), DEFAULT_ID);
    }

    public void setFavNameAndId(String name, int id) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(context.getString(R.string.favorite_city_id), id);
        editor.putString(context.getString(R.string.favorite_city_name), name);
        editor.apply();
    }

    public void setAsUnitsSystem(String temperatureUnit) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(context.getString(R.string.temp_unit_shared_prefs), temperatureUnit);
        editor.apply();
    }

    public String getUnitsSystem() {
        return preferences.getString(
                context.getString(R.string.temp_unit_shared_prefs), DEFAULT_UNITS_SYSTEM);
    }
}
