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
import com.nguyenhongphuc98.dsaw.data.model.TrackingStatus;

import java.util.List;

public class RouteAdaptor extends ArrayAdapter {

    Context context;
    List<TrackingStatus> lsTracking;

    public RouteAdaptor(@NonNull Context context, List<TrackingStatus> ls) {
        super(context, R.layout.custom_route_row, ls);
        this.context = context;
        lsTracking = ls;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewRow = convertView;

        if (viewRow == null) {
            viewRow = layoutInflater.inflate(R.layout.custom_route_row, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.date = viewRow.findViewById(R.id.route_date_tv_item);
            holder.location = viewRow.findViewById(R.id.route_location_tv_item);

            viewRow.setTag(holder);
        }

        ViewHolder viewHolder = (ViewHolder) viewRow.getTag();

        //viewHolder.cover.setImageResource(R.drawable.ninja_avt);

        TrackingStatus e = lsTracking.get(position);
        viewHolder.date.setText(e.getUpdate_time());
        viewHolder.location.setText(e.getLocation());

        return viewRow;
    }

    public static class ViewHolder {
        TextView date;
        TextView location;
    }
}
