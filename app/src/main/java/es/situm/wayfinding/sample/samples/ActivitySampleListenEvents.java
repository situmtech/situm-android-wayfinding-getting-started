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
import es.situm.wayfinding.OnPoiSelectedListener;
import es.situm.wayfinding.OnUserInteractionListener;
import es.situm.wayfinding.SitumMap;
import es.situm.wayfinding.SitumMapView;
import es.situm.wayfinding.sample.R;

public class ActivitySampleListenEvents extends AppCompatActivity implements SitumMapView.OnMapReadyCallback,
        OnUserInteractionListener, OnActiveBuildingListener, OnLoadBuildingsListener,
        OnFloorChangeListener, OnPoiSelectedListener, OnLocationChangeListener {

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
        situmMap.setOnPoiSelectedListener(this);
        situmMap.setOnFloorSelectedListener(this);
        situmMap.setUserInteractionsListener(this);
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
    // OnPoiSelectedListener
    // =============================================================================================

    @Override
    public void onPOISelected(Poi poi, Floor level, Building building) {
        Log.d(TAG, "OnPoiSelectedListener#onPOISelected");
    }

    @Override
    public void onPoiDeselected(Building building) {
        Log.d(TAG, "OnPoiSelectedListener#onPoiDeselected");
    }

    // =============================================================================================
    // OnUserInteractionListener
    // =============================================================================================

    @Override
    public void onRouteToSelectedPOIRequested() {
        Log.d(TAG, "OnUserInteractionListener#onRouteToSelectedPOIRequested");
    }

    @Override
    public void onRouteFinish() {
        Log.d(TAG, "OnUserInteractionListener#onRouteFinish");
    }

    @Override
    public void onRouteCanceled() {
        Log.d(TAG, "OnUserInteractionListener#onRouteCanceled");
    }

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
