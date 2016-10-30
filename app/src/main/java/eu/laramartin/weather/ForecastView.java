package eu.laramartin.weather;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lara on 27/10/2016.
 */

public class ForecastView extends LinearLayout {
    @BindView(R.id.day_week_forecast_text_view)
    TextView dayWeekTextView;
    @BindView(R.id.temp_forecast_text_view)
    TextView tempTextView;
    @BindView(R.id.icon_forecast_image_view)
    ImageView iconImageView;

    public ForecastView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = inflate(context, R.layout.forecast_item, this);
        ButterKnife.bind(this, view);
    }
}
