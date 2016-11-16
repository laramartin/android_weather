package eu.laramartin.weather.ui.cities;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.util.Log;

import eu.laramartin.weather.R;
import eu.laramartin.weather.business.WeatherInteractor;
import eu.laramartin.weather.business.db.CitiesContract;
import eu.laramartin.weather.business.db.CitiesDbHelper;

/**
 * Created by Lara on 13/11/2016.
 */

public class CitiesListPresenter {

    private final static String LOG_TAG = CitiesListPresenter.class.getCanonicalName();
    private WeatherInteractor interactor;
    @Nullable
    CitiesListView view;
    private CitiesDbHelper dbHelper;

    public CitiesListPresenter(WeatherInteractor interactor, CitiesDbHelper dbHelper) {
        this.interactor = interactor;
        this.dbHelper = dbHelper;
    }

    public void bind(CitiesListView view) {
        this.view = view;
    }

    public void unbind() {
        view = null;
    }

    public void loadData() {
        if (view != null) {
            final Cursor cursor = dbHelper.readCities();
            while (cursor.moveToNext()) {
                Log.v(LOG_TAG, "id: " + cursor.getInt(0) + " city: " + cursor.getString(1));
                view.addCityCard(new CityCard(R.drawable.sample,
                        cursor.getString(cursor.getColumnIndex(CitiesContract.CitiesEntry.COLUMN_NAME)),
                        0));
            }
        }
    }

    public void addCity() {
        CityCard newCityCard = new CityCard(R.drawable.sample, "london", 0);
        dbHelper.insertCity(newCityCard);
        view.addCityCard(newCityCard);
        Cursor cursor = dbHelper.readCities();
        while (cursor.moveToNext()) {
            Log.v(LOG_TAG, "id: " + cursor.getInt(0) + " city: " + cursor.getString(1));
        }

    }
}
