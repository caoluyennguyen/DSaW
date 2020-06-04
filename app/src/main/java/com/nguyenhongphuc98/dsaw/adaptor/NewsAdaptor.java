package com.nguyenhongphuc98.dsaw.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.News;

import java.util.List;

public class NewsAdaptor extends ArrayAdapter{
    Context context;
    List<News> lsNews;

    public NewsAdaptor(@NonNull Context context, List<News> ls){
        super(context, R.layout.custom_news_row, ls);
        this.context = context;
        lsNews = ls;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewRow = convertView;

        if (viewRow == null){
            viewRow = layoutInflater.inflate(R.layout.custom_news_row,parent,false);
            ViewHolder holder = new ViewHolder();
            holder.cover = viewRow.findViewById(R.id.news_item_cover);
            holder.cover.setClipToOutline(true);
            holder.title = viewRow.findViewById(R.id.news_item_title);
            holder.content = viewRow.findViewById(R.id.news_item_content);

            viewRow.setTag(holder);
        }

        ViewHolder viewHolder = (ViewHolder) viewRow.getTag();

        News e = lsNews.get(position);
        viewHolder.title.setText(e.getTitle());
        viewHolder.content.setText(e.getContent());
        DataManager.Instance().fetchPhoto(e.getCover(), viewHolder.cover,"posts");

        return viewRow;
    }

    public static class ViewHolder{
        ImageView cover;
        TextView title;
        TextView content;
    }
}