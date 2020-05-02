package com.nguyenhongphuc98.dsaw.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.data.model.Case;
import com.nguyenhongphuc98.dsaw.data.model.News;

import java.util.List;

public class CaseAdaptor extends ArrayAdapter {

    Context context;
    List<Case> lsCases;

    public CaseAdaptor(@NonNull Context context, List<Case> ls) {
        super(context, R.layout.custom_news_row,ls);
        this.context=context;
        lsCases =ls;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewRow= convertView;

        if(viewRow==null){
            viewRow=layoutInflater.inflate(R.layout.custom_case_row,parent,false);
            ViewHolder holder = new ViewHolder();
            holder.name = viewRow.findViewById(R.id.case_item_name);
            holder.cmnd = viewRow.findViewById(R.id.case_item_cmnd);
            holder.f =viewRow.findViewById(R.id.case_item_f);

            viewRow.setTag(holder);
        }

        ViewHolder viewHolder= (ViewHolder) viewRow.getTag();

        //viewHolder.cover.setImageResource(R.drawable.ninja_avt);

        Case e= lsCases.get(position);
        viewHolder.name.setText(e.getName());
        viewHolder.cmnd.setText(e.getUser());
        viewHolder.f.setText(e.getF());

        if (!e.getF().equals("F0")) {
            viewHolder.f.setBackgroundResource(R.drawable.background_button_f1);
        }

        return viewRow;
    }

    public static class ViewHolder{
        TextView name;
        TextView cmnd;
        Button f;
    }
}
