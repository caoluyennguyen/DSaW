package com.nguyenhongphuc98.dsaw.ui.surveys;

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

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.Utils;
import com.nguyenhongphuc98.dsaw.adaptor.SurveyAdaptor;
import com.nguyenhongphuc98.dsaw.data.DataCenter;
import com.nguyenhongphuc98.dsaw.data.model.SurveyModel;

import java.util.List;

public class AdminSurveyListFragment extends Fragment {

    private AdminSurveyListViewModel mViewModel;

    private ListView lvSurvey;

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
        mViewModel.setContext(getContext());
        mViewModel.getAdaptor().observe(this, new Observer<SurveyAdaptor>() {
            @Override
            public void onChanged(SurveyAdaptor surveyAdaptor) {
                lvSurvey.setAdapter(surveyAdaptor);
            }
        });
    }

    private void setupAction() {
        lvSurvey.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(),"did click at:" + mViewModel.getListSurvey().get(position).getId(),Toast.LENGTH_SHORT).show();
                String sid = mViewModel.getListSurvey().get(position).getId();
//                SurveyResultFragment f = new SurveyResultFragment();
//                f.setSurveyId(sid);
//                Utils.replaceFragment(f);
                DataCenter.surveyID = sid;
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.go_to_survey_result);
            }
        });
    }
}
