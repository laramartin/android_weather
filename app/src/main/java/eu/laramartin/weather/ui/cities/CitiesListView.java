package eu.laramartin.weather.ui.cities;

/**
 * Created by Lara on 13/11/2016.
 */

public interface CitiesListView {

    void addCityCard(CityCard cityCard);

    void displayCurrentTemp(int temperature);

    void displayCurrentCityName(String city);
}
