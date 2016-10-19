package eu.laramartin.weather.data;

import java.util.List;

/**
 * Created by Lara on 12/10/2016.
 */

public class Forecast {

    List<Weather> weather;
    Main main;


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

}
