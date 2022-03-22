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
import es.situm.wayfinding.LibrarySettings;
import es.situm.wayfinding.OnActiveBuildingListener;
import es.situm.wayfinding.OnFloorChangeListener;
import es.situm.wayfinding.OnLoadBuildingsListener;
import es.situm.wayfinding.OnLocationChangeListener;
import es.situm.wayfinding.OnPoiSelectionListener;
import es.situm.wayfinding.OnUserInteractionListener;
import es.situm.wayfinding.SitumMapsLibrary;
import es.situm.wayfinding.SitumMapsListener;
import es.situm.wayfinding.navigation.Navigation;
import es.situm.wayfinding.navigation.NavigationError;
import es.situm.wayfinding.navigation.OnNavigationListener;
import es.situm.wayfinding.sample.R;

public class ActivitySampleUsingLibrary extends AppCompatActivity implements SitumMapsListener,
        OnUserInteractionListener, OnActiveBuildingListener, OnLoadBuildingsListener,
        OnFloorChangeListener, OnPoiSelectionListener, OnLocationChangeListener {

    private static final String TAG = ActivitySampleUsingLibrary.class.getSimpleName();

    /**
     * The situmizer.
     */
    protected SitumMapsLibrary mLibrary;
    /**
     * Lirary settings
     */
    protected LibrarySettings librarySettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_using_library);
        String user = getString(R.string.situm_credentials_api_user);
        String apiKey = getString(R.string.situm_credentials_api_key);
        librarySettings = new LibrarySettings();
        librarySettings.setApiKey(user, apiKey);
        // Create library:
        mLibrary = new SitumMapsLibrary(R.id.maps_library_target, this, librarySettings);
        // Get notified about context initialisation events:
        mLibrary.setSitumMapsListener(this);
        // Events:
        mLibrary.setOnLoadBuildingsListener(this);
        mLibrary.setOnActiveBuildingListener(this);
        mLibrary.setOnPoiSelectionListener(this);
        mLibrary.setOnFloorChangeListener(this);
        mLibrary.setUserInteractionsListener(this);
        mLibrary.setOnNavigationListener(this);
        // Stay alert of location changes:
        mLibrary.setOnLocationChangeListener(this);
        // Load:
        mLibrary.load();
    }

    // =============================================================================================
    // SitumMapsListener
    // =============================================================================================

    @Override
    public void onSuccess() {
        Log.d(TAG, "SitumMapsListener#onSuccess");
    }

    @Override
    public void onError(int code) {
        Log.e(TAG, "SitumMapsListener#onError, error code is " + code);
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
