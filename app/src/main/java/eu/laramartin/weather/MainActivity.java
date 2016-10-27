package eu.laramartin.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements WeatherView {


    private static final String LOG_TAG = MainActivity.class.getCanonicalName();
    WeatherPresenter presenter;

    @BindView(R.id.city_TextView) TextView city;
    @BindView(R.id.temperature_TextView) TextView temperatureTextView;
    @BindView(R.id.description_TextView) TextView descriptionTextView;
    @BindView(R.id.humidity_TextView) TextView humidityTextView;
    @BindView(R.id.pressure_TextView) TextView pressureTextView;
    @BindView(R.id.wind_TextView) TextView windTextView;
    @BindView(R.id.forecast_5) ForecastView forecastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new WeatherPresenter(new WeatherInteractorImpl(getString(R.string.appid)));
        presenter.bind(this);
        ButterKnife.bind(this);
        presenter.performCall("berlin");

//        forecastView.textView.setText();
    }

    @Override
    public void displayTemp(int temp) {
        temperatureTextView.setText(String.valueOf(temp));
    }

    @Override
    public void displayDescription(String description) {
        descriptionTextView.setText(description);
    }

    @Override
    public void displayHumidity(int humidity) {
        humidityTextView.setText(getString(R.string.humidity, humidity));
    }

    @Override
    public void displayPressure(int pressure) {
        pressureTextView.setText(getString(R.string.pressure, pressure));
    }

    @Override
    public void displayWind(double windSpeed) {
        windTextView.setText(getString(R.string.wind, windSpeed));
    }
}
