package eu.laramartin.weather.ui.favorite;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import eu.laramartin.weather.api.model.CurrentWeatherResponse;
import eu.laramartin.weather.api.model.Forecast;
import eu.laramartin.weather.api.model.ForecastResponse;
import eu.laramartin.weather.business.WeatherInteractor;
import eu.laramartin.weather.ui.common.DateUtils;
import eu.laramartin.weather.ui.common.TempFormat;
import eu.laramartin.weather.ui.common.TextUtils;
import eu.laramartin.weather.ui.common.WeatherIcons;
import eu.laramartin.weather.ui.preferences.Settings;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static eu.laramartin.weather.ui.common.DateUtils.getHourFromUnixTime;

/**
 * Created by Lara on 21/10/2016.
 */

public class FavoriteCityPresenter {

    private final static String LOG_TAG = FavoriteCityPresenter.class.getCanonicalName();
    private WeatherInteractor interactor;
    FavoriteCityView view;
    private Settings settings;

    public FavoriteCityPresenter(WeatherInteractor interactor, Settings settings) {
        this.interactor = interactor;
        this.settings = settings;
    }

    public void bind(FavoriteCityView view) {
        this.view = view;
    }

    public void unbind() {
        view = null;
    }

    public void performCall(String location, String tempUnits) {
        Call<ForecastResponse> callForecast = interactor.getForecasts(location, tempUnits);
        callForecast.enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                displayForecast(response.body());
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                view.setErrorVisibility(true);
                Log.e(LOG_TAG, "error in ForecastResponse: " + t.getLocalizedMessage(), t);
            }
        });
        interactor.getWeather(location, tempUnits).enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                displayCurrentWeather(response.body());
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                view.setErrorVisibility(true);
                Log.e(LOG_TAG, "error in CurrentWeatherResponse: " + t.getLocalizedMessage(), t);
            }
        });
    }

    @VisibleForTesting
    void displayCurrentWeather(CurrentWeatherResponse response) {
        if (response == null) {
            view.setErrorVisibility(true);
            return;
        }
        view.setContentVisibility(true);
        view.displayCurrentDate(DateUtils.getWholeDateOfCurrentWeather(response.getDate()));
        view.displayCurrentHour(getHourFromUnixTime(response.getDate()));
        view.displayCurrentTemp((int) response.getMain().getTemperature(), getTempFormat());
        view.displayCurrentDescription(
                TextUtils.setFirstCharInUppercase(
                        response.getWeather().get(0).getDescription()));
        view.displayCurrentHumidity((int) response.getMain().getHumidity());
        view.displayCurrentPressure((int) response.getMain().getPressure());
        view.displayCurrentWind(response.getWind().getWindSpeed());
        view.displayCurrentCity(
                TextUtils.setFirstCharInUppercase(
                        response.getCity()));
        view.displayCurrentIcon(response.getWeather().get(0).getIcon());
        view.displayCurrentSunriseSunsetTime(
                getHourFromUnixTime(response.getWeatherSys().getSunrise()),
                getHourFromUnixTime(response.getWeatherSys().getSunset()));
    }

    @VisibleForTesting
    void displayForecast(@Nullable ForecastResponse response) {
        if (response == null) {
            view.setErrorVisibility(true);
            return;
        }
        int i = 0;
        for (Forecast forecast : response.getForecasts()) {
            view.displayForecast(i, DateUtils.getDayOfTheWeek(forecast.getDate()),
                    (int) forecast.getTemperature().getTempMin(),
                    (int) forecast.getTemperature().getTempMax(),
                    WeatherIcons.getIcon(forecast.getWeather().get(0).getIcon()),
                    getTempFormat());
            i++;
        }
    }

    private TempFormat getTempFormat() {
        if (settings.getUnitsSystem().equalsIgnoreCase("metric")) {
            return TempFormat.CELSIUS;
        }
        return TempFormat.FAHRENHEIT;
    }

    public void performCall() {
        Log.v(LOG_TAG, "temp units: " + settings.getUnitsSystem());
        performCall(settings.getFavName(), settings.getUnitsSystem());
    }
}
