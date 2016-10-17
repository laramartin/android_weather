package eu.laramartin.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.laramartin.weather.data.WeatherResponse;
import eu.laramartin.weather.data.WeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String LOG_TAG = MainActivity.class.getCanonicalName();
    @BindView(R.id.cityTextView) TextView city;
    @BindView(R.id.temperatureTextView) TextView temperature;
    @BindView(R.id.descriptionTextView) TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService weatherService = retrofit.create(WeatherService.class);

        String location = "london";
        String appid = "";
        Call<WeatherResponse> call = weatherService.getWeather(location, appid);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                Log.v("Mainactivity", response.body().getName());
                city.setText(response.body().getName());
                temperature.setText(String.valueOf(response.body().getMain().getTemperature()) +
                        getString(R.string.degree_fahrenheit) );
                description.setText(response.body().getWeather().get(0).getDescription());
                Log.v(LOG_TAG, "temperature: " + String.valueOf(response.body().getMain().getTemperature()));
                Log.v(LOG_TAG, "humidity: " + String.valueOf(response.body().getMain().getHumidity()));
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("Mainactivity", "error in response: " + t.getLocalizedMessage());
            }
        });
    }
}
