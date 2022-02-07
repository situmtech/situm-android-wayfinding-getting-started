package es.situm.wayfinding.sample.samples;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.situm.sdk.SitumSdk;
import es.situm.sdk.error.Error;
import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.cartography.Floor;
import es.situm.sdk.model.cartography.Poi;
import es.situm.sdk.utils.Handler;
import es.situm.wayfinding.OnActiveBuildingListener;
import es.situm.wayfinding.OnLibraryViewListener;
import es.situm.wayfinding.OnPoiSelectionListener;
import es.situm.wayfinding.SitumMap;
import es.situm.wayfinding.SitumMapView;
import es.situm.wayfinding.sample.R;

public class ActivitySampleCustomizeUI extends AppCompatActivity implements
        SitumMapView.OnMapReadyCallback,
        OnActiveBuildingListener,
        OnPoiSelectionListener, SeekBar.OnSeekBarChangeListener, OnLibraryViewListener {

    private static final String TAG = ActivitySampleCustomizeUI.class.getSimpleName();
    private SitumMap mSitumMap;
    private SeekBar mSeekBar;
    private List<Floor> currentFloors = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_customize_ui);
        SitumMapView mapView = findViewById(R.id.map_view);
        mapView.setOnMapReadyCallback(this);
    }

    @Override
    public void onMapReady(SitumMap situmMap) {
        mSitumMap = situmMap;
        mSitumMap.setOnActiveBuildingListener(this);
        mSitumMap.setOnPoiSelectionListener(this);
        // Set this class to receive a call on root view created:
        mSitumMap.setOnLibraryViewListener(this);
    }

    @Override
    public void onLibraryViewCreated(FrameLayout root) {
        // Now the UI is ready: configure SeekBar for handling building floors.
        mSeekBar = root.findViewById(R.id.floors);
        updateSeekBar(null);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onError() {
        Log.e(TAG, "onError callback received");
    }

    // =============================================================================================
    // Update floors on active building change:
    // =============================================================================================
    @Override
    public void onActiveBuildingChange(Building building) {
        SitumSdk.communicationManager().fetchFloorsFromBuilding(building, new Handler<Collection<Floor>>() {
            @Override
            public void onSuccess(Collection<Floor> floors) {
                currentFloors = new ArrayList<>(floors);
                updateSeekBar(floors);
            }

            @Override
            public void onFailure(Error error) {
                Log.e(TAG, "onFailure callback received: " + error);
            }
        });
    }

    @Override
    public void onNoActiveBuilding() {
        updateSeekBar(null);
    }

    private void updateSeekBar(@Nullable Collection<Floor> floors) {
        if (mSeekBar != null) {
            if (floors == null) {
                mSeekBar.setVisibility(View.GONE);
            } else {
                mSeekBar.incrementProgressBy(1);
                mSeekBar.setMax(floors.size() > 0 ? floors.size() - 1 : 0);
                mSeekBar.setVisibility(View.VISIBLE);
            }
            mSeekBar.setProgress(0);
        }
    }

    // =============================================================================================
    // SeekBar.OnSeekBarChangeListener:
    // =============================================================================================
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser && currentFloors != null) {
            Floor selectedFloor = currentFloors.get(progress);
            mSitumMap.selectFloor(selectedFloor);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    // =============================================================================================
    // Show PoI info:
    // =============================================================================================
    @Override
    public void onPoiSelected(Poi poi, Floor floor, Building building) {
        String text = String.format("%s at %s selected!", poi.getName(), building.getName());
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPoiDeselected(Building building) {

    }

}
