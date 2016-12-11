package eu.laramartin.weather.ui.cities;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.eyeem.recyclerviewtools.adapter.WrapAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.laramartin.weather.BuildConfig;
import eu.laramartin.weather.R;
import eu.laramartin.weather.business.WeatherInteractorImpl;
import eu.laramartin.weather.business.db.CitiesDbHelper;
import eu.laramartin.weather.ui.common.Dialogs;
import eu.laramartin.weather.ui.preferences.Settings;

/**
 * Created by Lara on 30/10/2016.
 */

public class CitiesListLayout extends FrameLayout implements CitiesListView, SwipeRefreshLayout.OnRefreshListener  {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private CitiesListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    CitiesListPresenter presenter;
    @BindView(R.id.swipe_container_cities)
    SwipeRefreshLayout swipeRefreshLayout;

    private static final String LOG_TAG = CitiesListLayout.class.getCanonicalName();

    public CitiesListLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(final Context context) {
        View view = inflate(context, R.layout.layout_cities_list, this);
        ButterKnife.bind(this, view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogs.showInputCityDialog(context, presenter);
            }
        });


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        presenter = new CitiesListPresenter(new WeatherInteractorImpl(BuildConfig.API_KEY),
                new CitiesDbHelper(context), new Settings(context));
        adapter = new CitiesListAdapter(context, presenter);
        recyclerView.setAdapter(adapter);
        createFooter(adapter);
        presenter.bind(this);
        presenter.loadData();

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void addCityCard(CityCard cityCard) {
        adapter.add(cityCard);
        layoutManager.scrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    public void updateItem(CityCard cityCard) {
        adapter.replace(cityCard);
    }

    @Override
    public void displayForecast(int id, ForecastCard forecastCard) {
        // TODO
        adapter.addForecast(id, forecastCard);
    }

    @Override
    public void displayCityNotFound(String inputCity) {
        Dialogs.showCityNotFoundDialog(getContext(), presenter, inputCity);
    }

    @Override
    public void displayErrorServer() {
        Dialogs.showErrorServerDialog(getContext());
    }

    @Override
    public void clear() {
        adapter.clear();
    }

    @Override
    public void setFavIcon(int cityId, boolean isFavorite) {
        adapter.setFavIcon(cityId, isFavorite);
    }

    @Override
    public void onRefresh() {
        adapter.clear();
        presenter.loadData();
        swipeRefreshLayout.setRefreshing(false);
    }

    public void createFooter(CitiesListAdapter adapter) {
        WrapAdapter wrapAdapter = new WrapAdapter(adapter);
        recyclerView.setAdapter(wrapAdapter);
        wrapAdapter.addFooter(LayoutInflater.from(getContext()).inflate(
                R.layout.footer, recyclerView, false));
    }
}
