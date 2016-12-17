package eu.laramartin.weather.ui.cities;

/**
 * Created by Lara on 13/11/2016.
 */

public interface CitiesListView {

    void addCityCard(CityCard cityCard);

    void updateTemp(int id, String temp);

    void displayForecast(int id, ForecastCard forecastCard);

    void displayCityNotFound(String inputCity);

    void displayErrorServer();

    void clear();

    void setFavIcon(int cityId, boolean isFavorite);
}
