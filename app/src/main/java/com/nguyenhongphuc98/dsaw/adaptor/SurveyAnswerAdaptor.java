package com.nguyenhongphuc98.dsaw.adaptor;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.data.model.News;

import java.util.List;

public class SurveyAnswerAdaptor extends ArrayAdapter {
    Context context;
    List<News> lsNews;

    public SurveyAnswerAdaptor(@NonNull Context context, List<News> ls){
        super(context, R.layout.custom_news_row, ls);
        this.context = context;
        lsNews = ls;
    }
}
