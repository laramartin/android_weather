package eu.laramartin.weather;

import eu.laramartin.weather.data.CurrentWeatherResponse;
import eu.laramartin.weather.data.ForecastResponse;
import eu.laramartin.weather.data.WeatherService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lara on 21/10/2016.
 */

public class WeatherInteractorImpl implements WeatherInteractor {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private WeatherService weatherService;
    private String appid;
    private String units = "metric";
    private int numberOfDays = 5;

    public WeatherInteractorImpl(String appid) {
        this.appid = appid;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherService = retrofit.create(WeatherService.class);
    }

    @Override
    public Call<ForecastResponse> getForecasts(String location) {
        return weatherService.getForecasts(location, appid, units, numberOfDays);
    }

    @Override
    public Call<CurrentWeatherResponse> getWeather(String location) {
        return weatherService.getWeather(location, appid, units);
    }
}
