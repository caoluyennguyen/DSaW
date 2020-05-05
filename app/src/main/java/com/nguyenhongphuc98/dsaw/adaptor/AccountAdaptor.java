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

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.data.model.Account;
import com.nguyenhongphuc98.dsaw.data.model.Case;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountAdaptor extends ArrayAdapter {
    Context context;
    List<Account> lsUsers;
    Map<String,String> lsAreas;

    PopupMenu menu;

    String selectedUserID = "";

    public AccountAdaptor(@NonNull Context context, List<Account> ls) {
        super(context, R.layout.custom_case_row,ls);
        this.context=context;
        lsUsers =ls;
    }
    
    //call this method before setup anything else
    public void fillData(View view) {
        //load all area from db
        lsAreas = new HashMap<>();
        lsAreas.put("Hà Tĩnh","area1");
        lsAreas.put("Đà Nẵng","area2");
        lsAreas.put("Bến Tre","area3");
        lsAreas.put("Hà Nội","area4");
        lsAreas.put("Hà Giang","area5");
        lsAreas.put("Sơn Tĩnh","area6");

        menu = new PopupMenu(context, view);
        for (Map.Entry me : lsAreas.entrySet()) {
            menu.getMenu().add(me.getKey().toString()); // menus items
        }

        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(context,"did assign: "+selectedUserID+ "for "+lsAreas.get(item.getTitle()),Toast.LENGTH_SHORT).show();
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
                menu.show();
                selectedUserID = e.getIdentity();
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
