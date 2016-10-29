package eu.laramartin.weather.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lara on 19/10/2016.
 */

public class CurrentWeatherResponse {

    List<Weather> weather;
    Main main;
    Wind wind;
    @SerializedName("name")
    String city;
    @SerializedName("dt")
    long date;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
