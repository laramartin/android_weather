package eu.laramartin.weather.ui.favorite;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import eu.laramartin.weather.api.model.ForecastResponse;
import eu.laramartin.weather.business.WeatherInteractor;

import static org.mockito.Mockito.verify;

/**
 * Created by Lara on 01/12/2016.
 */
@Ignore
public class FavoriteCityPresenterTest {

    private static final String MYLOCATION = "mylocation";
    FavoriteCityPresenter presenter;
    @Mock
    private WeatherInteractor interactor;
    @Mock
    private FavoriteCityView view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new FavoriteCityPresenter(interactor);
        presenter.bind(view);
    }

    @Test
    public void displayForecastNull() throws Exception {
        ForecastResponse response = null;
        presenter.displayForecast(response);
        verify(view).setErrorVisibility(true);
    }

}