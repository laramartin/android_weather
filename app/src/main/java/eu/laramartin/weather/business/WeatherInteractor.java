package eu.laramartin.weather.business;

import eu.laramartin.weather.api.model.CurrentWeatherResponse;
import eu.laramartin.weather.api.model.ForecastResponse;
import retrofit2.Call;

/**
 * Created by Lara on 21/10/2016.
 */

public interface WeatherInteractor {
    Call<ForecastResponse> getForecasts(String location);

    Call<CurrentWeatherResponse> getWeather(String location);
}
