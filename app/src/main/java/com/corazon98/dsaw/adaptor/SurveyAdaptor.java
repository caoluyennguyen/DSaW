package com.corazon98.dsaw.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.corazon98.dsaw.R;
import com.corazon98.dsaw.data.model.SurveyModel;

import java.util.List;

public class SurveyAdaptor  extends ArrayAdapter {

    Context context;
    List<SurveyModel> lsSurvey;

    public SurveyAdaptor(@NonNull Context context, List<SurveyModel> ls) {
        super(context, R.layout.custom_survey_row,ls);
        this.context=context;
        lsSurvey =ls;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewRow= convertView;

        if(viewRow==null){
            viewRow=layoutInflater.inflate(R.layout.custom_survey_row,parent,false);
            SurveyAdaptor.ViewHolder holder=new SurveyAdaptor.ViewHolder();

            holder.count =viewRow.findViewById(R.id.survey_item_count_tv);
            holder.name =viewRow.findViewById(R.id.survey_item_name_tv);

            viewRow.setTag(holder);
        }

        SurveyAdaptor.ViewHolder viewHolder= (SurveyAdaptor.ViewHolder) viewRow.getTag();


        SurveyModel e= lsSurvey.get(position);
        viewHolder.count.setText(e.getCount());
        viewHolder.name.setText(e.getName());

        return viewRow;
    }

    public static class ViewHolder{
        TextView count;
        TextView name;
    }
}
