package es.situm.wayfinding.sample.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.situm.sdk.SitumSdk;
import es.situm.sdk.error.Error;
import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.cartography.Geofence;
import es.situm.sdk.utils.Handler;
import es.situm.wayfinding.OnActiveBuildingListener;
import es.situm.wayfinding.SitumMap;
import es.situm.wayfinding.SitumMapView;
import es.situm.wayfinding.sample.R;

/**
 * Created by Regueira on 13/10/20
 */
public class ActivitySampleChangeGeofencesColor extends AppCompatActivity implements SitumMapView.OnMapReadyCallback,
        OnActiveBuildingListener {

    private static final String TAG = ActivitySampleChangeGeofencesColor.class.getSimpleName();
    private SitumMap situmMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_situm_maps);
    }

    @Override
    public void onMapReady(SitumMap situmMap) {
        this.situmMap = situmMap;
        situmMap.setOnActiveBuildingListener(this);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onActiveBuildingChange(Building building) {
        SitumSdk.communicationManager().fetchGeofencesFromBuilding(building, new Handler<List<Geofence>>() {
            @Override
            public void onSuccess(List<Geofence> geofences) {
                Map<Integer, List<Geofence>> colorGeofences = new HashMap<>();
                colorGeofences.put(0xff000000, geofences);
                situmMap.changeGeofencesColor(colorGeofences);
            }

            @Override
            public void onFailure(Error error) {
                Log.e(TAG, "onFailure callback received: " + error);
            }
        });
    }

    @Override
    public void onNoActiveBuilding() {

    }

}