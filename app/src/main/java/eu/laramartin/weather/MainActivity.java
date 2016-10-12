package eu.laramartin.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import eu.laramartin.weather.data.WeatherResponse;
import eu.laramartin.weather.data.WeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService weatherService = retrofit.create(WeatherService.class);

        String location = "berlin";
        String appid = "";
        Call<WeatherResponse> call = weatherService.getWeather(location, appid);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                Log.v("Mainactivity", response.body().getName());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("Mainactivity", "error in response: " + t.getLocalizedMessage());
            }
        });
    }
}
