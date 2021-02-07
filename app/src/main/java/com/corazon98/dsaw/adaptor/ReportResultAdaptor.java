package com.corazon98.dsaw.adaptor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.corazon98.dsaw.R;
import com.corazon98.dsaw.data.DataManager;
import com.corazon98.dsaw.data.model.ReportModel;

import java.util.List;

public class ReportResultAdaptor extends ArrayAdapter {
    Context context;
    List<ReportModel> answers;

    public ReportResultAdaptor(@NonNull Context context, List<ReportModel> ls) {
        super(context, R.layout.custom_report_result_row,ls);
        this.context=context;
        answers =ls;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewRow= convertView;

        if(viewRow==null){
            viewRow=layoutInflater.inflate(R.layout.custom_report_result_row,parent,false);
            ViewHolder holder=new ViewHolder();

            holder.dateSubmit =viewRow.findViewById(R.id.report_date_submit);
            holder.tvQuestion =viewRow.findViewById(R.id.report_result_count);
            holder.answerListLayout =viewRow.findViewById(R.id.report_result_frame_subitem);
            holder.imageView = viewRow.findViewById(R.id.report_result_img);

            viewRow.setTag(holder);
        }

        ViewHolder viewHolder= (ViewHolder) viewRow.getTag();


        ReportModel e = answers.get(position);
        int p = position + 1;
        viewHolder.tvQuestion.setText("Câu trả lời thứ "+ p);
        viewHolder.dateSubmit.setText(e.getDateSubmit());

        viewHolder.answerListLayout.removeAllViews();
        for(int i = 0; i< answers.get(position).getLsAnswers().size(); i++) {
//            View subRow = layoutInflater.inflate(R.layout.custom_survey_result_sub_item_row,null);
//
//            TextView dotView = subRow.findViewById(R.id.survey_result_dot_subitem_tv);
//            TextView titleView = subRow.findViewById(R.id.survey_result_title_subitem_tv);
//
//            titleView.setText(answers.get(position).getAnswers().get(i));

            TextView answeri = new TextView(context);
            answeri.setTextColor(Color.WHITE);
            answeri.setText(answers.get(position).getLsAnswers().get(i));
            viewHolder.answerListLayout.addView(answeri);
        }

        //viewHolder.imageView.setImageResource(R.drawable.manager_48);
        DataManager.Instance().fetchPhoto(answers.get(position).getImageUrl(), viewHolder.imageView,"report");

        return viewRow;
    }

    public static class ViewHolder{
        TextView dateSubmit;
        TextView tvQuestion;
        LinearLayout answerListLayout;
        ImageView imageView;
    }
}
