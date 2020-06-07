package com.nguyenhongphuc98.dsaw.adaptor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.data.model.Question;

import java.util.List;

public class QuestionAdapter extends ArrayAdapter {
    Context context;
    List<Question> lsQuestion;
    AnswerAdaptor mAdapter;

    public QuestionAdapter(@NonNull Context context, List<Question> ls){
        super(context, R.layout.custom_survey_multichoice_answer, ls);
        this.context = context;
        lsQuestion = ls;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewRow = convertView;

        if (lsQuestion.get(position).getType().equals("MT")){
            if (viewRow == null) {
                Log.e("DataManager", "Title of question was found is " + lsQuestion.get(position).getType());
                viewRow = layoutInflater.inflate(R.layout.custom_survey_multichoice_question,parent,false);

                QuestionAdapter.ViewHolder holder = new QuestionAdapter.ViewHolder();
                holder.number = viewRow.findViewById(R.id.number);
                holder.question = viewRow.findViewById(R.id.question);
                holder.listviewOfAnswer = viewRow.findViewById(R.id.listview_of_answer);
                holder.listOfAnswer = viewRow.findViewById(R.id.list_of_answer);

                viewRow.setTag(holder);
            }

            int numOfQuestion = position + 1;
            ViewHolder viewHolder = (ViewHolder) viewRow.getTag();
            Question e = lsQuestion.get(position);
            viewHolder.number.setText("Câu " + numOfQuestion + ":");
            viewHolder.question.setText(e.getTitle());
            //mAdapter = new AnswerAdaptor(getContext(), lsQuestion.get(position).getAnswers());
            //viewHolder.listviewOfAnswer.setAdapter(mAdapter);
            for(int i = 0; i< lsQuestion.get(position).getAnswers().size(); i++) {
                View subRow = layoutInflater.inflate(R.layout.custom_survey_multichoice_answer,null);

                CheckedTextView answer = subRow.findViewById(R.id.answer);

                answer.setText(lsQuestion.get(position).getAnswers().get(i));
                //viewHolder.listOfAnswer.addView(subRow);
            }
            Log.e("DataManager","Câu " + numOfQuestion + " loai " + lsQuestion.get(position).getType());
        }
        else{
            if (viewRow == null) {
                viewRow = layoutInflater.inflate(R.layout.custom_survey_text_question,parent,false);
                QuestionAdapter.ViewHolder holder = new QuestionAdapter.ViewHolder();
                holder.number = viewRow.findViewById(R.id.tqNumber);
                holder.question = viewRow.findViewById(R.id.tqQuestion);
                holder.answer = viewRow.findViewById(R.id.edtAnswer);

                viewRow.setTag(holder);
            }
            int numOfQuestion = position + 1;
            ViewHolder viewHolder = (ViewHolder) viewRow.getTag();
            Question e = lsQuestion.get(position);
            viewHolder.number.setText("Câu " + numOfQuestion + ":");
            viewHolder.question.setText(e.getTitle());
            Log.e("DataManager","Câu " + numOfQuestion + " loai " + lsQuestion.get(position).getType());
            Log.e("DataManager","Câu " + numOfQuestion + " co cau tra loi la " + lsQuestion.get(position).getAnswers());
        }

        return viewRow;
    }

    public static class ViewHolder{
        TextView number;
        TextView question;
        ListView listviewOfAnswer;
        EditText answer;
        LinearLayout listOfAnswer;
    }
}
