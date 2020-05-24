package com.nguyenhongphuc98.dsaw.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.lifecycle.MutableLiveData;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.Account;
import com.nguyenhongphuc98.dsaw.data.model.Area;
import com.nguyenhongphuc98.dsaw.data.model.Case;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountAdaptor extends ArrayAdapter {
    Context context;
    List<Account> lsUsers;
    public MutableLiveData<List<Area>> lsAreas;

    PopupMenu menu;

    String selectedUserID = "";

    public AccountAdaptor(@NonNull Context context, List<Account> ls) {
        super(context, R.layout.custom_case_row,ls);
        this.context=context;
        lsUsers =ls;
        lsAreas = new MutableLiveData<>();
        fillData();
    }
    
    //call this method before setup anything else
    public void fillData() {
        //load all area from db
        DataManager.Instance().fetchAllAreas(lsAreas);
    }

    public void setupMenu(View view) {
        menu = new PopupMenu(context, view);
        int i = 0;
        for (Area a : lsAreas.getValue()) {

            //menu.getMenu().add(a.getName()); // menus items
            menu.getMenu().add(Menu.NONE, Menu.NONE, i++,a.getName());
        }

        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //Toast.makeText(context, "did assign: " + selectedUserID + "for " + lsAreas.getValue().get(item.getItemId()).getId(), Toast.LENGTH_SHORT).show();
                for (Account e: lsUsers) {
                    if (e.getIdentity().equals(selectedUserID)) {
                        //user or manager
                        if (e.getRole().equals("user")) {
                            e.setRole("manager");
                            e.setArea_management(lsAreas.getValue().get(item.getOrder()).getId());
                            DataManager.Instance().updateACcount(e);
                        }
                    }
                }
                return true;
            }
        });
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewRow= convertView;

        if(viewRow==null){
            viewRow=layoutInflater.inflate(R.layout.custom_case_row,parent,false);
            AccountAdaptor.ViewHolder holder = new AccountAdaptor.ViewHolder();
            holder.name = viewRow.findViewById(R.id.case_item_name);
            holder.cmnd = viewRow.findViewById(R.id.case_item_cmnd);
            holder.action =viewRow.findViewById(R.id.case_item_f);

            viewRow.setTag(holder);
        }

        final AccountAdaptor.ViewHolder viewHolder= (AccountAdaptor.ViewHolder) viewRow.getTag();


        final Account e= lsUsers.get(position);
        viewHolder.name.setText(e.getUsername());
        viewHolder.cmnd.setText(e.getIdentity());

        if (e.getRole().equals("user")) {
            viewHolder.action.setText("Promote");
            viewHolder.action.setBackgroundResource(R.drawable.background_button_f1);
        } else {
            viewHolder.action.setText("Demote");
            viewHolder.action.setBackgroundResource(R.drawable.background_orange);
        }

        //set action to this row
        viewHolder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e.getRole().equals("manager")) {
                    e.setRole("user");
                    e.setArea_management("null");
                    DataManager.Instance().updateACcount(e);
                } else {
                    menu.show();
                    selectedUserID = e.getIdentity();
                }
            }
        });

        return viewRow;
    }

    public static class ViewHolder{
        TextView name;
        TextView cmnd;
        Button action;
    }
}
