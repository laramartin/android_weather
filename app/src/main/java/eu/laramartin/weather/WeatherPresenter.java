package eu.laramartin.weather;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import eu.laramartin.weather.data.CurrentWeatherResponse;
import eu.laramartin.weather.data.Forecast;
import eu.laramartin.weather.data.ForecastResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lara on 21/10/2016.
 */

public class WeatherPresenter {

    private final static String LOG_TAG = WeatherPresenter.class.getCanonicalName();
    private WeatherInteractor interactor;
    WeatherView view;

    public WeatherPresenter(WeatherInteractor interactor) {
        this.interactor = interactor;
    }

    public void bind(WeatherView view) {
        this.view = view;
    }

    public void unbind() {
        view = null;
    }

    public void performCall(String location) {
        Call<ForecastResponse> callForecast = interactor.getForecasts(location);
        callForecast.enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                int i = 0;
                for (Forecast forecast : response.body().getForecasts()) {
//                    Log.v("WeatherPresenter", forecast.toString());
//                    Log.v("WeatherPresenter", String.valueOf(forecast.getDate()));
//                    Log.v("WeatherPresenter", "min: " + String.valueOf(forecast.getTemperature().getTempMin()));
//                    Log.v("WeatherPresenter", "max: " + String.valueOf(forecast.getTemperature().getTempMax()));
                    view.displayForecast(i, getDayOfTheWeek(forecast.getDate()),
                            (int) forecast.getTemperature().getTempMin(),
                            (int) forecast.getTemperature().getTempMax());
                    i++;
                }
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                Log.e(LOG_TAG, "error in ForecastResponse: " + t.getLocalizedMessage(), t);
            }
        });
        interactor.getWeather(location).enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                view.displayCurrentDate(getWholeDateOfCurrentWeather(response.body().getDate()));
                view.displayCurrentHour(getHourOfCurrentWeather(response.body().getDate()));
                view.displayTemp((int) response.body().getMain().getTemperature());
                view.displayDescription(response.body().getWeather().get(0).getDescription());
                view.displayHumidity((int) response.body().getMain().getHumidity());
                view.displayPressure((int) response.body().getMain().getPressure());
                view.displayWind(response.body().getWind().getWindSpeed());
                view.displayCity(response.body().getCity());
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                Log.e(LOG_TAG, "error in CurrentWeatherResponse: " + t.getLocalizedMessage(), t);
            }
        });
    }

    private String getDayOfTheWeek(long unixTime) {
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat("EEE");
        return formatter.format(date);
    }

    private String getWholeDateOfCurrentWeather(long unixTime) {
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM d");
        return formatter.format(date);
    }

    private String getHourOfCurrentWeather(long unixTime) {
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }
}
