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
            case "01d":
            case "02d":
                return R.drawable.cloud;
            case "03d":
                return R.drawable.cloud;
        }
    }
}
