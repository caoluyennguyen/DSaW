package com.nguyenhongphuc98.dsaw.ui.map;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.data.DataCenter;
import com.nguyenhongphuc98.dsaw.data.model.Case;

import java.util.List;

public class MapVisualizeFragment extends Fragment implements OnMapReadyCallback {

    private MapVisualizeViewModel mViewModel;

    GoogleMap mGoogleMap;

    MapView mMapView;

    View view;

    public static MapVisualizeFragment newInstance() {
        return new MapVisualizeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map_visualize, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) view.findViewById(R.id.mapVisualize);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MapVisualizeViewModel.class);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mViewModel.getListCases().observe(this, new Observer<List<Case>>() {
            @Override
            public void onChanged(List<Case> cases) {

                for (Case c: cases) {
                    double latitude = Double.parseDouble(c.getLocation().split(",")[0]);
                    double logtitude = Double.parseDouble(c.getLocation().split(",")[1]);
                    //Log.d("CASE", "onChanged: location of case: " + latitude + logtitude);

                    // Defaul is f0 - so it will get red color
                    int strokeColor = Color.RED;

                    // Purple color for F1
                    if (c.getF().equals("F1"))
                        strokeColor = Color.rgb(214, 79, 153);
                    // Orange color for F2
                    if (c.getF().equals("F2"))
                        strokeColor = Color.rgb(247, 159, 7);
                    // Yellow color for F3
                    if (c.getF().equals("F3"))
                        strokeColor = Color.rgb(162, 255, 0);

                    Circle circle = googleMap.addCircle(new CircleOptions()
                            .center(new LatLng(latitude, logtitude))
                            .radius(20)
                            .strokeColor(strokeColor)
                            .fillColor(Color.GREEN));
                }

                Log.d("CASE", "your location: " + DataCenter.currentLocation.getLatitude() + ":"+ DataCenter.currentLocation.getLongitude());
                Circle circle = googleMap.addCircle(new CircleOptions()
                        .center(new LatLng(DataCenter.currentLocation.getLatitude(), DataCenter.currentLocation.getLongitude()))
                        .radius(20)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));
            }
        });




        CameraPosition liberty = CameraPosition.builder()
                .target(new LatLng(DataCenter.currentLocation.getLatitude(), DataCenter.currentLocation.getLongitude()))
                .zoom(16)
                .bearing(0)
                .tilt(45)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(liberty));
    }
}
