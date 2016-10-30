package eu.laramartin.weather.data;

/**
 * Created by Lara on 30/10/2016.
 */

public class TextUtils {

    public static String setFirstCharInUppercase(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
