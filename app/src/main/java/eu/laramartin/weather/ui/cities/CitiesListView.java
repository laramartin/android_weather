package eu.laramartin.weather.ui.cities;

import eu.laramartin.weather.ui.common.TempFormat;

/**
 * Created by Lara on 13/11/2016.
 */

public interface CitiesListView {

    void addCityCard(CityCard cityCard);

    void updateTemp(int id, int temp, TempFormat tempFormat);

    void displayForecast(int id, ForecastCard forecastCard);

    void displayCityNotFound(String inputCity);

    void displayErrorServer();

    void clear();

    void setFavIcon(int cityId, boolean isFavorite);
}
