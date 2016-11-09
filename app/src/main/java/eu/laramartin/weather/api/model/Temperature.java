package eu.laramartin.weather.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lara on 20/10/2016.
 */
public class Temperature {

    @SerializedName("day")
    double tempDay;
    @SerializedName("min")
    double tempMin;
    @SerializedName("max")
    double tempMax;

    public double getTempDay() {
        return tempDay;
    }

    public void setTempDay(double tempDay) {
        this.tempDay = tempDay;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "tempDay=" + tempDay +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                '}';
    }
}
