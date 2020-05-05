package com.nguyenhongphuc98.dsaw.ui.admin;

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
import com.nguyenhongphuc98.dsaw.adaptor.AccountAdaptor;

public class UserManagerFragment extends Fragment {

    private UserManagerViewModel mViewModel;
    private Button btnFilter;
    private EditText etSearch;
    private ListView lvUsers;

    PopupMenu popup;

    public static UserManagerFragment newInstance() {
        return new UserManagerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_manager, container, false);
        setupView(view);
        setupAction();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UserManagerViewModel.class);
        mViewModel.setContext(getContext());
        mViewModel.getAdaptor().observe(this, new Observer<AccountAdaptor>() {
            @Override
            public void onChanged(AccountAdaptor accountAdaptor) {
                accountAdaptor.fillData(etSearch);
                lvUsers.setAdapter(accountAdaptor);
            }
        });

    }

    void setupView(View view) {
        btnFilter = view.findViewById(R.id.usermanager_filter_btn);
        etSearch = view.findViewById(R.id.usermanager_search_et);
        lvUsers = view.findViewById(R.id.usermanager_user_lv);

        popup = new PopupMenu(getContext(), btnFilter);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.type_user_menu, popup.getMenu());
    }

    void setupAction() {
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
                mViewModel.filterByNameOrCMND(s.toString());
            }
        });

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String role = item.getTitle().toString();
                if (role.equals("Người dùng"))
                    mViewModel.filterByRole("user");
                else
                    mViewModel.filterByRole("manager");
                return true;
            }
        });
    }
}
