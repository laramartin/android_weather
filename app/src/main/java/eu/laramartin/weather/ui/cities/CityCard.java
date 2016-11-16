package eu.laramartin.weather.ui.cities;

/**
 * Created by Lara on 05/11/2016.
 */

public class CityCard {

    public int cityImageResourceId;
    private int id;
    public String cityName;
    public int temperature;
    private boolean isExpanded;


    public CityCard(int resourceId, int id, String city, int temperature) {
        this.cityImageResourceId = resourceId;
        this.id = id;
        this.cityName = city;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
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

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean getIsExpanded() {
        return isExpanded;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean isExpanded) {
        this.isExpanded = isExpanded;
    }
}
