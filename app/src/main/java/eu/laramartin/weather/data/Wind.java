package eu.laramartin.weather.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lara on 23/10/2016.
 */

public class Wind {

    @SerializedName("speed")
    double windSpeed;
    @SerializedName("deg")
    double windDegrees;

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDegrees() {
        return windDegrees;
    }

    public void setWindDegrees(double windDegrees) {
        this.windDegrees = windDegrees;
    }
}
