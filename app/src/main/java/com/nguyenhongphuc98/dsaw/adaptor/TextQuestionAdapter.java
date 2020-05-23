package com.nguyenhongphuc98.dsaw.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.data.model.Question;

import java.util.List;

public class TextQuestionAdapter extends ArrayAdapter {
    Context context;
    List<Question> lsQuestion;

    public TextQuestionAdapter(@NonNull Context context, List<Question> ls){
        super(context, R.layout.custom_survey_text_question, ls);
        this.context = context;
        lsQuestion = ls;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewRow = convertView;

        if (viewRow == null){
            viewRow = layoutInflater.inflate(R.layout.custom_survey_text_question,parent,false);
            TextQuestionAdapter.ViewHolder holder = new TextQuestionAdapter.ViewHolder();
            holder.number = viewRow.findViewById(R.id.tqNumber);
            holder.question = viewRow.findViewById(R.id.tqQuestion);

            viewRow.setTag(holder);
        }

        TextQuestionAdapter.ViewHolder viewHolder = (TextQuestionAdapter.ViewHolder) viewRow.getTag();
        Question e = lsQuestion.get(position);
        viewHolder.number.setText("Câu " + e.getId() + ":");
        viewHolder.question.setText(e.getTitle());
        return viewRow;
    }

    public static class ViewHolder{
        TextView number;
        TextView question;
    }
}
