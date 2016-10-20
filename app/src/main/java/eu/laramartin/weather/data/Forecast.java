package eu.laramartin.weather.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lara on 12/10/2016.
 */

public class Forecast {

    List<Weather> weather;
    double humidity;

    @SerializedName("temp")
    Temperature temperature;

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "weather=" + weather +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                '}';
    }
}
