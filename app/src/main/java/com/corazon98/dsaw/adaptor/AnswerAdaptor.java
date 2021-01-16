package com.corazon98.dsaw.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.corazon98.dsaw.R;

import java.util.List;

public class AnswerAdaptor extends ArrayAdapter {
    Context context;
    List<String> lsAnswer;

    public AnswerAdaptor(@NonNull Context context, List<String> ls){
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
        String choice = lsAnswer.get(position);
        viewHolder.answer.setText(choice);
        return viewRow;
    }

    public static class ViewHolder{
        CheckedTextView answer;
    }
}
