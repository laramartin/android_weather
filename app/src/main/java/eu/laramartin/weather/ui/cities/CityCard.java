package eu.laramartin.weather.ui.cities;

/**
 * Created by Lara on 05/11/2016.
 */

public class CityCard {

    private int cityImageResourceId;
    private int id;
    private String cityName;
    private String temperature = "";
    private boolean isExpanded;
    private ForecastCard forecastCard;


    public CityCard(int resourceId, int id, String city, String temperature) {
        this.cityImageResourceId = resourceId;
        this.id = id;
        this.cityName = city;
        this.temperature = temperature;
    }

    public CityCard(int cityImageResourceId, int id, String cityName) {
        this.cityImageResourceId = cityImageResourceId;
        this.id = id;
        this.cityName = cityName;
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

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
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

    public ForecastCard getForecastCard() {
        return forecastCard;
    }

    public void setForecastCard(ForecastCard forecastCard) {
        this.forecastCard = forecastCard;
    }
}
