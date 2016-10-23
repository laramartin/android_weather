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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new WeatherPresenter(new WeatherInteractorImpl());
        presenter.bind(this);
        ButterKnife.bind(this);
        presenter.performCall("berlin");
    }

    @Override
    public void displayTemp(double temp) {
        temperatureTextView.setText(String.valueOf(temp) + getString(R.string.degree_fahrenheit));
    }

    @Override
    public void displayDescription(String description) {
        descriptionTextView.setText(description);
    }
}
