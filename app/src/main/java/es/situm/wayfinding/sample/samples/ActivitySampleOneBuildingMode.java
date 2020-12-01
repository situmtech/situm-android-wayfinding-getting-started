package es.situm.wayfinding.sample.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import es.situm.wayfinding.SitumMap;
import es.situm.wayfinding.SitumMapView;
import es.situm.wayfinding.sample.R;

public class ActivitySampleOneBuildingMode extends AppCompatActivity implements SitumMapView.OnMapReadyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_one_building_mode);
        SitumMapView mapView = findViewById(R.id.map_view);
        mapView.setOnMapReadyCallback(this);
    }

    @Override
    public void onMapReady(SitumMap situmMap) {
        //This will start the positioning in the first building of the account unless you set the building_id in the xml
        String buildingId = getString(R.string.one_building_mode_building_id);
        if (buildingId.equals("-1")) {
            situmMap.enableOneBuildingMode();
        } else {
            situmMap.enableOneBuildingMode(buildingId);
        }
    }

    @Override
    public void onError() {

    }
}
