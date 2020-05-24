package com.nguyenhongphuc98.dsaw.ui.cases;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
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
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.Account;
import com.nguyenhongphuc98.dsaw.data.model.Case;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaseFragment extends Fragment {

    private CaseViewModel mViewModel;

    private Spinner spinner;
    ArrayAdapter<CharSequence> fAdapter;

    private ListView lvCase;
    private Button btnUpdate;
    private EditText tvCmnd;
    private TextView tvSeeAll;

    ViewCaseFragment viewCaseFragment;

    CaseAdaptor adaptor;
    public List<Case> lsCases;

    String fx;

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

        adaptor = new CaseAdaptor(getContext(), lsCases);
        lvCase.setAdapter(adaptor);

        mViewModel.getLiveAccount().observe(this, new Observer<Account>() {
            @Override
            public void onChanged(Account account) {

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm");
                Date date = new Date();
                String t = dateFormat.format(date);
                Log.d("TAGGG", "onChanged: date"+ t);

                Case c = new Case(account.getArea(),
                        t,
                        "zzzzzzzzzzzzzzzzzz",
                         fx,
                        "case01",
                        "22356,44426",
                         account.getIdentity(),
                         account.getUsername());
                DataManager.Instance().insertCase(c);
                lsCases.add(c);
                adaptor.notifyDataSetChanged();
            }
        });

//        mViewModel.getliveListCases().observe(this, new Observer<List<Case>>() {
//            @Override
//            public void onChanged(List<Case> cases) {
////                mViewModel.lsCases.clear();
////                for (Case c : mViewModel.getliveListCases().getValue()) {
////                    mViewModel.lsCases.add(c);
////                }
//                adaptor.notifyDataSetChanged();
//            }
//        });
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

        lsCases = new ArrayList<>();
    }

    private void setupACtion() {

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getContext(),"Please choose object",Toast.LENGTH_SHORT).show();
                    return;
                }

                String f = spinner.getSelectedItem().toString().split("-")[0].split(" ")[0];
                mViewModel.updateCase(tvCmnd.getText().toString(),f);
                fx = f;
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
