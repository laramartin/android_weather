package eu.laramartin.weather.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lara on 12/10/2016.
 */

public interface WeatherService {

    @GET("weather")
    Call<CurrentWeatherResponse> getWeather(@Query("q") String location,
                                            @Query("appid") String appid,
                                            @Query("units") String unit);

    @GET("forecast/daily")
    Call<ForecastResponse> getForecasts(@Query("q") String location,
                                        @Query("appid") String appid,
                                        @Query("units") String unit,
                                        @Query("cnt") int numberOfDays);
}
