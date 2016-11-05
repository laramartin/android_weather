package eu.laramartin.weather;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lara on 30/10/2016.
 */

public class CitiesListLayout extends FrameLayout {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final String LOG_TAG = CitiesListLayout.class.getCanonicalName();
    private List<CityCard> testData = new ArrayList<>();

    public CitiesListLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = inflate(context, R.layout.layout_cities_list, this);
        ButterKnife.bind(this, view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        testData.add(new CityCard());
        testData.add(new CityCard());
        testData.add(new CityCard());
        testData.add(new CityCard());
        testData.add(new CityCard());
        testData.add(new CityCard());
        testData.add(new CityCard());
        testData.add(new CityCard());
        adapter = new CitiesListAdapter(context, testData);
        recyclerView.setAdapter(adapter);
    }
}
