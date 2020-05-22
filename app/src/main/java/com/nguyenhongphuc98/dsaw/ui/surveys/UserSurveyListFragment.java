package com.nguyenhongphuc98.dsaw.ui.surveys;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.Utils;
import com.nguyenhongphuc98.dsaw.adaptor.SurveyAdaptor;
import com.nguyenhongphuc98.dsaw.data.DataCenter;
import com.nguyenhongphuc98.dsaw.data.model.SurveyModel;
import com.nguyenhongphuc98.dsaw.ui.medical_report.PersonallReport;

import java.util.List;

public class UserSurveyListFragment extends Fragment {

    enum SurveyType{
        KBYT,
        KBNT,
        BC
    }

    private UserSurveyListViewModel mViewModel;

    private View viewKBYT;

    private View viewKBNT;

    private View viewBC;

    private ListView lvSurvey;

    private SurveyAdaptor kbytAdaptor;
    private SurveyAdaptor kbntAdaptor;
    private SurveyAdaptor bcAdaptor;

    SurveyType selectedType = SurveyType.KBYT;

    public static UserSurveyListFragment newInstance() {
        return new UserSurveyListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_survey_list_fragment, container, false);
        setupView(view);
        setupAction();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UserSurveyListViewModel.class);

        mViewModel.getlistKBYT().observe(this, new Observer<List<SurveyModel>>() {
            @Override
            public void onChanged(List<SurveyModel> surveyModels) {
                kbytAdaptor = new SurveyAdaptor(getContext(), surveyModels);
                if (selectedType == SurveyType.KBYT)
                    lvSurvey.setAdapter(kbytAdaptor);
            }
        });

        mViewModel.getlistKBNT().observe(this, new Observer<List<SurveyModel>>() {
            @Override
            public void onChanged(List<SurveyModel> surveyModels) {
                kbntAdaptor = new SurveyAdaptor(getContext(), surveyModels);
            }
        });

        mViewModel.getlistBC().observe(this, new Observer<List<SurveyModel>>() {
            @Override
            public void onChanged(List<SurveyModel> surveyModels) {
                  bcAdaptor = new SurveyAdaptor(getContext(), surveyModels);
            }
        });

    }

    private void setupView(View view) {
        viewKBYT = view.findViewById(R.id.tv_survey_kbyt);
        viewKBNT = view.findViewById(R.id.tv_survey_kbytnt);
        viewBC = view.findViewById(R.id.tv_survey_bc);
        lvSurvey = view.findViewById(R.id.user_survey_lv);

    }

    private void setupAction() {
        viewKBYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedType = SurveyType.KBYT;
                lvSurvey.setAdapter(kbytAdaptor);
            }
        });

        viewKBNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedType = SurveyType.KBNT;
                lvSurvey.setAdapter(kbntAdaptor);

            }
        });

        viewBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedType = SurveyType.BC;
                lvSurvey.setAdapter(bcAdaptor);
            }
        });

        lvSurvey.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<SurveyModel> surveys;
                if (selectedType == SurveyType.KBYT)
                    surveys = mViewModel.getlistKBYT().getValue();
                else
                    if (selectedType == SurveyType.KBNT)
                        surveys = mViewModel.getlistKBNT().getValue();
                    else surveys = mViewModel.getlistBC().getValue();

                Toast.makeText(getContext(),"did click at:" + surveys.get(position).getId(),Toast.LENGTH_SHORT).show();
                DataCenter.surveyID = surveys.get(position).getId();
                Utils.replaceFragment(new PersonallReport());
            }
        });
    }
}
