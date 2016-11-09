package eu.laramartin.weather.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lara on 12/10/2016.
 */

public class Main {

    @SerializedName("temp")
    double temperature;
    double humidity;
    double pressure;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
}
