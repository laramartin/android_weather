package eu.laramartin.weather.ui.cities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import eu.laramartin.weather.R;
import eu.laramartin.weather.ui.common.ForecastView;

/**
 * Created by Lara on 05/11/2016.
 */

public class CitiesListAdapter extends RecyclerView.Adapter<CitiesListAdapter.ViewHolder> {

    private List<CityCard> cityCards = new ArrayList<>();


    private Context context;


    public CitiesListAdapter(Context context) {
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View citiesListView = inflater.inflate(R.layout.layout_cities_row, parent, false);
        return new ViewHolder(citiesListView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(cityCards.get(position));
    }

    @Override
    public int getItemCount() {
        return cityCards.size();
    }

    public void add(CityCard cityCard) {
        cityCards.add(cityCard);
        notifyItemInserted(cityCards.size() - 1);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image_cities_row)
        ImageView cityImageView;
        @BindView(R.id.city_name_cities_row)
        TextView cityNameTextView;
        @BindView(R.id.temperature_cities_row)
        TextView tempTextView;
        @BindViews({R.id.forecast_1_cities_row, R.id.forecast_2_cities_row,
                R.id.forecast_3_cities_row, R.id.forecast_4_cities_row, R.id.forecast_5_cities_row})
        List<ForecastView> forecastViews;
        @BindView(R.id.arrow_expand_collapse_cities_row)
        ImageView arrowExpandCollapseImageView;

        @Nullable
        private CityCard cityCard;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            for (int i = 0; i < forecastViews.size(); i++) {
                ForecastView currentForecastView = forecastViews.get(i);
                currentForecastView.setVisibility(View.GONE);
                currentForecastView.setEnabled(false);
            }
            arrowExpandCollapseImageView.setImageResource(R.drawable.ic_expand_more_black_24dp);



        }

        public void bind(CityCard cityCard) {
            this.cityCard = cityCard;
            cityImageView.setImageResource(cityCard.getCityImageResourceId());
            cityNameTextView.setText(cityCard.getCityName());
            tempTextView.setText(String.valueOf(cityCard.getTemperature()));
            showOrHideForecast();
        }

        private void showOrHideForecast() {
            if (cityCard != null) {
                if (cityCard.getIsExpanded()) {
                    showForecast();
                } else {
                    hideForecast();
                }
            }
        }

        @Override
        public void onClick(final View view) {
            if (cityCard != null) {
                cityCard.setExpanded(!cityCard.getIsExpanded());
                showOrHideForecast();
            }
        }

        private void showForecast() {
            for (int i = 0; i < forecastViews.size(); i++) {
                ForecastView currentForecastView = forecastViews.get(i);
                currentForecastView.setVisibility(View.VISIBLE);
                currentForecastView.setEnabled(true);
            }
            arrowExpandCollapseImageView.setImageResource(R.drawable.ic_expand_less_black_24dp);
        }

        private void hideForecast() {
            for (int i = 0; i < forecastViews.size(); i++) {
                ForecastView currentForecastView = forecastViews.get(i);
                currentForecastView.setVisibility(View.GONE);
                currentForecastView.setEnabled(false);
            }
            arrowExpandCollapseImageView.setImageResource(R.drawable.ic_expand_more_black_24dp);
        }
    }
}
