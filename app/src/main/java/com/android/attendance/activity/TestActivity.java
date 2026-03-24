package com.android.attendance.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.android.attendance.bean.AttendanceSessionBean;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public class TestActivity extends Activity {
    Button submit;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);
        this.submit = (Button) findViewById(R.id.button1);
        this.submit.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.TestActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View arg0) {
                DBAdapter dbAdapter = new DBAdapter(TestActivity.this);
                AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
                attendanceSessionBean.setAttendance_session_faculty_id(1);
                attendanceSessionBean.setAttendance_session_department("CSE");
                attendanceSessionBean.setAttendance_session_class("BE");
                attendanceSessionBean.setAttendance_session_date("06/04/2016");
                attendanceSessionBean.setAttendance_session_subject("DataBase");
                dbAdapter.addAttendanceSession(attendanceSessionBean);
                Log.d("add", "inserted");
                ArrayList<AttendanceSessionBean> attendanceSessionBeanList = dbAdapter.getAllAttendanceSession();
                Iterator<AttendanceSessionBean> it = attendanceSessionBeanList.iterator();
                while (it.hasNext()) {
                    AttendanceSessionBean sessionBean = it.next();
                    Log.d("for", "in for loop");
                    int aid = sessionBean.getAttendance_session_id();
                    int fid = sessionBean.getAttendance_session_faculty_id();
                    String sclass = sessionBean.getAttendance_session_class();
                    String dept = sessionBean.getAttendance_session_department();
                    String date = sessionBean.getAttendance_session_date();
                    String sub = sessionBean.getAttendance_session_subject();
                    Log.d("id", aid + "");
                    Log.d("fid", fid + "");
                    Log.d("sclass", sclass);
                    Log.d("dept", dept);
                    Log.d("date", date);
                    Log.d("sub", sub);
                }
            }
        });
    }
}
