package eu.laramartin.weather.data;

import java.util.List;

/**
 * Created by Lara on 19/10/2016.
 */

public class CurrentWeatherResponse {

    List<Weather> weather;
    Main main;
    Wind wind;
    String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
