package com.corazon98.dsaw.ui.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.corazon98.dsaw.R;
import com.corazon98.dsaw.adaptor.NewsAdaptor;
import com.corazon98.dsaw.data.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private NewsViewModel newsViewModel;

    private NewsAdaptor adaptor;
    private List<News> lsNews = new ArrayList<>();

    private ListView lvNews;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_news, container, false);

        lvNews=root.findViewById(R.id.news_info_lv);

        newsViewModel.getListNews().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                lsNews.clear();
                for (News a : news) {
                    lsNews.add(a);
                    Log.e("News fragment", "Activity start");
                }
                adaptor = new NewsAdaptor(getContext(),news);
                lvNews.setAdapter(adaptor);
            }
        });

        InitEvent();

        return root;
    }

    public void InitEvent()
    {
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Selected item at position: " + position, Toast.LENGTH_LONG).show();
                view = lvNews.getChildAt(position);
                /*if (lsNews.get(position).getType().equalsIgnoreCase("image")) {
                    try {
                        // Open browser here

                    } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getContext(), "Please install a web browser.", Toast.LENGTH_SHORT).show();
                    }
                }*/
                try {
                    // Open browser here
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(lsNews.get(position).getLink()));
                    startActivity(browserIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "Have a good day! ^^", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}