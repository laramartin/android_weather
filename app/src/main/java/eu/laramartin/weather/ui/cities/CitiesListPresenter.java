package eu.laramartin.weather.ui.cities;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import eu.laramartin.weather.R;
import eu.laramartin.weather.api.model.CurrentWeatherResponse;
import eu.laramartin.weather.api.model.Forecast;
import eu.laramartin.weather.api.model.ForecastResponse;
import eu.laramartin.weather.business.WeatherInteractor;
import eu.laramartin.weather.business.db.CitiesContract;
import eu.laramartin.weather.business.db.CitiesDbHelper;
import eu.laramartin.weather.ui.common.WeatherIcons;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static eu.laramartin.weather.ui.common.DateUtils.getDayOfTheWeek;

/**
 * Created by Lara on 13/11/2016.
 */

public class CitiesListPresenter {

    private final static String LOG_TAG = CitiesListPresenter.class.getCanonicalName();
    private WeatherInteractor interactor;
    @Nullable
    CitiesListView view;
    private CitiesDbHelper dbHelper;
    @BindView(R.id.input_edit_text_add_city)
    EditText inputEditText;

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
                        cursor.getInt(0),
                        cursor.getString(cursor.getColumnIndex(CitiesContract.CitiesEntry.COLUMN_NAME)),
                        0));

                int id = cursor.getInt(0);
                String location = cursor.getString(cursor.getColumnIndex(CitiesContract.CitiesEntry.COLUMN_NAME));
                performCall(view, id, location);
            }
        }
    }

    public void performCall(final CitiesListView view, final int id, final String location) {
        Call<CurrentWeatherResponse> callCurrentWeather = interactor.getWeather(location);
        callCurrentWeather.enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                if (response.body() == null) {
                    Log.e(LOG_TAG, "error in ForecastResponse: body is null");
                    return;
                }
                int temperature = (int) response.body().getMain().getTemperature();

                view.updateItem(new CityCard(
                        R.drawable.sample,
                        id,
                        location,
                        temperature
                ));
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                Log.e(LOG_TAG, "error in ForecastResponse: " + t.getLocalizedMessage(), t);
            }
        });

        Call<ForecastResponse> callForecast = interactor.getForecasts(location);
        callForecast.enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                if (response.body() == null) {
                    return;
                }
//                int forecastIndex = 0;
                ForecastCard forecastCard = new ForecastCard();
                List<ForecastCard.ForecastCardItem> list = new ArrayList<ForecastCard.ForecastCardItem>();
                for (Forecast forecast : response.body().getForecasts()) {
                    ForecastCard.ForecastCardItem forecastCardItem = new ForecastCard.ForecastCardItem();

                    forecastCardItem.setDayOfTheWeek(getDayOfTheWeek(forecast.getDate()));
                    forecastCardItem.setTempMin((int) forecast.getTemperature().getTempMin());
                    forecastCardItem.setTempMax((int) forecast.getTemperature().getTempMax());
                    forecastCardItem.setIcon(WeatherIcons.getIcon(forecast.getWeather().get(0).getIcon()));
                    list.add(forecastCardItem);
                }
                forecastCard.setList(list);
                view.displayForecast(id, forecastCard);
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                Log.e(LOG_TAG, "error in ForecastResponse: " + t.getLocalizedMessage(), t);
            }
        });
    }

    public void addCity() {
        CityCard newCityCard = new CityCard(R.drawable.sample, 0, "london", 0);
        dbHelper.insertCity(newCityCard);
        view.addCityCard(newCityCard);
        Cursor cursor = dbHelper.readCities();
        while (cursor.moveToNext()) {
            Log.v(LOG_TAG, "id: " + cursor.getInt(0) + " city: " + cursor.getString(1));
        }
    }

    public void showInputCityDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        builder.setView(inflater.inflate(R.layout.layout_dialog_add_city, null));
        // TODO add cancel action
        builder.setNegativeButton(R.string.cancel, null);
        builder.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // TODO add accept action
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
