package com.corazon98.dsaw.ui.surveys;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.corazon98.dsaw.R;
import com.corazon98.dsaw.adaptor.SurveyAdaptor;
import com.corazon98.dsaw.data.DataCenter;
import com.corazon98.dsaw.data.model.SurveyModel;

import java.util.List;

public class AdminSurveyListFragment extends Fragment {

    private AdminSurveyListViewModel mViewModel;

    private ListView lvSurvey;

    private SurveyAdaptor adaptor;

    public static AdminSurveyListFragment newInstance() {
        return new AdminSurveyListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_survey_list, container, false);
        lvSurvey = view.findViewById(R.id.admin_survey_lv);
        setupAction();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AdminSurveyListViewModel.class);

        mViewModel.getLsSurvey().observe(this, new Observer<List<SurveyModel>>() {
            @Override
            public void onChanged(List<SurveyModel> surveyModels) {
                adaptor = new SurveyAdaptor(getContext(), surveyModels);
                lvSurvey.setAdapter(adaptor);
            }
        });
    }

    private void setupAction() {
        lvSurvey.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(),"did click at:" + mViewModel.getLsSurvey().getValue().get(position).getId(),Toast.LENGTH_SHORT).show();
                SurveyModel survey= mViewModel.getLsSurvey().getValue().get(position);
                DataCenter.surveyID = survey.getId();

                if (survey.getType().equals("report"))
                    NavHostFragment.findNavController(getParentFragment()).navigate(R.id.go_to_report_result);
                else
                    NavHostFragment.findNavController(getParentFragment()).navigate(R.id.go_to_survey_result);
            }
        });
    }
}
