package com.corazon98.dsaw.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.corazon98.dsaw.R;
import com.corazon98.dsaw.data.model.AnswerViewModel;

import java.util.List;

public class SurveyResultAdaptor extends ArrayAdapter {
    Context context;
    List<AnswerViewModel> answers;

    public SurveyResultAdaptor(@NonNull Context context, List<AnswerViewModel> ls) {
        super(context, R.layout.custom_survey_result_row,ls);
        this.context=context;
        answers =ls;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewRow= convertView;

        if(viewRow==null){
            viewRow=layoutInflater.inflate(R.layout.custom_survey_result_row,parent,false);
            ViewHolder holder=new ViewHolder();

            holder.tvQuestion =viewRow.findViewById(R.id.survey_result_question_item);
            holder.answerListLayout =viewRow.findViewById(R.id.survey_result_frame_subitem);

            viewRow.setTag(holder);
        }

        ViewHolder viewHolder= (ViewHolder) viewRow.getTag();


        AnswerViewModel e = answers.get(position);
        int p = position + 1;
        viewHolder.tvQuestion.setText("Câu "+ p + ": " + e.getQuestionTitle());


        for(int i = 0; i< answers.get(position).getAnswers().size(); i++) {
            View subRow = layoutInflater.inflate(R.layout.custom_survey_result_sub_item_row,null);

            TextView dotView = subRow.findViewById(R.id.survey_result_dot_subitem_tv);
            TextView titleView = subRow.findViewById(R.id.survey_result_title_subitem_tv);

            titleView.setText(answers.get(position).getAnswers().get(i));
            viewHolder.answerListLayout.addView(subRow);
        }

        return viewRow;
    }

    public static class ViewHolder{
        TextView tvQuestion;
        LinearLayout answerListLayout;
    }
}
