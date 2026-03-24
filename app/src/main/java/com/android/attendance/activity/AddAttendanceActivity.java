package com.android.attendance.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import com.android.attendance.bean.AttendanceBean;
import com.android.attendance.bean.StudentBean;
import com.android.attendance.context.ApplicationContext;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public class AddAttendanceActivity extends Activity {
    Button attendanceSubmit;
    private ArrayAdapter<String> listAdapter;
    private ListView listView;
    ArrayList<StudentBean> studentBeanList;
    int sessionId = 0;
    String status = "P";
    DBAdapter dbAdapter = new DBAdapter(this);

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.__listview_main);
        this.sessionId = getIntent().getExtras().getInt("sessionId");
        this.listView = (ListView) findViewById(R.id.listview);
        ArrayList<String> studentList = new ArrayList<>();
        this.studentBeanList = ((ApplicationContext) getApplicationContext()).getStudentBeanList();
        Iterator<StudentBean> it = this.studentBeanList.iterator();
        while (it.hasNext()) {
            StudentBean studentBean = it.next();
            String users = studentBean.getStudent_firstname() + "," + studentBean.getStudent_lastname();
            studentList.add(users);
            Log.d("users: ", users);
        }
        this.listAdapter = new ArrayAdapter<>(this, (int) R.layout.add_student_attendance, (int) R.id.labelA, studentList);
        this.listView.setAdapter((ListAdapter) this.listAdapter);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.android.attendance.activity.AddAttendanceActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                arg0.getChildAt(arg2).setBackgroundColor(0);
                arg1.setBackgroundColor(334455);
                final StudentBean studentBean2 = AddAttendanceActivity.this.studentBeanList.get(arg2);
                final Dialog dialog = new Dialog(AddAttendanceActivity.this);
                dialog.requestWindowFeature(1);
                dialog.setContentView(R.layout.test_layout);
                RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
                dialog.findViewById(R.id.PresentradioButton);
                dialog.findViewById(R.id.AbsentradioButton);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.android.attendance.activity.AddAttendanceActivity.1.1
                    @Override // android.widget.RadioGroup.OnCheckedChangeListener
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == 2131099653) {
                            AddAttendanceActivity.this.status = "P";
                        } else if (checkedId == 2131099648) {
                            AddAttendanceActivity.this.status = "A";
                        }
                    }
                });
                AddAttendanceActivity.this.attendanceSubmit = (Button) dialog.findViewById(R.id.attendanceSubmitButton);
                AddAttendanceActivity.this.attendanceSubmit.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.AddAttendanceActivity.1.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View arg02) {
                        AttendanceBean attendanceBean = new AttendanceBean();
                        attendanceBean.setAttendance_session_id(AddAttendanceActivity.this.sessionId);
                        attendanceBean.setAttendance_student_id(studentBean2.getStudent_id());
                        attendanceBean.setAttendance_status(AddAttendanceActivity.this.status);
                        DBAdapter dbAdapter = new DBAdapter(AddAttendanceActivity.this);
                        dbAdapter.addNewAttendance(attendanceBean);
                        dialog.dismiss();
                    }
                });
                dialog.setCancelable(true);
                dialog.show();
            }
        });
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
