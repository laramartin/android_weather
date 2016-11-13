package eu.laramartin.weather.ui.cities;

/**
 * Created by Lara on 05/11/2016.
 */

public class CityCard {

    public int cityImageResourceId;
    public String cityName;
    public double temperature;


    public CityCard(int resourceId, String city, int temp) {
        this.cityImageResourceId = resourceId;
        this.cityName = city;
        this.temperature = temp;
    }

    public int getCityImageResourceId() {
        return cityImageResourceId;
    }

    public void setCityImageResourceId(int cityImageResourceId) {
        this.cityImageResourceId = cityImageResourceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
