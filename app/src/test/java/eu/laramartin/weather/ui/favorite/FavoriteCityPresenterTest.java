package eu.laramartin.weather.ui.favorite;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import eu.laramartin.weather.business.WeatherInteractor;

/**
 * Created by Lara on 01/12/2016.
 */
@Ignore
public class FavoriteCityPresenterTest {

    private static final String MYLOCATION = "mylocation";
    FavoriteCityPresenter presenter;
    private WeatherInteractor interactor;
    private FavoriteCityView view;

    @Before
    public void setUp() throws Exception {
        presenter = new FavoriteCityPresenter(interactor);
        presenter.bind(view);
    }

    @Test
    public void performCall() throws Exception {
        presenter.performCall(MYLOCATION);
    }

}