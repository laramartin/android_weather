package eu.laramartin.weather.ui.favorite;

import eu.laramartin.weather.ui.common.TempFormat;

/**
 * Created by Lara on 21/10/2016.
 */
public interface FavoriteCityView {
    void displayCurrentTemp(int temperature, TempFormat tempFormat);

    void displayCurrentDescription(String description);

    void displayCurrentHumidity(int humidity);

    void displayCurrentPressure(int pressure);

    void displayCurrentWind(double windSpeed);

    void displayForecast(int i, String dayOfWeek, int minTemp, int maxTemp, int icon, TempFormat tempFormat);

    void displayCurrentCity(String city);

    void displayCurrentDate(String wholeDateOfCurrentWeather);

    void displayCurrentHour(String hourOfCurrentWeather);

    void setContentVisibility(boolean b);

    void setErrorVisibility(boolean b);

    void displayCurrentIcon(String icon);

    void displayCurrentSunriseSunsetTime(String sunriseTime, String sunsetTime);
}
