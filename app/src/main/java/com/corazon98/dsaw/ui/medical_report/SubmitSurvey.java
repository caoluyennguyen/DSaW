package com.corazon98.dsaw.ui.medical_report;

import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.corazon98.dsaw.R;
import com.corazon98.dsaw.adaptor.OnIntentReceived;
import com.corazon98.dsaw.adaptor.QuestionAdapter;
import com.corazon98.dsaw.data.DataCenter;
import com.corazon98.dsaw.data.DataManager;
import com.corazon98.dsaw.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class SubmitSurvey extends Fragment {
    final int CODE_OPEN_DOCUMENT = 22;

    private SubmitSurveyViewModel mViewModel;
    private QuestionAdapter adapter;
    private List<Question> lsQuestion = new ArrayList<>();
    private List<String> lsAnswer;

    private ImageView btnBack;
    private ListView lvQuestion;
    private Button btnSubmit;
    private OnIntentReceived mIntentListener;

    int questionClicked;
    Uri coverImg;
    List<Uri> lsCoverImg = new ArrayList<>();
    List<String> lsIdCoverImg = new ArrayList<>();

    public static SubmitSurvey newInstance() {
        return new SubmitSurvey();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submit_survey, container, false);

        InitView(view);
        InitEvent();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SubmitSurveyViewModel.class);

        adapter = new QuestionAdapter(getContext(), lsQuestion);
        lvQuestion.setAdapter(adapter);
        mIntentListener = adapter;

        // TODO: Use the ViewModel
        mViewModel.getmListQuestion().observe(this, mListQuestion -> {
            lsQuestion.clear();
            for (Question a : mListQuestion) {
                lsQuestion.add(a);
                /*if (a.getType().equalsIgnoreCase("image")) {
                    lsCoverImg.add(null);
                    lsIdCoverImg.add(null);
                }*/
                Log.e("Submit survey", "Activity start");
            }

            if (lsQuestion.size() == 0)
                lsQuestion.add(new Question("", new ArrayList<String>(), "", "Tạm thời chưa có câu trả lời", ""));

            adapter.notifyDataSetChanged();
        });

        mViewModel.FetchData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (data!=null)
            {
                Uri selectedFile = data.getData();
                coverImg = selectedFile;
                Toast.makeText(getContext(), "Tải ảnh thành công", Toast.LENGTH_LONG).show();

                lsCoverImg.add(selectedFile);

                Long localDateTime=System.currentTimeMillis();
                String id = localDateTime.toString();
                lsIdCoverImg.add(id);

                mIntentListener.onIntent(data, resultCode, selectedFile);

                Log.e("Submit survey", "Uri image: " + selectedFile);
                Log.e("Submit survey", "List uri image: " + lsCoverImg);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InitView(View view)
    {
        lvQuestion = view.findViewById(R.id.list_of_question);
        btnBack = view.findViewById(R.id.go_back_btn);
        btnSubmit = view.findViewById(R.id.summit_button);
    }

    public void InitEvent()
    {
        btnBack.setOnClickListener(v -> NavHostFragment.findNavController(getParentFragment()).navigate(R.id.navigation_report));

        lvQuestion.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(getContext(), "Selected item at position: " + position, Toast.LENGTH_LONG).show();
            questionClicked = position;
            //view = lvQuestion.getChildAt(position);
            if (lsQuestion.get(position).getType().equalsIgnoreCase("image")) {
                try {
                    Intent intentOpenFile = new Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intentOpenFile, "Choose image"), CODE_OPEN_DOCUMENT);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Potentially direct the user to the Market with a Dialog
                    Toast.makeText(getContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSubmit.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Submit survey", Toast.LENGTH_LONG).show();
            lsAnswer = new ArrayList<>();
            //lsAnswer.clear();
            ArrayList<ArrayList<String>> lsMultipleAnswer = new ArrayList<>();
            ArrayList<ArrayList<Integer>> lsMultipleAnswerId = new ArrayList<>();
            View parentView = null;
            int index = 0;

            for (int i = 0; i < lvQuestion.getCount(); i++)
            {
                parentView = lvQuestion.getChildAt(i);
                if (lsQuestion.get(i).getType().equalsIgnoreCase("text")) {
                    lsAnswer.add(((TextView) parentView.findViewById(R.id.edtAnswer)).getText().toString());
                }
                else if (lsQuestion.get(i).getType().equalsIgnoreCase("image")) {
                    lsAnswer.add(lsIdCoverImg.get(index));
                    DataManager.Instance().UploadImageToReport(lsIdCoverImg.get(index), "report/", lsCoverImg.get(index));
                    Log.e("Submit survey", "List id cover image: " + lsIdCoverImg);

                    index++;
                }
                else {
                    ArrayList<String> lsCheckedAnswer = new ArrayList<>();
                    ArrayList<Integer> lsCheckedAnswerId = new ArrayList<>();
                    LinearLayout lsAnswer =  parentView.findViewById(R.id.list_of_answer);
                    for (int j = 0; j < lsQuestion.get(i).getAnswers().size(); j++) {
                        LinearLayout answerLayout = (LinearLayout) lsAnswer.getChildAt(j);
                        AppCompatCheckedTextView answer = (AppCompatCheckedTextView) answerLayout.getChildAt(0);
                        if (answer.isChecked()) {
                            Log.e("Submit survey", "Answer is checked: " + answer.getText().toString());
                            lsCheckedAnswer.add(answer.getText().toString());
                            //lsCheckedAnswer.add(String.valueOf(j));
                            lsCheckedAnswerId.add(j);
                        }
                    }
                    lsMultipleAnswer.add(lsCheckedAnswer);
                    lsMultipleAnswerId.add(lsCheckedAnswerId);
                }
                Log.e("Submit survey", "List question: " + lvQuestion.getAdapter());
            }

            //save answer to firebase
            if (lsQuestion.get(0).getType().equalsIgnoreCase("MT")) {
                DataManager.Instance().AddNewMultipleAnswer(DataCenter.surveyID, DataCenter.currentUser.getIdentity(), lsQuestion, lsMultipleAnswer, lsMultipleAnswerId);
            }
            else {
                // save list image
                /*for (int i = 0; i < lsCoverImg.size(); i++) {
                    DataManager.Instance().UploadFileToFirebase("report/", lsCoverImg.get(i));
                }*/
                // save list answer
                DataManager.Instance().AddNewAnswer(DataCenter.surveyID, DataCenter.currentUser.getIdentity(), lsQuestion, lsAnswer);
            }

            lsCoverImg.clear();
            lsIdCoverImg.clear();
        });
    }
}