package eu.laramartin.weather.ui.cities;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.laramartin.weather.BuildConfig;
import eu.laramartin.weather.R;
import eu.laramartin.weather.business.WeatherInteractorImpl;

/**
 * Created by Lara on 30/10/2016.
 */

public class CitiesListLayout extends FrameLayout implements CitiesListView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private CitiesListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    CityCardPresenter presenter;

    private static final String LOG_TAG = CitiesListLayout.class.getCanonicalName();

    public CitiesListLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = inflate(context, R.layout.layout_cities_list, this);
        ButterKnife.bind(this, view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CitiesListAdapter(context);
        recyclerView.setAdapter(adapter);
        presenter = new CityCardPresenter(new WeatherInteractorImpl(BuildConfig.API_KEY));
        presenter.bind(this);
        presenter.loadData();
    }

    @Override
    public void addCityCard(CityCard cityCard) {
        adapter.add(cityCard);
    }
}
