package com.corazon98.dsaw.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.corazon98.dsaw.R;
import com.corazon98.dsaw.data.model.Question;

import java.util.List;

public class MultichoiceQuestionAdaptor extends ArrayAdapter {
    Context context;
    List<Question> lsQuestion;
    AnswerAdaptor mAdapter;

    public MultichoiceQuestionAdaptor(@NonNull Context context, List<Question> ls){
        super(context, R.layout.custom_survey_multichoice_question, ls);
        this.context = context;
        lsQuestion = ls;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewRow = convertView;

        if (viewRow == null){
            viewRow = layoutInflater.inflate(R.layout.custom_survey_multichoice_question,parent,false);
            MultichoiceQuestionAdaptor.ViewHolder holder = new MultichoiceQuestionAdaptor.ViewHolder();
            holder.number = viewRow.findViewById(R.id.number);
            holder.question = viewRow.findViewById(R.id.question);
            holder.listviewOfAnswer = viewRow.findViewById(R.id.list_view_of_answer);

            viewRow.setTag(holder);
        }

        ViewHolder viewHolder = (ViewHolder) viewRow.getTag();
        Question e = lsQuestion.get(position);
        viewHolder.number.setText("Câu " + e.getId() + ":");
        viewHolder.question.setText(e.getTitle());

        if (!lsQuestion.isEmpty()){
            for (int i=0; i < lsQuestion.get(position).getAnswers().size(); i++){
                //View subRow = layoutInflater.inflate(R.layout.custom_survey_multichoice_answer,null);
                //CheckedTextView answer = subRow.findViewById(R.id.answer);
                //answer.setText(lsQuestion.get(position).getAnswers().get(i));
                //viewHolder.listOfAnswer.addView(subRow);
            }
        }

        mAdapter = new AnswerAdaptor(getContext(), lsQuestion.get(position).getAnswers());
        viewHolder.listviewOfAnswer.setAdapter(mAdapter);

        return viewRow;
    }

    public static class ViewHolder{
        TextView number;
        TextView question;
        ListView listviewOfAnswer;
    }
}
