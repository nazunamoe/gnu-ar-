package gnuarmap.observer.com.gnuar.Lists;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import gnuarmap.observer.com.gnuar.R;


import gnuarmap.observer.com.gnuar.MapApiKey;

import static gnuarmap.observer.com.gnuar.Lists.LocationList.CUSTOM_MARKER_POINT;
import static gnuarmap.observer.com.gnuar.Lists.LocationList.CUSTOM_MARKER_POINT2;
import static gnuarmap.observer.com.gnuar.Lists.LocationList.DEFAULT_MARKER_POINT;

public class GMap extends FragmentActivity implements MapView.MapViewEventListener, MapView.POIItemEventListener,
    MapView.CurrentLocationEventListener{


    public MapView mMapView;
    private MapPOIItem mDefaultMarker;
    private MapPOIItem mCustomMarker;
    private MapPOIItem mCustomBmMarker;
    private MapReverseGeoCoder mReverseGeoCoder = null;
    private boolean isUsingCustomLocationMarker = false;



    //----------------------------------Location Part---------------------------------------

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }
//----------------------------------Location Part---------------------------------------


    // CalloutBalloonAdapter 인터페이스 구현
    class CustomCalloutBalloonAdapter implements CalloutBalloonAdapter {
        private final View mCalloutBalloon;

        public CustomCalloutBalloonAdapter() {
            mCalloutBalloon = getLayoutInflater().inflate(R.layout.callout_ballon, null);
        }

        @Override
        public View getCalloutBalloon(MapPOIItem poiItem) {
            ((ImageView) mCalloutBalloon.findViewById(R.id.badge)).setImageResource(R.mipmap.badge);
            ((TextView) mCalloutBalloon.findViewById(R.id.title)).setText(poiItem.getItemName());
            return mCalloutBalloon;
        }

        @Override
        public View getPressedCalloutBalloon(MapPOIItem poiItem) {
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gmarker_map_view);


        mMapView = (MapView) findViewById(R.id.map_view);
        mMapView.setDaumMapApiKey(MapApiKey.DAUM_MAPS_ANDROID_APP_API_KEY);
        mMapView.setMapViewEventListener(this);
        mMapView.setPOIItemEventListener(this);
        mMapView.setZoomLevel(4,true);

        // 구현한 CalloutBalloonAdapter 등록
        mMapView.setCalloutBalloonAdapter(new CustomCalloutBalloonAdapter());
        createDefaultMarker(mMapView);
        createCustomMarker(mMapView);
        createCustomMarker1(mMapView);
        showAll();
    }

    public void onClicked2(View v){
        Toast.makeText(getApplicationContext(),"현재위치보기 \n자동으로 현재위치까지 이동합니다.",Toast.LENGTH_LONG).show();
        mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        mMapView.setZoomLevel(3,true);
    }


    @Override
    public void onMapViewInitialized(MapView mapView) {

    }


    public void createDefaultMarker(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "교양동";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(0);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
        mapView.selectPOIItem(mDefaultMarker, true);
        mapView.setMapCenterPoint(DEFAULT_MARKER_POINT, false);
    }

    private void createCustomMarker(MapView mapView) {
        mCustomMarker = new MapPOIItem();
        String name = "경상대학교 정문";
        mCustomMarker.setItemName(name);
        mCustomMarker.setTag(1);
        mCustomMarker.setMapPoint(CUSTOM_MARKER_POINT);
        mCustomMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);

        mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        mCustomMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);


        mCustomMarker.setCustomImageResourceId(R.mipmap.marker);
        mCustomMarker.setCustomImageAutoscale(false);
        mCustomMarker.setCustomImageAnchor(0.5f, 0.5f);

        mapView.addPOIItem(mCustomMarker);
        mapView.selectPOIItem(mCustomMarker, true);
        mapView.setMapCenterPoint(CUSTOM_MARKER_POINT, false);

    }

    private void createCustomMarker1(MapView mapView) {
        mCustomBmMarker = new MapPOIItem();
        String name = "407동";
        mCustomBmMarker.setItemName(name);
        mCustomBmMarker.setTag(2);
        mCustomBmMarker.setMapPoint(CUSTOM_MARKER_POINT2);

        mCustomBmMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);

        mCustomBmMarker.setCustomImageResourceId(R.mipmap.marker);
        mCustomBmMarker.setCustomImageAutoscale(false);
        mCustomBmMarker.setCustomImageAnchor(0.5f, 0.5f);

        mapView.addPOIItem(mCustomBmMarker);
        mapView.selectPOIItem(mCustomBmMarker, true);
        mapView.setMapCenterPoint(CUSTOM_MARKER_POINT2, false);
    }

    private void showAll() {
        int padding = 20;
        float minZoomLevel = 4;
        float maxZoomLevel = 7;
        MapPointBounds bounds = new MapPointBounds(CUSTOM_MARKER_POINT, DEFAULT_MARKER_POINT);
        mMapView.moveCamera(CameraUpdateFactory.newMapPointBounds(bounds, padding, minZoomLevel, maxZoomLevel));
    }


//----------------------------------MapEvent Part---------------------------------------

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }
//----------------------------------MapEvent Part---------------------------------------


//----------------------------------Marker Part---------------------------------------

    String[] markerMenuItems = {
            "검색하기",
            "정보"
    };



    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }



    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, final MapPOIItem mapPOIItem) {
        final String itemname = mapPOIItem.getItemName();
        Toast.makeText(this, "Clicked " + itemname, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
}
//----------------------------------Marker Part---------------------------------------
