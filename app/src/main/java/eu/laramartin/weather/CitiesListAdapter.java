package eu.laramartin.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_card_view)
        ImageView cityImageView;
        @BindView(R.id.city_name_card_text_view)
        TextView cityNameTextView;
        @BindView(R.id.temperature_card_text_view)
        TextView tempTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(CityCard cityCard) {
            cityImageView.setImageResource(cityCard.getCityImageResourceId());
            cityNameTextView.setText(cityCard.getCityName());
            tempTextView.setText(String.valueOf(cityCard.getTemperature()));
        }
    }
}
