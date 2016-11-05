package eu.laramartin.weather;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Lara on 05/11/2016.
 */

public class CityCard {

    public ImageView cityImageView;
    public TextView cityNameTextView;
    public TextView tempTextView;

    public ImageView getCityImageView() {
        return cityImageView;
    }

    public void setCityImageView(ImageView cityImageView) {
        this.cityImageView = cityImageView;
    }

    public TextView getCityNameTextView() {
        return cityNameTextView;
    }

    public void setCityNameTextView(TextView cityNameTextView) {
        this.cityNameTextView = cityNameTextView;
    }

    public TextView getTempTextView() {
        return tempTextView;
    }

    public void setTempTextView(TextView tempTextView) {
        this.tempTextView = tempTextView;
    }
}
