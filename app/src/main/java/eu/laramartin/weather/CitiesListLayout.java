package eu.laramartin.weather;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.ButterKnife;

/**
 * Created by Lara on 30/10/2016.
 */

public class CitiesListLayout extends FrameLayout {

    private static final String LOG_TAG = CitiesListLayout.class.getCanonicalName();

    public CitiesListLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = inflate(context, R.layout.layout_cities_list, this);
        ButterKnife.bind(this, view);
    }
}
