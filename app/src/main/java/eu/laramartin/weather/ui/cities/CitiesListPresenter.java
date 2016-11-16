package eu.laramartin.weather.ui.cities;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.util.Log;

import eu.laramartin.weather.R;
import eu.laramartin.weather.api.model.CurrentWeatherResponse;
import eu.laramartin.weather.business.WeatherInteractor;
import eu.laramartin.weather.business.db.CitiesContract;
import eu.laramartin.weather.business.db.CitiesDbHelper;
import eu.laramartin.weather.ui.common.TextUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
//                view.addCityCard(new CityCard(R.drawable.sample,
//                        cursor.getString(cursor.getColumnIndex(CitiesContract.CitiesEntry.COLUMN_NAME)),
////                        cursor.getInt(cursor.getColumnIndex(CitiesContract.CitiesEntry.COLUMN_CURRENT_TEMP))
//                        0));

                Call<CurrentWeatherResponse> currentWeatherResponse =
                        interactor.getWeather(
                                cursor.getString(
                                        cursor.getColumnIndex(CitiesContract.CitiesEntry.COLUMN_NAME)));
                currentWeatherResponse.enqueue(new Callback<CurrentWeatherResponse>() {

                    @Override
                    public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
//                        view.addCityCard(new CityCard(R.drawable.sample,
//                                cursor.getString(cursor.getColumnIndex(CitiesContract.CitiesEntry.COLUMN_NAME)),
//                                (int) response.body().getMain().getTemperature()));
                        view.displayCurrentCityName(
                                TextUtils.setFirstCharInUppercase(
                                        response.body().getCity()));
                        view.displayCurrentTemp((int) response.body().getMain().getTemperature());
                    }

                    @Override
                    public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                        Log.e(LOG_TAG, "error in CurrentWeatherResponse: " + t.getLocalizedMessage(), t);
                    }
                });

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
