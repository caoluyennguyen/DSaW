package com.nguyenhongphuc98.dsaw.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.adaptor.NewsAdaptor;
import com.nguyenhongphuc98.dsaw.data.model.News;

import java.util.List;

public class NewsFragment extends Fragment {

    private NewsViewModel newsViewModel;

    private ListView lvNews;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsViewModel =
                ViewModelProviders.of(this).get(NewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        lvNews=root.findViewById(R.id.news_info_lv);

        newsViewModel.getAdaptor().observe(this, new Observer<NewsAdaptor>() {
            @Override
            public void onChanged(NewsAdaptor newsAdaptor) {
                lvNews.setAdapter(newsAdaptor);
            }
        });

        return root;
    }
}