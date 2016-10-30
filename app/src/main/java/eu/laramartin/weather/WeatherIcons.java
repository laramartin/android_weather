package eu.laramartin.weather;

import android.support.annotation.DrawableRes;

/**
 * Created by Lara on 30/10/2016.
 */

public class WeatherIcons {

    @DrawableRes
    public static int getIcon(String code) {
        switch (code) {
            default:
                return R.drawable.ic_weather_unknown;
            case "01d":
                return R.drawable.ic_weather_sun;
            case "01n":
                return R.drawable.ic_weather_moon;
            case "02d":
                return R.drawable.ic_weather_sun_cloud;
            case "02n":
                return R.drawable.ic_weather_moon_few_clouds;
            case "03d":
            case "03n":
                return R.drawable.ic_weather_scattered_clouds;
            case "04d":
            case "04n":
                return R.drawable.ic_weather_broken_cloud;
            case "09d":
            case "09n":
                return R.drawable.ic_weather_shower_rain;
            case "10d":
            case "10n":
                return R.drawable.ic_weather_light_rain;
            case "11d":
            case "11n":
                return R.drawable.ic_weather_thunder;
            case "13d":
            case "13n":
                return R.drawable.ic_weather_snow;
            case "50d":
            case "50n":
                return R.drawable.ic_weather_fogg;
        }
    }
}
