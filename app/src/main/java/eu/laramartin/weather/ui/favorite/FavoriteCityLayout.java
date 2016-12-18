package eu.laramartin.weather.ui.favorite;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import eu.laramartin.weather.BuildConfig;
import eu.laramartin.weather.R;
import eu.laramartin.weather.business.WeatherInteractorImpl;
import eu.laramartin.weather.ui.common.ForecastView;
import eu.laramartin.weather.ui.common.TempFormat;
import eu.laramartin.weather.ui.common.WeatherIcons;
import eu.laramartin.weather.ui.events.FavCityChangedEvent;
import eu.laramartin.weather.ui.preferences.Settings;
import eu.laramartin.weather.ui.events.SettingsChangedEvent;

public class FavoriteCityLayout extends FrameLayout implements FavoriteCityView, SwipeRefreshLayout.OnRefreshListener {

    private static final String LOG_TAG = FavoriteCityLayout.class.getCanonicalName();
    FavoriteCityPresenter presenter;

    @BindView(R.id.city_text_view)
    TextView cityTextView;
    @BindView(R.id.temperature_TextView)
    TextView temperatureTextView;
    @BindView(R.id.description_TextView)
    TextView descriptionTextView;
    @BindView(R.id.humidity_TextView)
    TextView humidityTextView;
    @BindView(R.id.pressure_TextView)
    TextView pressureTextView;
    @BindView(R.id.wind_TextView)
    TextView windTextView;
    @BindViews({R.id.forecast_1, R.id.forecast_2, R.id.forecast_3, R.id.forecast_4, R.id.forecast_5})
    List<ForecastView> forecastViews;
    @BindView(R.id.current_date)
    TextView currentDateTextView;
    @BindView(R.id.current_hour)
    TextView currentHourTextView;
    @BindView(R.id.content_layout)
    RelativeLayout contentLayout;
    @BindView(R.id.loading_layout)
    LinearLayout loadingLayout;
    @BindView(R.id.error_layout)
    LinearLayout errorLayout;
    @BindView(R.id.current_icon)
    ImageView currentIcon;
    @BindView(R.id.sunrise_sunset_text_view)
    TextView sunriseSunsetTextView;
    @BindView(R.id.swipe_container_favourite)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.temp_unit_fav_city_text_view)
    TextView tempUnitFavCityTextView;

    public FavoriteCityLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = inflate(context, R.layout.layout_favorite, this);
        ButterKnife.bind(this, view);
        presenter = new FavoriteCityPresenter(new WeatherInteractorImpl(BuildConfig.API_KEY), new Settings(context));
        presenter.bind(this);
        presenter.performCall();
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSettingsChangedEventReceived(SettingsChangedEvent settingsChangedEvent) {
        presenter.performCall();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFavCityChangedEventReceived(FavCityChangedEvent favCityChangedEvent) {
        presenter.performCall();
    }

    @Override
    public void displayCurrentCity(String city) {
        cityTextView.setText(city);
    }

    @Override
    public void displayCurrentDate(String wholeDateOfCurrentWeather) {
        currentDateTextView.setText(wholeDateOfCurrentWeather);
    }

    @Override
    public void displayCurrentHour(String hourOfCurrentWeather) {
        currentHourTextView.setText(hourOfCurrentWeather);
    }

    @Override
    public void setContentVisibility(boolean b) {
        errorLayout.setVisibility(b ? View.INVISIBLE : View.VISIBLE);
        contentLayout.setVisibility(b ? View.VISIBLE : View.INVISIBLE);
        loadingLayout.setVisibility(b ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void setErrorVisibility(boolean b) {
        errorLayout.setVisibility(b ? View.VISIBLE : View.INVISIBLE);
        contentLayout.setVisibility(b ? View.INVISIBLE : View.VISIBLE);
        loadingLayout.setVisibility(b ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void displayCurrentTemp(int temp, TempFormat tempFormat) {
        temperatureTextView.setText(String.valueOf(temp));
        int resString = R.string.unit_degree_celsius;
        switch (tempFormat) {
            case CELSIUS:
                resString = R.string.unit_degree_celsius;
                break;
            case FAHRENHEIT:
                resString = R.string.unit_degree_fahrenheit;
                break;
        }
        tempUnitFavCityTextView.setText(resString);
    }

    @Override
    public void displayCurrentDescription(String description) {
        descriptionTextView.setText(description);
    }

    @Override
    public void displayCurrentHumidity(int humidity) {
        humidityTextView.setText(getContext().getString(R.string.humidity, humidity));
    }

    @Override
    public void displayCurrentPressure(int pressure) {
        pressureTextView.setText(getContext().getString(R.string.pressure, pressure));
    }

    @Override
    public void displayCurrentWind(double windSpeed, TempFormat tempFormat) {
        int resString = R.string.wind_metric_unit;
        switch (tempFormat) {
            case CELSIUS:
                resString = R.string.wind_metric_unit;
                break;
            case FAHRENHEIT:
                resString = R.string.wind_imperial_unit;
                break;
        }
        windTextView.setText(getContext().getString(R.string.wind, windSpeed,
                getContext().getString(resString)));
    }

    @Override
    public void displayForecast(int i, String dayOfWeek, int minTemp, int maxTemp, int iconId, TempFormat tempFormat) {
        ForecastView currentForecastView = forecastViews.get(i);
        currentForecastView.dayWeekTextView.setText(dayOfWeek);
        int resString = R.string.max_min_temp_celsius;
        switch (tempFormat) {
            case CELSIUS:
                resString = R.string.max_min_temp_celsius;
                break;
            case FAHRENHEIT:
                resString = R.string.max_min_temp_fahrenheit;
                break;
        }
        currentForecastView.tempTextView.setText(getContext().getString(resString, minTemp, maxTemp));
        currentForecastView.iconImageView.setImageResource(iconId);
    }

    @Override
    public void displayCurrentIcon(String icon) {
        currentIcon.setImageResource(WeatherIcons.getIcon(icon));
    }

    @Override
    public void displayCurrentSunriseSunsetTime(String sunriseTime, String sunsetTime) {
        sunriseSunsetTextView.setText(getContext().getString(R.string.sunrise_sunset_time,
                sunriseTime, sunsetTime));
    }

    @Override
    public void onRefresh() {
        presenter.performCall();
        swipeRefreshLayout.setRefreshing(false);
    }
}
