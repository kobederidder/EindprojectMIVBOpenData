package ehb.be.eindprojectmivbopendata.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import ehb.be.eindprojectmivbopendata.R;
import ehb.be.eindprojectmivbopendata.parsers.StopParser;
import ehb.be.eindprojectmivbopendata.source.Stop;

/**
 * Created by mobapp10 on 15/05/17.
 */

public class MapFragment  extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapView gm;
    ArrayList<Stop> mStop = new ArrayList<>();

    public MapFragment() {
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        gm = (MapView) rootView.findViewById(R.id.mv_map);
        gm.onCreate(savedInstanceState);
        gm.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        gm.onResume();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng belgCoord = new LatLng(50.85712, 4.34744);
        CameraUpdate updateMove = CameraUpdateFactory.newLatLngZoom(belgCoord, 14);
        mMap.animateCamera(updateMove);

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(50.85712, 4.34744))
                .title("Marker"));

        drawMarkers();
    }

    public void getAllStopsOnMap() {
        mStop = StopParser.getInstance().getmStopList();

        if (mMap != null) {
            drawMarkers();
        }
    }

    private void drawMarkers() {

        for (int i = 0; i < mStop.size(); i++) {
            double lat = Double.parseDouble(mStop.get(i).getStop_lat());
            double lon = Double.parseDouble(mStop.get(i).getStop_lon());
            LatLng coord = new LatLng(lat, lon);

            mMap.addMarker(new MarkerOptions()
                    .position(coord)
                    .title(mStop.get(i).getStop_name()));
        }
    }
}
