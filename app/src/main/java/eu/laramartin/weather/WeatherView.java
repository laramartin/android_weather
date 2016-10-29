package eu.laramartin.weather;

/**
 * Created by Lara on 21/10/2016.
 */
public interface WeatherView {
    void displayTemp(int temperature);
    void displayDescription(String description);
    void displayHumidity(int humidity);
    void displayPressure(int pressure);
    void displayWind(double windSpeed);
    void displayForecast(int i, String dayOfWeek, int minTemp, int maxTemp);
    void displaycity(String city);
}
