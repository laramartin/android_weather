package eu.laramartin.weather.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lara on 19/10/2016.
 */

public class ForecastResponse {

    @SerializedName("list")
    List<Forecast> forecasts;
    City city;

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
