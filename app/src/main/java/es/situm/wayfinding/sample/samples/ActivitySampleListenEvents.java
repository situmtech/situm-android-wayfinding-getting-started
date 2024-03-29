package es.situm.wayfinding.sample.samples;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.cartography.Floor;
import es.situm.sdk.model.cartography.Poi;
import es.situm.sdk.model.location.Location;
import es.situm.wayfinding.OnActiveBuildingListener;
import es.situm.wayfinding.OnFloorChangeListener;
import es.situm.wayfinding.OnLoadBuildingsListener;
import es.situm.wayfinding.OnLocationChangeListener;
import es.situm.wayfinding.OnPoiSelectionListener;
import es.situm.wayfinding.OnUserInteractionListener;
import es.situm.wayfinding.SitumMap;
import es.situm.wayfinding.SitumMapView;
import es.situm.wayfinding.navigation.Navigation;
import es.situm.wayfinding.navigation.NavigationError;
import es.situm.wayfinding.navigation.OnNavigationListener;
import es.situm.wayfinding.sample.R;

public class ActivitySampleListenEvents extends AppCompatActivity implements SitumMapView.OnMapReadyCallback,
        OnUserInteractionListener, OnActiveBuildingListener, OnLoadBuildingsListener,
        OnFloorChangeListener, OnPoiSelectionListener, OnLocationChangeListener, OnNavigationListener {

    private static final String TAG = ActivitySampleListenEvents.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_situm_maps);
        SitumMapView mapView = findViewById(R.id.map_view);
        mapView.setOnMapReadyCallback(this);
    }

    @Override
    public void onMapReady(SitumMap situmMap) {
        // Events:
        situmMap.setOnLoadBuildingsListener(this);
        situmMap.setOnActiveBuildingListener(this);
        situmMap.setOnPoiSelectionListener(this);
        situmMap.setOnFloorChangeListener(this);
        situmMap.setUserInteractionsListener(this);
        situmMap.setOnNavigationListener(this);
        // Stay alert of location changes:
        situmMap.setOnLocationChangeListener(this);
    }

    @Override
    public void onError() {
        Log.e(TAG, "onError callback received");
    }

    // =============================================================================================
    // OnLoadBuildingsListener
    // =============================================================================================

    @Override
    public void onBuildingLoaded(Building building) {
        Log.d(TAG, "OnLoadBuildingsListener#onBuildingLoaded");
    }

    @Override
    public void onBuildingsLoaded(List<Building> list) {
        Log.d(TAG, "OnLoadBuildingsListener#onBuildingsLoaded");
    }

    @Override
    public void onFloorImageLoaded(Building building, Floor floor) {
        Log.d(TAG, "OnLoadBuildingsListener#onFloorImageLoaded");
    }

    // =============================================================================================
    // OnActiveBuildingListener
    // =============================================================================================

    @Override
    public void onActiveBuildingChange(Building building) {
        Log.d(TAG, "OnActiveBuildingListener#onActiveBuildingChange");
    }

    @Override
    public void onNoActiveBuilding() {
        Log.d(TAG, "OnActiveBuildingListener#onNoActiveBuilding");
    }

    // =============================================================================================
    // OnFloorChangeListener
    // =============================================================================================

    @Override
    public void onFloorChanged(Floor from, Floor to, Building building) {
        Log.d(TAG, "OnFloorChangeListener#onFloorChanged");
    }

    // =============================================================================================
    // OnPoiSelectionListener
    // =============================================================================================

    @Override
    public void onPoiSelected(Poi poi, Floor level, Building building) {
        Log.d(TAG, "OnPoiSelectionListener#onPoiSelected");
    }

    @Override
    public void onPoiDeselected(Building building) {
        Log.d(TAG, "OnPoiSelectionListener#onPoiDeselected");
    }

    // =============================================================================================
    // OnNavigationListener
    // =============================================================================================

    @Override
    public void onNavigationRequested(Navigation navigation) {
        Log.d(TAG, "OnNavigationListener#onNavigationRequested");
    }

    @Override
    public void onNavigationStarted(Navigation navigation) {
        Log.d(TAG, "OnNavigationListener#onNavigationStarted");
    }

    @Override
    public void onNavigationError(Navigation navigation, NavigationError navigationError) {
        Log.d(TAG, "OnNavigationListener#onNavigationError");
    }

    @Override
    public void onNavigationFinished(Navigation navigation) {
        Log.d(TAG, "OnNavigationListener#onNavigationFinished");
    }

    // =============================================================================================
    // OnUserInteractionListener
    // =============================================================================================

    @Override
    public void onMapReady() {
        Log.d(TAG, "OnUserInteractionListener#onMapReady");
    }

    @Override
    public void onMapTouched(LatLng latLng) {
        Log.d(TAG, "OnUserInteractionListener#onMapTouched");
    }

    @Override
    public void onBuildingMarkerSelected(Building building) {
        Log.d(TAG, "OnUserInteractionListener#onBuildingMarkerSelected");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Log.d(TAG, "OnUserInteractionListener#onPointerCaptureChanged");
    }

    // =============================================================================================
    // OnLocationChangeListener
    // =============================================================================================

    @Override
    public void onLocationChanged(Location userLocation) {
        Log.d(TAG, "OnLocationChangeListener#onLocationChanged");
    }

    @Override
    public void onUserNotInBuilding() {
        Log.d(TAG, "OnLocationChangeListener#onUserNotInBuilding");
    }

    @Override
    public void onPositioningStopped() {
        Log.d(TAG, "OnLocationChangeListener#onPositioningStopped");
    }

}
