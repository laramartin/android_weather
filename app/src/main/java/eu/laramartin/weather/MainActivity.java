package eu.laramartin.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements WeatherView {


    private static final String LOG_TAG = MainActivity.class.getCanonicalName();
    WeatherPresenter presenter;

    @BindView(R.id.city_TextView)
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
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new WeatherPresenter(new WeatherInteractorImpl(BuildConfig.API_KEY));
        presenter.bind(this);
        ButterKnife.bind(this);
        presenter.performCall("ripollet");

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorite_city:
                                Log.v(LOG_TAG, "favorite city");
                                break;
                            case R.id.action_select_cities:
                                Log.v(LOG_TAG, "select cities");
                                break;
                            case R.id.action_settings:
                                Log.v(LOG_TAG, "settings");
                                break;
                        }
                        return false;
                    }
                });
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
    public void displayCurrentTemp(int temp) {
        temperatureTextView.setText(String.valueOf(temp));
    }

    @Override
    public void displayCurrentDescription(String description) {
        descriptionTextView.setText(description);
    }

    @Override
    public void displayCurrentHumidity(int humidity) {
        humidityTextView.setText(getString(R.string.humidity, humidity));
    }

    @Override
    public void displayCurrentPressure(int pressure) {
        pressureTextView.setText(getString(R.string.pressure, pressure));
    }

    @Override
    public void displayCurrentWind(double windSpeed) {
        windTextView.setText(getString(R.string.wind, windSpeed));
    }

    @Override
    public void displayForecast(int i, String dayOfWeek, int minTemp, int maxTemp) {
        ForecastView currentForecastView = forecastViews.get(i);
        currentForecastView.dayWeekTextView.setText(dayOfWeek);
        currentForecastView.tempTextView.setText(getString(R.string.max_min_temp, minTemp, maxTemp));
    }
}
