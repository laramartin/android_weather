package eu.laramartin.weather;

/**
 * Created by Lara on 21/10/2016.
 */
public interface WeatherView {
    void displayCurrentTemp(int temperature);

    void displayCurrentDescription(String description);

    void displayCurrentHumidity(int humidity);

    void displayCurrentPressure(int pressure);

    void displayCurrentWind(double windSpeed);

    void displayForecast(int i, String dayOfWeek, int minTemp, int maxTemp, int icon);

    void displayCurrentCity(String city);

    void displayCurrentDate(String wholeDateOfCurrentWeather);

    void displayCurrentHour(String hourOfCurrentWeather);

    void setContentVisibility(boolean b);

    void setErrorVisibility(boolean b);

    void displayCurrentIcon(String icon);
}
