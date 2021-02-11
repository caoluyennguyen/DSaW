package com.corazon98.dsaw.ui.surveys;

import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.VideoView;

import com.corazon98.dsaw.R;
import com.corazon98.dsaw.adaptor.ReportResultAdaptor;
import com.corazon98.dsaw.data.DataCenter;
import com.corazon98.dsaw.data.DataManager;
import com.corazon98.dsaw.data.model.City;
import com.corazon98.dsaw.data.model.District;
import com.corazon98.dsaw.data.model.ReportModel;
import com.corazon98.dsaw.data.model.Ward;

import java.util.ArrayList;
import java.util.List;

public class ReportResultFragment extends Fragment {

    private ReportResultViewModel mViewModel;
    private ListView listView;
    private ReportResultAdaptor adaptor;
    private List<ReportModel> lsReport = new ArrayList<>();

    private ArrayList<City> lsCity = new ArrayList<>();
    private ArrayAdapter<String> adCityName;
    private int cityPos = 0;

    private ArrayList<District> lsDistrict = new ArrayList<>();
    private ArrayAdapter<String> adDistrictName;
    private int districtPos = 0;

    private List<Ward> lsWard = new ArrayList<>();
    private ArrayAdapter<String> adWardName;
    private int wardPos = 0;

    private Spinner mSpinCity;
    private Spinner mSpinDistrict;
    private Spinner mSpinWard;

    Dialog nagDialog;
    Button btnClose;
    ImageView ivPreview;
    VideoView vvPreview;
    RadioButton rbAll;
    RadioButton rbNewest;
    boolean sortingAll;

    public static ReportResultFragment newInstance() {
        return new ReportResultFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_result, container, false);
        listView = view.findViewById(R.id.report_result_lv);
        mSpinCity = view.findViewById(R.id.report_spinner);
        mSpinDistrict = view.findViewById(R.id.report_spinner_district);
        mSpinWard = view.findViewById(R.id.report_spinner_ward);

        nagDialog = new Dialog(getContext(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        nagDialog.setCancelable(false);
        nagDialog.setContentView(R.layout.dialog_full_image);
        btnClose = (Button)nagDialog.findViewById(R.id.btnIvClose);
        ivPreview = (ImageView)nagDialog.findViewById(R.id.iv_preview_image);
        vvPreview = (VideoView) nagDialog.findViewById(R.id.vv_preview_video);

        rbAll = view.findViewById(R.id.rb_all);
        rbNewest = view.findViewById(R.id.rb_newest);
        sortingAll = true;

        SetupEvent();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RegisterDataLiveListener();
    }

    private void RegisterDataLiveListener()
    {
        mViewModel = ViewModelProviders.of(this).get(ReportResultViewModel.class);

        mViewModel.getLsReport().observe(this, reportModels -> {
            lsReport.clear();

            for (ReportModel r : reportModels) {
                lsReport.add(r);
            }
            adaptor.notifyDataSetChanged();
        });

        /*adaptor = new ReportResultAdaptor(getContext(), lsReport);
        listView.setAdapter(adaptor);*/

        //get all city
        adCityName = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, lsCity);
        adCityName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinCity.setAdapter(adCityName);

        mViewModel.GetAllCity();
        mViewModel.getLsCity().observe(this, cities -> {
            lsCity.clear();
            lsCity.add(new City("0","Tất cả"));
            for (City a : cities) {
                lsCity.add(a);
                Log.e("Survey result fragment get city: ", a.getName());
            }

            if (lsCity.size() == 0)
                lsCity.add(new City());

            adCityName.notifyDataSetChanged();
        });

        adDistrictName = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
        mViewModel.getLsDistrict().observe(this, districts -> {
            lsDistrict.clear();
            for (District a : districts)
            {
                lsDistrict.add(a);
                Log.e("User fragment get districts: ", a.getName());
            }

            if (lsDistrict.size() == 0)
                lsDistrict.add(new District());


            adDistrictName.notifyDataSetChanged();
        });

        adWardName = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
        mViewModel.getLsWard().observe(this, wards -> {
            lsWard.clear();
            for (Ward a : wards)
            {
                lsWard.add(a);
                Log.e("User fragment get wards: ", a.getName());
            }

            if (lsWard.size() == 0)
                lsWard.add(new Ward());

            adWardName.notifyDataSetChanged();
        });
    }

    private void SetupEvent()
    {
        mSpinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lsReport.clear();

                mViewModel.GetDistrictOfCity(lsCity.get(position).getCode());
                adDistrictName = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, lsDistrict);
                adDistrictName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinDistrict.setAdapter(adDistrictName);

                cityPos = position;
                //mViewModel.fetchData(DataCenter.surveyID, position - 1 , sortingAll);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                districtPos = position;

                mViewModel.GetWardOfDistrict(lsCity.get(cityPos).getCode(), lsDistrict.get(position).getCode());

                adWardName = new ArrayAdapter(getContext(), R.layout.custom_spinner_item, lsWard);
                adWardName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinWard.setAdapter(adWardName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinWard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wardPos = position;

                mViewModel.fetchData(DataCenter.surveyID, cityPos - 1, districtPos, position , sortingAll);
                adaptor = new ReportResultAdaptor(getContext(),lsReport);
                listView.setAdapter(adaptor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnClose.setOnClickListener(arg0 -> {
            nagDialog.dismiss();
            vvPreview.stopPlayback();
            ivPreview.setVisibility(View.GONE);
            vvPreview.setVisibility(View.GONE);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            DataManager.Instance().fetchPhoto(lsReport.get(position).getImageUrl(), ivPreview,"report", vvPreview);
            nagDialog.show();
        });

        rbAll.setOnClickListener(v -> {
            sortingAll = true;
            mViewModel.fetchData(DataCenter.surveyID, cityPos - 1, districtPos, wardPos, sortingAll);
        });

        rbNewest.setOnClickListener(v -> {
            sortingAll = false;
            mViewModel.fetchData(DataCenter.surveyID, cityPos - 1, districtPos, wardPos, sortingAll);
        });
    }
}
