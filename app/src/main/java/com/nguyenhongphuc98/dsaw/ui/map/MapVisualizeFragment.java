package com.nguyenhongphuc98.dsaw.ui.map;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.Case;

import java.util.List;

public class MapVisualizeFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnCircleClickListener {

    private MapVisualizeViewModel mViewModel;

    GoogleMap mGoogleMap;

    MapView mMapView;

    View view;

    Dialog caseInfoDialog;

    ImageButton btnTracking;

    EditText edtDistance;

    Button btnDistance;

    public static MapVisualizeFragment newInstance() {
        return new MapVisualizeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map_visualize, container, false);
        this.caseInfoDialog = new Dialog(getContext());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = view.findViewById(R.id.mapVisualize);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

        btnTracking = view.findViewById(R.id.btnTracking);

        edtDistance = view.findViewById(R.id.edtDistance);
        btnDistance = view.findViewById(R.id.btnSaveDistance);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MapVisualizeViewModel.class);

        btnTracking.setOnClickListener(v -> {
            DataCenter.routeUID = DataCenter.currentUser.getIdentity();
            DataCenter.routeUNAME = DataCenter.currentUser.getUsername();
            NavHostFragment.findNavController(getParentFragment()).navigate(R.id.go_to_user_tracking);
        });

        btnDistance.setOnClickListener(v -> {
            DataManager.Instance().UpdateUserDistance(Integer.parseInt(edtDistance.getText().toString()));
            UnfocusElement();
        });
    }

    void UnfocusElement()
    {
        edtDistance.setFocusableInTouchMode(false);
        edtDistance.setFocusable(false);
        edtDistance.setFocusableInTouchMode(true);
        edtDistance.setFocusable(true);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mViewModel.getListCases().observe(this, cases -> {
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
                        .clickable(true)
                        .center(new LatLng(latitude, logtitude))
                        .radius(20)
                        .strokeColor(strokeColor)
                        .fillColor(Color.GREEN));

                circle.setTag(c);
            }

            Log.d("CASE", "your location: " + DataCenter.currentLocation.getLatitude() + ":"+ DataCenter.currentLocation.getLongitude());
            Circle circle = googleMap.addCircle(new CircleOptions()
                    .center(new LatLng(DataCenter.currentLocation.getLatitude(), DataCenter.currentLocation.getLongitude()))
                    .radius(20)
                    .strokeColor(Color.BLACK)
                    .fillColor(Color.WHITE));
        });

        CameraPosition liberty = CameraPosition.builder()
                .target(new LatLng(DataCenter.currentLocation.getLatitude(), DataCenter.currentLocation.getLongitude()))
                .zoom(16)
                .bearing(0)
                .tilt(45)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(liberty));

        googleMap.setOnCircleClickListener(this);
    }

    @Override
    public void onCircleClick(Circle circle) {
        Case c = (Case) circle.getTag();
        //Toast.makeText(getContext(), c.getName(), Toast.LENGTH_SHORT).show();

        mappingDialog();

        displayName.setText("Name: " + c.getName());
        identify.setText("ID: " + c.getUser());
        time.setText("Begin time: " + c.getBegin_time());
        level.setText("Case level: " + c.getF());

        caseInfoDialog.show();
    }

    Boolean mapped = false;
    ImageButton closeBtn;
    TextView displayName;
    TextView identify;
    TextView level;
    TextView time;

    private void mappingDialog() {
        if (mapped)
            return;

        caseInfoDialog.setContentView(R.layout.custom_case_info_popup);

        closeBtn = caseInfoDialog.findViewById(R.id.case_info_close);
        displayName = caseInfoDialog.findViewById(R.id.case_info_display_name);
        identify = caseInfoDialog.findViewById(R.id.case_info_identity);
        level = caseInfoDialog.findViewById(R.id.case_info_case_lv);
        time = caseInfoDialog.findViewById(R.id.case_info_begin_time);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caseInfoDialog.dismiss();
            }
        });
    }

}
