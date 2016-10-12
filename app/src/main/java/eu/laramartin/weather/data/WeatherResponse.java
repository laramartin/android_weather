package eu.laramartin.weather.data;

import java.util.List;

/**
 * Created by Lara on 12/10/2016.
 */

public class WeatherResponse {

    List<Weather> weather;
    Main main;

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
}
