package es.situm.wayfinding.sample.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import es.situm.wayfinding.SitumMap;
import es.situm.wayfinding.SitumMapView;
import es.situm.wayfinding.sample.R;

public class ActivitySampleCustomizeLocationRequests extends AppCompatActivity implements SitumMapView.OnMapReadyCallback {

    private static final String TAG = ActivitySampleCustomizeLocationRequests.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_situm_maps);
        SitumMapView mapView = findViewById(R.id.map_view);
        mapView.setOnMapReadyCallback(this);
    }

    @Override
    public void onMapReady(SitumMap situmMap) {
        // Customize location requests:
        situmMap.addLocationRequestInterceptor(builder -> {
            // E.g. don't use bluetooth:
            builder.useBle(false);
        });
        // See also:
        // Customize directions and navigation requests:
        situmMap.addDirectionsRequestInterceptor(builder -> {
            Log.d(TAG, "DirectionsRequest.Builder intercepted!");
        });
        situmMap.addNavigationRequestInterceptor(builder -> {
            Log.d(TAG, "NavigationRequest.Builder intercepted!");
        });
    }

    @Override
    public void onError() {
        Log.e(TAG, "OnMapReadyCallback#onError.");
    }
}
