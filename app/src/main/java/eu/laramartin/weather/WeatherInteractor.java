package eu.laramartin.weather;

import eu.laramartin.weather.data.CurrentWeatherResponse;
import eu.laramartin.weather.data.ForecastResponse;
import retrofit2.Call;

/**
 * Created by Lara on 21/10/2016.
 */

public interface WeatherInteractor {
    Call<ForecastResponse> getForecasts(String location);
    Call<CurrentWeatherResponse> getWeather(String location);
}
