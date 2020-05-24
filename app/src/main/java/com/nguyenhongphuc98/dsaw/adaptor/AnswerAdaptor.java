package com.nguyenhongphuc98.dsaw.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.data.model.Answer;

import java.util.List;

public class AnswerAdaptor extends ArrayAdapter {
    Context context;
    List<Answer> lsAnswer;
    String typeOfQuestion;

    public AnswerAdaptor(@NonNull Context context, List<Answer> ls){
        super(context, R.layout.custom_survey_multichoice_answer, ls);
        this.context = context;
        lsAnswer = ls;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewRow = convertView;

        if (viewRow == null){
            viewRow = layoutInflater.inflate(R.layout.custom_survey_multichoice_answer,parent,false);
            AnswerAdaptor.ViewHolder holder = new AnswerAdaptor.ViewHolder();
            holder.answer = viewRow.findViewById(R.id.answer);

            viewRow.setTag(holder);
        }

        AnswerAdaptor.ViewHolder viewHolder = (AnswerAdaptor.ViewHolder) viewRow.getTag();
        Answer e = lsAnswer.get(position);
        viewHolder.answer.setText(e.getTextAnswer());
        return viewRow;
    }

    public static class ViewHolder{
        CheckedTextView answer;
    }
}
