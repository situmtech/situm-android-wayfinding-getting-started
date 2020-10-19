package es.situm.wayfinding.sample.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import es.situm.wayfinding.sample.R;
import es.situm.wayfinding.sample.samples.ActivitySampleAutostartPositioning;
import es.situm.wayfinding.sample.samples.ActivitySampleChangeGeofencesColor;
import es.situm.wayfinding.sample.samples.ActivitySampleCustomizeRequests;
import es.situm.wayfinding.sample.samples.ActivitySampleCustomizeUI;
import es.situm.wayfinding.sample.samples.ActivitySampleDashboardTheme;
import es.situm.wayfinding.sample.samples.ActivitySampleDelegateBackEvents;
import es.situm.wayfinding.sample.samples.ActivitySampleLogEvents;
import es.situm.wayfinding.sample.samples.ActivitySampleNoSearchView;
import es.situm.wayfinding.sample.samples.ActivitySampleOneBuildingMode;
import es.situm.wayfinding.sample.samples.ActivitySampleSimpleMap;
import es.situm.wayfinding.sample.samples.ActivitySampleUsingLibrary;

public class MainActivity extends AppCompatActivity implements ViewHolderHelper.OnSampleClickListener {

    private RecyclerView mRecyclerView;
    private ViewHolderHelper mSamplesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.samples_recycler_view);
        mSamplesList = new ViewHolderHelper();
        mSamplesList.init(this, mRecyclerView, this);

        mSamplesList.addItem(R.string.situm_sample_title_simple_map, ActivitySampleSimpleMap.class);
        mSamplesList.addItem(R.string.situm_sample_title_log_events, ActivitySampleLogEvents.class);
        mSamplesList.addItem(R.string.situm_sample_title_delegate_back_events, ActivitySampleDelegateBackEvents.class);
        mSamplesList.addItem(R.string.situm_sample_title_library, ActivitySampleUsingLibrary.class);
        mSamplesList.addItem(R.string.situm_sample_title_no_search_view, ActivitySampleNoSearchView.class);
        mSamplesList.addItem(R.string.situm_sample_title_dashboard_theme, ActivitySampleDashboardTheme.class);
        mSamplesList.addItem(R.string.situm_sample_title_interceptors, ActivitySampleCustomizeRequests.class);
        mSamplesList.addItem(R.string.situm_sample_title_one_building_mode, ActivitySampleOneBuildingMode.class);
        mSamplesList.addItem(R.string.situm_sample_title_autostart_positioning, ActivitySampleAutostartPositioning.class);
        mSamplesList.addItem(R.string.situm_sample_title_customize_ui, ActivitySampleCustomizeUI.class);
        mSamplesList.addItem(R.string.situm_sample_title_change_geofences_color, ActivitySampleChangeGeofencesColor.class);
    }


    @Override
    public void onSampleClick(ViewHolderHelper.DataHolder item) {
        Intent launchSample = new Intent(this, item.getSampleClass());
        startActivity(launchSample);
    }
}
