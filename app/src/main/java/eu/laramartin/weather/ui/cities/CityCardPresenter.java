package eu.laramartin.weather.ui.cities;

import android.support.annotation.Nullable;

import eu.laramartin.weather.R;
import eu.laramartin.weather.business.WeatherInteractor;

/**
 * Created by Lara on 13/11/2016.
 */

public class CityCardPresenter {

    private final static String LOG_TAG = CityCardPresenter.class.getCanonicalName();
    private WeatherInteractor interactor;
    @Nullable
    CitiesListView view;

    public CityCardPresenter(WeatherInteractor interactor) {
        this.interactor = interactor;
    }

    public void bind(CitiesListView view) {
        this.view = view;
    }

    public void unbind() {
        view = null;
    }

    public void loadData() {
        if (view != null) {
            view.addCityCard(new CityCard(R.drawable.sample,
                    "Berlin",
                    5));
            view.addCityCard(new CityCard(R.drawable.sample,
                    "Berlin",
                    5));
            view.addCityCard(new CityCard(R.drawable.sample,
                    "Berlin",
                    5));
            view.addCityCard(new CityCard(R.drawable.sample,
                    "Berlin",
                    5));
        }
    }
}
