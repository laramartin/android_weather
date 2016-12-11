package eu.laramartin.weather.ui.preferences;

import android.support.annotation.Nullable;

/**
 * Created by Lara on 11/12/2016.
 */

public class PreferencesPresenter {

    private final static String LOG_TAG = PreferencesPresenter.class.getCanonicalName();
    private Settings settings;
    @Nullable
    private PreferencesView view;

    public PreferencesPresenter(Settings settings) {
        this.settings = settings;
    }

    public void bind(PreferencesView view) {
        this.view = view;
    }

    public void unbind() {
        view = null;
    }

    public void selectedTemperature(int i) {
        // TODO
    }
}
