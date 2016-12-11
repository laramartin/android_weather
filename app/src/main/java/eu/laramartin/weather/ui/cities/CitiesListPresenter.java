package eu.laramartin.weather.ui.cities;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import eu.laramartin.weather.R;
import eu.laramartin.weather.api.model.CurrentWeatherResponse;
import eu.laramartin.weather.api.model.Forecast;
import eu.laramartin.weather.api.model.ForecastResponse;
import eu.laramartin.weather.business.WeatherInteractor;
import eu.laramartin.weather.business.db.CitiesContract;
import eu.laramartin.weather.business.db.CitiesDbHelper;
import eu.laramartin.weather.ui.common.WeatherIcons;
import eu.laramartin.weather.ui.preferences.Settings;
import eu.laramartin.weather.ui.preferences.SettingsChangedEvent;
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
    private Settings settings;

    public CitiesListPresenter(WeatherInteractor interactor, CitiesDbHelper dbHelper, Settings settings) {
        this.interactor = interactor;
        this.dbHelper = dbHelper;
        this.settings = settings;
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
                        cursor.getString(cursor.getColumnIndex(CitiesContract.CitiesEntry.COLUMN_NAME))));
                int id = cursor.getInt(0);
                String location = cursor.getString(cursor.getColumnIndex(CitiesContract.CitiesEntry.COLUMN_NAME));
                performCall(view, id, location, settings.getTempUnit());
            }
        }
    }

    public void performCall(final CitiesListView view, final int id, final String location, String tempUnits) {
        Call<CurrentWeatherResponse> callCurrentWeather = interactor.getWeather(location, tempUnits);
        callCurrentWeather.enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                displayForecast(response, view, id, location);
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                Log.e(LOG_TAG, "error in CurrentWeatherResponse: " + t.getLocalizedMessage(), t);
            }
        });

        Call<ForecastResponse> callForecast = interactor.getForecasts(location, tempUnits);
        callForecast.enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                displayCurrentWeather(response, view, id);
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                Log.e(LOG_TAG, "error in ForecastResponse: " + t.getLocalizedMessage(), t);
            }
        });
    }

    private void displayCurrentWeather(Response<ForecastResponse> response, CitiesListView view, int id) {
        if (response.body() == null) {
            Log.v(LOG_TAG, "error code: " + response.code());
            if (response.code() == 502) {
                // TODO show message in dialog with invalid input
            }
            return;
        }
        ForecastCard forecastCard = new ForecastCard();
        List<ForecastCard.ForecastCardItem> list = new ArrayList<>();
        for (Forecast forecast : response.body().getForecasts()) {
            ForecastCard.ForecastCardItem forecastCardItem = new ForecastCard.ForecastCardItem();

            forecastCardItem.setDayOfTheWeek(getDayOfTheWeek(forecast.getDate()));
            forecastCardItem.setTempMin((int) forecast.getTemperature().getTempMin());
            forecastCardItem.setTempMax((int) forecast.getTemperature().getTempMax());
            forecastCardItem.setIcon(WeatherIcons.getIcon(forecast.getWeather().get(0).getIcon()));
            list.add(forecastCardItem);
//            Log.v(LOG_TAG, "city forecast: " + response.body().getCity());
//            Log.v(LOG_TAG, "day: " + getDayOfTheWeek(forecast.getDate()));
//            Log.v(LOG_TAG, "max temp: " + (int) forecast.getTemperature().getTempMax());
        }
        forecastCard.setList(list);
        view.displayForecast(id, forecastCard);
    }

    private void displayForecast(Response<CurrentWeatherResponse> response, CitiesListView view, int id, String location) {
        if (response.body() == null) {
            Log.e(LOG_TAG, "error in ForecastResponse: body is null");
            return;
        }
        String temperature = String.format(Locale.US, "%.0f °C", response.body().getMain().getTemperature());
        view.updateItem(new CityCard(
                R.drawable.sample,
                id,
                location,
                temperature
        ));
        Log.v(LOG_TAG, "current weather city: " + response.body().getCity());
    }

    public void addCityIfExists(final String inputCity) {
        Call<CurrentWeatherResponse> callCurrentWeather = interactor.getWeather(inputCity, settings.getTempUnit());
        callCurrentWeather.enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                if (response.body() == null) {
                    Log.v(LOG_TAG, "error code: " + response.code());
                    if (view != null) {
                        view.displayCityNotFound(inputCity);
                    }
                    return;
                }
                if (response.body().getCity().equalsIgnoreCase(inputCity)) {
                    storeCity(inputCity);
                } else {
                    if (view != null) {
                        view.displayCityNotFound(inputCity);
                    }
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                if (view != null) {
                    view.displayErrorServer();
                }
            }
        });
    }

    private void storeCity(String inputCity) {
        dbHelper.insertCity(inputCity);
        if (view != null) {
            view.clear();
        }
        loadData();
    }

    public void deleteItem(int itemId) {
        if (dbHelper.delete(itemId) == 1) {
            Log.v(LOG_TAG, "deleted");
        } else {
            Log.e(LOG_TAG, "Error deleting");
        }
        if (view != null) {
            view.clear();
        }
        loadData();
    }

    public void setAsFavoriteCityInCityCard(CityCard cityCard) {
        settings.setFavNameAndId(cityCard.getCityName(), cityCard.getId());
        setFavoriteCity(cityCard);
    }

    public boolean isFavoriteCity(CityCard cityCard) {
        return cityCard.getId() == settings.getFavId();
    }

    public int getIdPreviousFavoriteCity() {
        return settings.getFavId();
    }

    public void clickedFavorite(CityCard cityCard) {
        if (!cityCard.isFavorite()){
            if (view != null) {
                view.setFavIcon(getIdPreviousFavoriteCity(), false);
                view.setFavIcon(cityCard.getId(), true);
            }
            setAsFavoriteCityInCityCard(cityCard);
            EventBus.getDefault().post(new SettingsChangedEvent());
        }
    }

    private void setFavoriteCity(CityCard cityCard) {
        cityCard.setFavorite(true);
    }

    public void setAsNotFavoriteCity(CityCard cityCard) {
        cityCard.setFavorite(false);
    }

    public int getFavoriteIcon(CityCard cityCard) {
        if (cityCard.isFavorite()) {
            return R.drawable.ic_favorite_black_24dp;
        } else {
            return R.drawable.ic_favorite_border_black_24dp;
        }
    }
}
