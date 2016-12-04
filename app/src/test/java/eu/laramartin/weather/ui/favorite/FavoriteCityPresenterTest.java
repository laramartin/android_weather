package eu.laramartin.weather.ui.favorite;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import eu.laramartin.weather.business.WeatherInteractor;
import eu.laramartin.weather.ui.preferences.Settings;

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
    @Mock
    private Settings settings;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new FavoriteCityPresenter(interactor, settings);
        presenter.bind(view);
    }

    @Test
    public void showErrorWhenForecastResponseFailed() throws Exception {
        presenter.displayForecast(null);
        verify(view).setErrorVisibility(true);
    }

}