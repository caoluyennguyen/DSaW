package com.nguyenhongphuc98.dsaw.ui.route;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.data.model.TrackingStatus;

import java.util.ArrayList;
import java.util.List;

public class RouteViewModel extends ViewModel {

    private MutableLiveData<List<TrackingStatus>> mTracking;

    private List<TrackingStatus> lsTracking;

    public RouteViewModel() {
        mTracking = new MutableLiveData<>();
    }

    public void fetchData(String uid) {

        lsTracking = new ArrayList<>();
        lsTracking.add(new TrackingStatus("Khách sạn Mường Thanh, 34/22 Lý Liên Kiệt. Hà Nội","11/02"));
        lsTracking.add(new TrackingStatus("Trường ĐH CNTT 06, Linh Trung, Thủ Đức. Hồ Chí Minh","05/01"));
        lsTracking.add(new TrackingStatus("Nhà riêng, 34/22 Lý Liên Kiệt. Hà Nội","02/01"));
        mTracking.setValue(lsTracking);

    }

    public MutableLiveData<List<TrackingStatus>> getlistTrackig() {return  mTracking; }
}
