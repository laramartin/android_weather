package eu.laramartin.weather.ui.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lara on 18/11/2016.
 */

public class DateUtils {

    public static String getDayOfTheWeek(long unixTime) {
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat("EEE");
        return formatter.format(date);
    }

    public static String getHourFromUnixTime(long unixTime) {
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }

    public static String getWholeDateOfCurrentWeather(long unixTime) {
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM d");
        return formatter.format(date);
    }
}
