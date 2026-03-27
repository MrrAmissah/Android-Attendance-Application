package com.example.attendanceapp;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AttendanceViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> attendanceStatus;

    public AttendanceViewModel(@NonNull Application application) {
        super(application);
        attendanceStatus = new MutableLiveData<>();
    }

    public LiveData<Boolean> getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(boolean status) {
        attendanceStatus.setValue(status);
    }
}