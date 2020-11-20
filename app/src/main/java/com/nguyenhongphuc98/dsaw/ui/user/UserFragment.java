package com.nguyenhongphuc98.dsaw.ui.user;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.City;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class UserFragment extends Fragment {

    private UserViewModel mViewModel;


    private TextView mTextName;
    private TextView mTextCMND;
    private TextView mTextDayofBirth;
    private TextView mTextContact;
    private Button mBtnUpdate;
    private CheckedTextView checkedTextView;
    private Spinner mSpinCity;
    String[] city = { "Da Nang", "Ha Noi", "Ho Chi Minh", "Da Lat", "Quang Ngai"};
    private ArrayList<String> lsCityName = new ArrayList<>();
    private ArrayAdapter<String> adCityName;
    private ArrayList<City> lsCity = new ArrayList<>();


    final Calendar myCalendar = Calendar.getInstance();

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormat = "dd/MM/yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            mTextDayofBirth.setText(sdf.format(myCalendar.getTime()));
        }

    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(UserViewModel.class);

        View root = inflater.inflate(R.layout.fragment_user, container, false);
        InitComponent(root);
        InitEvent();
        RegisterDataLiveListener();
        UnfocusElement();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        mViewModel.GetUser(mTextName, mTextCMND, mTextDayofBirth, mTextContact);

        adCityName = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, lsCityName);
        adCityName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinCity.setAdapter(adCityName);

        mViewModel.GetAllCity();

        mViewModel.getLsCity().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
                lsCity.clear();
                lsCityName.clear();
                for (City a : cities) {
                    lsCity.add(a);
                    lsCityName.add(a.getName());
                    Log.e("User fragment", a.getName());
                }

                if (lsCityName.size() == 0)
                    lsCityName.add("Da Nang");

                adCityName.notifyDataSetChanged();
            }
        });

    }

    public void InitComponent(View view) {
        mTextName = view.findViewById(R.id.txtName);
        mTextCMND = view.findViewById(R.id.txtCMND);
        mTextDayofBirth = view.findViewById(R.id.txtDayOfBirth);
        mTextContact = view.findViewById(R.id.txtContact);
        mBtnUpdate = view.findViewById(R.id.user_update_btn);
        checkedTextView = view.findViewById(R.id.ctxAgreement);

        mSpinCity = view.findViewById(R.id.spinCity);
    }

    public void InitEvent()
    {
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkedTextView.isChecked()) {
                    mViewModel.setmName(mTextName.getText().toString());
                    mViewModel.setmCMND(mTextCMND.getText().toString());
                    mViewModel.setmDayOfBirth(mTextDayofBirth.getText().toString());
                    mViewModel.setmContact(mTextContact.getText().toString());
                    mViewModel.UpdateUser(mTextName.getText().toString(), mTextCMND.getText().toString(), mTextDayofBirth.getText().toString(), mTextContact.getText().toString());
                    Toast.makeText(getContext(), "Bạn vừa mới thay đổi thông tin cá nhân", Toast.LENGTH_LONG).show();
                    UnfocusElement();
                }
                else Toast.makeText(getContext(), "Vui lòng xác nhận cam kết", Toast.LENGTH_LONG).show();

            }
        });

        mTextDayofBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkedTextView.isChecked()) checkedTextView.setChecked(false);
                else checkedTextView.setChecked(true);
            }
        });

        mSpinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), lsCityName.get(position) , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void RegisterDataLiveListener() {
        mViewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextName.setText(String.valueOf(s));
            }
        });
        mViewModel.getCMND().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextCMND.setText(String.valueOf(s));
            }
        });
        mViewModel.getDayOfBirth().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextDayofBirth.setText(String.valueOf(s));
            }
        });
        mViewModel.getContact().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextContact.setText(String.valueOf(s));
            }
        });
    }

    public void UnfocusElement()
    {
        mTextName.setFocusableInTouchMode(false);
        mTextName.setFocusable(false);
        mTextName.setFocusableInTouchMode(true);
        mTextName.setFocusable(true);
        mTextCMND.setFocusableInTouchMode(false);
        mTextCMND.setFocusable(false);
        mTextCMND.setFocusableInTouchMode(true);
        mTextCMND.setFocusable(true);
        mTextDayofBirth.setFocusableInTouchMode(false);
        mTextDayofBirth.setFocusable(false);
        mTextDayofBirth.setFocusableInTouchMode(true);
        mTextDayofBirth.setFocusable(true);
        mTextContact.setFocusableInTouchMode(false);
        mTextContact.setFocusable(false);
        mTextContact.setFocusableInTouchMode(true);
        mTextContact.setFocusable(true);
    }
}
