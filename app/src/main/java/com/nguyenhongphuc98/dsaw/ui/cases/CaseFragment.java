package com.nguyenhongphuc98.dsaw.ui.cases;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.nguyenhongphuc98.dsaw.R;

public class CaseFragment extends Fragment {

    private CaseViewModel mViewModel;

    private Spinner spinner;
    ArrayAdapter<CharSequence> fAdapter;

    public static CaseFragment newInstance() {
        return new CaseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_case, container, false);
        spinner = view.findViewById(R.id.case_spinner_level);

        ArrayAdapter<CharSequence> fAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.case_array, android.R.layout.simple_spinner_item);
        fAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(fAdapter);

        setupACtion();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CaseViewModel.class);
        // TODO: Use the ViewModel
    }

    private void setupACtion() {

    }
}
