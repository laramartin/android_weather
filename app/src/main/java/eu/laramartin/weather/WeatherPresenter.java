package eu.laramartin.weather;

import android.util.Log;

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
                for (Forecast forecast : response.body().getForecasts()) {
                    Log.v("MainActivity", "forecast temp: " + String.valueOf(forecast.getTemperature().getTempDay()));
                    Log.v("MainActivity", "forecast descript: " + forecast.getWeather().get(0).getDescription());
                    Log.v("MainActivity", "forecast humidity: " + String.valueOf(forecast.getHumidity()));
                }
                Log.v("Mainactivity", "day 1: " );
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                Log.e("Mainactivity", "error in ForecastResponse: " + t.getLocalizedMessage(), t);
            }
        });
        interactor.getWeather(location).enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                view.displayTemp(response.body().getMain().getTemperature());
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {

            }
        });
    }


}
