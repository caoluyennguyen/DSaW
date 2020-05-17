package com.nguyenhongphuc98.dsaw.ui.cases;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.Utils;
import com.nguyenhongphuc98.dsaw.adaptor.CaseAdaptor;

public class CaseFragment extends Fragment {

    private CaseViewModel mViewModel;

    private Spinner spinner;
    ArrayAdapter<CharSequence> fAdapter;

    private ListView lvCase;
    private Button btnUpdate;
    private EditText tvCmnd;
    private TextView tvSeeAll;

    ViewCaseFragment viewCaseFragment;

    public static CaseFragment newInstance() {
        return new CaseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_case, container, false);
        setupView(view);

        setupACtion();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CaseViewModel.class);
        mViewModel.setContext(getContext());
        mViewModel.getAdaptor().observe(this, new Observer<CaseAdaptor>() {
            @Override
            public void onChanged(CaseAdaptor caseAdaptor) {
                lvCase.setAdapter(caseAdaptor);
            }
        });
    }

    private void setupView(View view) {

        viewCaseFragment = new ViewCaseFragment();

        spinner = view.findViewById(R.id.case_spinner_level);
        tvCmnd = view.findViewById(R.id.case_cmnd_tf);
        lvCase = view.findViewById(R.id.case_recently_lv);
        btnUpdate = view.findViewById(R.id.case_update_btn);
        tvSeeAll = view.findViewById(R.id.case_see_all_tv);

        ArrayAdapter<CharSequence> fAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.case_array, android.R.layout.simple_spinner_item);
        fAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(fAdapter);
    }

    private void setupACtion() {

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f = spinner.getSelectedItem().toString().split("-")[0].split(" ")[0];
                mViewModel.updateCase(tvCmnd.getText().toString(),f);
            }
        });

        tvSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Utils.replaceFragment(viewCaseFragment);
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.go_to_view_case_fragment);
            }
        });
    }
}
