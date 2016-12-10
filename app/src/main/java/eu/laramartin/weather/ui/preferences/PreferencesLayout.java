package eu.laramartin.weather.ui.preferences;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import eu.laramartin.weather.R;

/**
 * Created by Lara on 30/10/2016.
 */

public class PreferencesLayout extends FrameLayout {

    private static final String LOG_TAG = PreferencesLayout.class.getCanonicalName();
    private Context context;

    public PreferencesLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View view = inflate(context, R.layout.layout_preferences, this);
        ButterKnife.bind(this, view);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
