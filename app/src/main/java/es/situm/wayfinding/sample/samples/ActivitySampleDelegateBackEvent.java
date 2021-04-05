package es.situm.wayfinding.sample.samples;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import es.situm.wayfinding.SitumMap;
import es.situm.wayfinding.SitumMapView;
import es.situm.wayfinding.sample.R;

/**
 * Simple activity delegating back pressed events to the Situm Map.
 *
 * @author Rodrigo Lago.
 */
public class ActivitySampleDelegateBackEvent extends AppCompatActivity implements SitumMapView.OnMapReadyCallback {

    private static final String TAG = ActivitySampleListenEvents.class.getSimpleName();
    private SitumMap situmMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_situm_maps);
        SitumMapView mapView = findViewById(R.id.map_view);
        mapView.setOnMapReadyCallback(this);
    }

    @Override
    public void onMapReady(SitumMap pSitumMap) {
        situmMap = pSitumMap;
    }

    @Override
    public void onError() {
        Log.e(TAG, "Error loading SitumMapView.");
    }

    @Override
    public void onBackPressed() {
        // If you want the library to handle back pressed events then delegate:
        if (situmMap != null) {
            if (situmMap.onBackPressed()) {
                return;
            }
        }
        super.onBackPressed();
    }

}
