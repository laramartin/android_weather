package eu.laramartin.weather.ui.cities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    private List<CityCard> cityCards;
    private Context context;


    public CitiesListAdapter(Context context, List<CityCard> cityCards) {
        this.cityCards = cityCards;
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
        @BindView(R.id.arrow_down_cities_row)
        ImageView arrowDownImageView;
        @BindView(R.id.arrow_up_cities_row)
        ImageView arrowUpImageView;

        private boolean isViewExpanded = false;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            if (!isViewExpanded) {
                for (int i = 0; i < forecastViews.size(); i++) {
                    ForecastView currentForecastView = forecastViews.get(i);
                    currentForecastView.setVisibility(View.GONE);
                    currentForecastView.setEnabled(false);
                }
                arrowUpImageView.setVisibility(View.GONE);
                arrowUpImageView.setEnabled(false);
                arrowDownImageView.setVisibility(View.VISIBLE);
                arrowDownImageView.setEnabled(true);
                isViewExpanded = true;
            }
        }

        public void bind(CityCard cityCard) {
            cityImageView.setImageResource(cityCard.getCityImageResourceId());
            cityNameTextView.setText(cityCard.getCityName());
            tempTextView.setText(String.valueOf(cityCard.getTemperature()));
        }

        @Override
        public void onClick(final View view) {
            if (!isViewExpanded) {
                for (int i = 0; i < forecastViews.size(); i++) {
                    ForecastView currentForecastView = forecastViews.get(i);
                    currentForecastView.setVisibility(View.GONE);
                    currentForecastView.setEnabled(false);
                }
                arrowUpImageView.setVisibility(View.GONE);
                arrowUpImageView.setEnabled(false);
                arrowDownImageView.setVisibility(View.VISIBLE);
                arrowDownImageView.setEnabled(true);
                isViewExpanded = true;
            } else {
                for (int i = 0; i < forecastViews.size(); i++) {
                    ForecastView currentForecastView = forecastViews.get(i);
                    currentForecastView.setVisibility(View.VISIBLE);
                    currentForecastView.setEnabled(true);
                }
                arrowUpImageView.setVisibility(View.VISIBLE);
                arrowUpImageView.setEnabled(true);
                arrowDownImageView.setVisibility(View.GONE);
                arrowDownImageView.setEnabled(false);
                isViewExpanded = false;
            }
        }
    }
}
