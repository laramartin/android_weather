package eu.laramartin.weather.ui.preferences;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.laramartin.weather.R;

/**
 * Created by Lara on 30/10/2016.
 */

public class PreferencesLayout extends FrameLayout implements PreferencesView{

    private static final String LOG_TAG = PreferencesLayout.class.getCanonicalName();
    private Context context;
    @BindView(R.id.temp_settings_view)
    RelativeLayout temp_settings_layout;
    PreferencesPresenter presenter;

    public PreferencesLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(final Context context) {
        View view = inflate(context, R.layout.layout_preferences, this);
        ButterKnife.bind(this, view);
        presenter = new PreferencesPresenter(new Settings(context));
        presenter.bind(this);

        temp_settings_layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "temp settings", Toast.LENGTH_SHORT).show();
            }
        });
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
