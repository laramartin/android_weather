package eu.laramartin.weather.ui.cities;

import android.content.Context;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import eu.laramartin.weather.R;
import eu.laramartin.weather.ui.common.Dialogs;
import eu.laramartin.weather.ui.common.ForecastView;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by Lara on 05/11/2016.
 */

public class CitiesListAdapter extends RecyclerView.Adapter<CitiesListAdapter.ViewHolder> {

    private ArrayList<CityCard> cityCards = new ArrayList<>();


    private Context context;
    private CitiesListPresenter presenter;


    public CitiesListAdapter(Context context, CitiesListPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
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
    public long getItemId(int position) {
        return cityCards.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return cityCards.size();
    }

    public void add(CityCard cityCard) {
        cityCards.add(cityCard);
        notifyItemInserted(cityCards.size() - 1);
    }

    public void replace(CityCard cityCard) {
        for (int i = 0; i < cityCards.size(); i++) {
            if (cityCard.getId() == cityCards.get(i).getId()) {
                cityCards.set(i, cityCard);
                notifyItemChanged(i);
            }
        }
    }

    public void addForecast(int id, ForecastCard forecastCard) {
        for (int i = 0; i < cityCards.size(); i++) {
            if (cityCards.get(i).getId() == id) {
                CityCard currentCityCard = cityCards.get(i);
                currentCityCard.setForecastCard(forecastCard);
                notifyItemChanged(i);
            }
        }
    }

    public void clear() {
        cityCards.clear();
        notifyDataSetChanged();
    }

    public void setFavIcon(int cityId, boolean isFavorite) {
        for (int i = 0; i < cityCards.size(); i++) {
            if (cityId == cityCards.get(i).getId()) {
                cityCards.get(i).setFavorite(isFavorite);
                notifyItemChanged(i);
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

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
        // TODO hide or show forecast layout instead of forecast views
        @BindView(R.id.forecast_layout)
        LinearLayout forecastLayout;
        @BindView(R.id.favorite_city_image_view)
        ImageView favoriteCityImageView;
        @Nullable
        private CityCard cityCard;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            forecastLayout.setVisibility(View.GONE);
            forecastLayout.setEnabled(false);
            arrowExpandCollapseImageView.setImageResource(R.drawable.ic_expand_more_black_24dp);
        }

        public void bind(final CityCard cityCard) {
            this.cityCard = cityCard;
            cityImageView.setImageResource(cityCard.getCityImageResourceId());
            cityNameTextView.setText(cityCard.getCityName());
            tempTextView.setText(String.valueOf(cityCard.getTemperature()));
            if (presenter.isFavoriteCity(cityCard)) {
                Log.v("adapter", "need to use filled heart icon for " + cityCard.getCityName());
                presenter.setAsFavoriteCityInCityCard(cityCard);
            } else {
                Log.v("adapter", "need to use empty heart icon for " + cityCard.getCityName());
                presenter.setAsNotFavoriteCity(cityCard);
            }
            favoriteCityImageView.setImageResource(presenter.getFavoriteIcon(cityCard));
            favoriteCityImageView.setAdjustViewBounds(true);
            favoriteCityImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.clickedFavorite(cityCard);
                }
            });
            showOrHideForecast();

            if (cityCard.getForecastCard() != null) {
                List<ForecastCard.ForecastCardItem> forecastCardList = cityCard.getForecastCard().getList();
                for (int i = 0; i < forecastCardList.size(); i++) {
                    forecastViews.get(i).dayWeekTextView.setText(forecastCardList.get(i).getDayOfTheWeek());
                    forecastViews.get(i).tempTextView.setText(context.getString(R.string.max_min_temp,
                            forecastCardList.get(i).getTempMin(), forecastCardList.get(i).getTempMax()));
                    forecastViews.get(i).iconImageView.setImageResource(forecastCardList.get(i).getIcon());
                }
            }
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
            forecastLayout.setVisibility(View.VISIBLE);
            forecastLayout.setEnabled(true);
            arrowExpandCollapseImageView.setImageResource(R.drawable.ic_expand_less_black_24dp);
        }

        private void hideForecast() {
            forecastLayout.setVisibility(View.GONE);
            forecastLayout.setEnabled(false);
            arrowExpandCollapseImageView.setImageResource(R.drawable.ic_expand_more_black_24dp);
        }

        @Override
        public boolean onLongClick(View view) {
            if (cityCard != null) {
                Dialogs.showDeleteCityConfirmationDialog(context, cityCard.getId(), presenter);
                ((Vibrator)context.getSystemService(VIBRATOR_SERVICE)).vibrate(100);
                return true;
            }
            return false;
        }
    }
}
