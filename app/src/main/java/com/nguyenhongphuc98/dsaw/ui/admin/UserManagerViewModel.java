package com.nguyenhongphuc98.dsaw.ui.admin;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.adaptor.AccountAdaptor;
import com.nguyenhongphuc98.dsaw.adaptor.CaseAdaptor;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.Account;
import com.nguyenhongphuc98.dsaw.data.model.Case;

import java.util.ArrayList;
import java.util.List;

public class UserManagerViewModel extends ViewModel {

    private MutableLiveData<List<Account>> lsAccount;
    public List<Account> lsUsers;

    public UserManagerViewModel() {
        lsAccount = new MutableLiveData<>();
        lsUsers = new ArrayList<>();
        fetchUser();
    }

    public LiveData<List<Account>> getLisAccount() {
        return lsAccount;
    }


    public void fetchUser() {

        DataManager.Instance().fetchAllAccount(lsAccount);

        //load list user from db
//        lsUsers = new ArrayList<>();
//        lsUsers.add(new Account(
//                "22/11/1998",
//                "184335348",
//                "123456",
//                "0366272703",
//                "user",
//                "phucbua",
//                "area1",
//                "null"));
//        lsUsers.add(new Account(
//                "22/11/1998",
//                "145663256",
//                "123456",
//                "0366272703",
//                "manager",
//                "Nguyen Phuc Thien Kim",
//                "area2",
//                "area2"));
//        lsUsers.add(new Account(
//                "22/11/1998",
//                "184335348",
//                "123456",
//                "0366272703",
//                "user",
//                "phucbua",
//                "area1",
//                "null"));
//        lsUsers.add(new Account(
//                "22/11/1998",
//                "184335348",
//                "123456",
//                "0366272703",
//                "manager",
//                "Nguyen Hong Pink",
//                "area1",
//                "area3"));
//        lsUsers.add(new Account(
//                "22/11/1998",
//                "184335348",
//                "123456",
//                "0366272703",
//                "user",
//                "Hong Ngoc",
//                "area1",
//                "null"));
//
//        lsAccount.setValue(lsUsers);
    }


    public List<Account> filterByRole(String role) {
        List<Account> lsUserFiltered = new ArrayList<>();
        for (Account c: lsAccount.getValue()) {
            if (role.equals(c.getRole())) {
                lsUserFiltered.add(c);
            }
        }

        //it mean select see all
        if (lsUserFiltered.size() == 0)
            lsUserFiltered = lsAccount.getValue();

       return lsUserFiltered;
    }

    public List<Account> filterByNameOrCMND(String searchText) {

        List<Account> lsUserFiltered = new ArrayList<>();
        String nomorlizeText = searchText.toLowerCase();

        if (searchText.isEmpty() || searchText.length() == 0)
            lsUserFiltered = lsAccount.getValue();
        else  {
            for (Account c: lsAccount.getValue()) {
                if (c.getUsername().toLowerCase().contains(nomorlizeText) || c.getIdentity().contains(searchText)) {
                    lsUserFiltered.add(c);
                }
            }
        }

       return  lsUserFiltered;
    }
}
