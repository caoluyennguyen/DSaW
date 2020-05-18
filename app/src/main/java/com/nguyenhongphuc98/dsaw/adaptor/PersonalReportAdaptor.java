package com.nguyenhongphuc98.dsaw.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.data.model.Question;

import java.util.List;

public class PersonalReportAdaptor extends ArrayAdapter {
    Context context;
    List<Question> lsQuestion;

    public PersonalReportAdaptor(@NonNull Context context, List<Question> ls){
        super(context, R.layout.custom_survey_question, ls);
        this.context = context;
        lsQuestion = ls;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewRow = convertView;

        if (viewRow == null){
            viewRow = layoutInflater.inflate(R.layout.custom_survey_question,parent,false);
            PersonalReportAdaptor.ViewHolder holder = new PersonalReportAdaptor.ViewHolder();
            holder.number = viewRow.findViewById(R.id.number);
            holder.question = viewRow.findViewById(R.id.question);
            holder.listOfAnswer = viewRow.findViewById(R.id.list_of_answer);

            viewRow.setTag(holder);
        }

        ViewHolder viewHolder = (ViewHolder) viewRow.getTag();
        Question e = lsQuestion.get(position);
        viewHolder.number.setText("Câu " + e.getId() + ":");
        viewHolder.question.setText(e.getTitle());

        for (int i=0; i < lsQuestion.get(position).getAnswers().size(); i++){
            View subRow = layoutInflater.inflate(R.layout.custom_survey_answer,null);
            CheckedTextView answer = subRow.findViewById(R.id.answer);
            answer.setText(lsQuestion.get(position).getAnswers().get(i));
            viewHolder.listOfAnswer.addView(subRow);
        }

        return viewRow;
    }

    public static class ViewHolder{
        TextView number;
        TextView question;
        LinearLayout listOfAnswer;
    }
}
