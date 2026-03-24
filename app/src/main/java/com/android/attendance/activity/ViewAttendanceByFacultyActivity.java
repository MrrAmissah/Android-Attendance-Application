package com.android.attendance.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.attendance.bean.AttendanceBean;
import com.android.attendance.bean.StudentBean;
import com.android.attendance.context.ApplicationContext;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public class ViewAttendanceByFacultyActivity extends Activity {
    ArrayList<AttendanceBean> attendanceBeanList;
    DBAdapter dbAdapter = new DBAdapter(this);
    private ArrayAdapter<String> listAdapter;
    private ListView listView;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        String users;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.__listview_main);
        this.listView = (ListView) findViewById(R.id.listview);
        ArrayList<String> attendanceList = new ArrayList<>();
        attendanceList.add("Id | StudentName |  Status");
        this.attendanceBeanList = ((ApplicationContext) getApplicationContext()).getAttendanceBeanList();
        Iterator<AttendanceBean> it = this.attendanceBeanList.iterator();
        while (it.hasNext()) {
            AttendanceBean attendanceBean = it.next();
            if (attendanceBean.getAttendance_session_id() != 0) {
                DBAdapter dbAdapter = new DBAdapter(this);
                StudentBean studentBean = dbAdapter.getStudentById(attendanceBean.getAttendance_student_id());
                users = attendanceBean.getAttendance_student_id() + ".     " + studentBean.getStudent_firstname() + "," + studentBean.getStudent_lastname() + "                  " + attendanceBean.getAttendance_status();
            } else {
                users = attendanceBean.getAttendance_status();
            }
            attendanceList.add(users);
            Log.d("users: ", users);
        }
        this.listAdapter = new ArrayAdapter<>(this, (int) R.layout.view_attendance_list, (int) R.id.labelAttendance, attendanceList);
        this.listView.setAdapter((ListAdapter) this.listAdapter);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
