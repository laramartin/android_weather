package eu.laramartin.weather.ui.preferences;

import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import eu.laramartin.weather.ui.events.SettingsChangedEvent;

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

    public void selectedUnitsSystem(int i) {
        settings.setAsUnitsSystem(getUnitsSystem(i));
        EventBus.getDefault().post(new SettingsChangedEvent());
        view.updateSelectedUnits(getUnitsSystem(i));
    }

    private String getUnitsSystem(int i) {
        switch(i) {
            default:
            case 0:
                return "metric";
            case 1:
                return "imperial";
        }
    }

    public void load() {
        view.updateSelectedUnits(settings.getUnitsSystem());
    }
}
