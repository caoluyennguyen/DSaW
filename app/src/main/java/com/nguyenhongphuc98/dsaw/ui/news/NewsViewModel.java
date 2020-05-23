package com.nguyenhongphuc98.dsaw.ui.news;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.adaptor.NewsAdaptor;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<List<News>> lsNews;

    public NewsViewModel() {
        lsNews = new MutableLiveData<>();
        fetchNews();
    }

    public LiveData<List<News>> getListNews() {
        return lsNews;
    }

    public void fetchNews() {

//        lsNews = new ArrayList<>();
//        lsNews.add(new News("Phuc",
//                "Khuyến cáo thực hành 7 thói quen nên làm để tránh dịch bệnh giúp cuốc sống sớm ổn định trở lại. Người người, nhà nhà hạnh phúc ấm no...",
//                "cover",
//                "22:03 - 22/11/2019",
//                "1",
//                "link to post",
//                "THỰC HÀNH 7 THÓI QUEN ĐỂ PHÒNG CHỐNG BỆNH COVID-19 TRONG MÙA DỊCH",
//                "link"));
//
//        lsNews.add(new News("Phuc",
//                "Khuyến cáo thực hành 7 thói quen nên làm để tránh dịch bệnh giúp cuốc sống sớm ổn định trở lại. Người người, nhà nhà hạnh phúc ấm no...",
//                "cover",
//                "22:03 - 22/11/2019",
//                "1",
//                "link to post",
//                "THỰC HÀNH 7 THÓI QUEN ĐỂ PHÒNG CHỐNG BỆNH COVID-19 TRONG MÙA DỊCH",
//                "link"));
//
//        lsNews.add(new News("Phuc",
//                "Khuyến cáo thực hành 7 thói quen nên làm để tránh dịch bệnh giúp cuốc sống sớm ổn định trở lại. Người người, nhà nhà hạnh phúc ấm no...",
//                "cover",
//                "22:03 - 22/11/2019",
//                "1",
//                "link to post",
//                "THỰC HÀNH 7 THÓI QUEN ĐỂ PHÒNG CHỐNG BỆNH COVID-19 TRONG MÙA DỊCH",
//                "link"));
//        adaptor = new NewsAdaptor(this.context, lsNews);

        DataManager.Instance().fetchAllPosts(lsNews);
    }
}