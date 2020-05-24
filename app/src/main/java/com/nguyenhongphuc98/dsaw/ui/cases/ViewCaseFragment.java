package com.nguyenhongphuc98.dsaw.ui.cases;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.adaptor.CaseAdaptor;
import com.nguyenhongphuc98.dsaw.data.model.Case;

import java.util.List;

public class ViewCaseFragment extends Fragment {

    private ViewCaseViewModel mViewModel;
    private Button btnFilter;
    private EditText etSearch;
    private ListView lvCases;

    PopupMenu popup;

    private CaseAdaptor adaptor;

    public static ViewCaseFragment newInstance() {
        return new ViewCaseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_case, container, false);
        setupView(view);
        setupAction();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ViewCaseViewModel.class);

        adaptor = new CaseAdaptor(getContext(),mViewModel.ls);
        lvCases.setAdapter(adaptor);

        mViewModel.getListCases().observe(this, new Observer<List<Case>>() {
            @Override
            public void onChanged(List<Case> cases) {
                mViewModel.ls.clear();
                for (Case a : cases) {
                    mViewModel.ls.add(a);
                }
                adaptor.notifyDataSetChanged();
            }
        });
    }

    private void setupView(View view) {

        btnFilter = view.findViewById(R.id.view_case_filter_btn);
        etSearch = view.findViewById(R.id.view_case_search_et);
        lvCases = view.findViewById(R.id.view_case_case_lv);

        popup = new PopupMenu(getContext(), btnFilter);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.case_menu, popup.getMenu());
    }

    private void setupAction() {
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.show();
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Toast.makeText(getContext(),s.toString(),Toast.LENGTH_SHORT).show();
                mViewModel.ls.clear();
                for (Case a : mViewModel.filterByNameOrCMND(s.toString())) {
                    mViewModel.ls.add(a);
                }
                adaptor.notifyDataSetChanged();
            }
        });

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String f = item.getTitle().toString().split(" ")[1];
                mViewModel.ls.clear();
                for (Case a : mViewModel.filterByF(f)) {
                    mViewModel.ls.add(a);
                }
                adaptor.notifyDataSetChanged();
                return true;
            }
        });
    }
}
