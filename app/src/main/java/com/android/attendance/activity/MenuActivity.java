package com.android.attendance.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import com.android.attendance.bean.AttendanceBean;
import com.android.attendance.context.ApplicationContext;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;
import java.util.ArrayList;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public class MenuActivity extends Activity {
    Button addFaculty;
    Button addStudent;
    Button attendancePerStudent;
    Button logout;
    Button viewFaculty;
    Button viewStudent;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        this.addStudent = (Button) findViewById(R.id.buttonaddstudent);
        this.addFaculty = (Button) findViewById(R.id.buttonaddfaculty);
        this.viewStudent = (Button) findViewById(R.id.buttonViewstudent);
        this.viewFaculty = (Button) findViewById(R.id.buttonviewfaculty);
        this.logout = (Button) findViewById(R.id.buttonlogout);
        this.addStudent.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.MenuActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AddStudentActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });
        this.addFaculty.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.MenuActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AddFacultyActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });
        this.viewFaculty.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.MenuActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ViewFacultyActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });
        this.viewStudent.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.MenuActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ViewStudentActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });
        this.logout.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.MenuActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.addFlags(67108864);
                MenuActivity.this.startActivity(intent);
            }
        });
        this.attendancePerStudent = (Button) findViewById(R.id.attendancePerStudentButton);
        this.attendancePerStudent.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.MenuActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View arg0) {
                DBAdapter dbAdapter = new DBAdapter(MenuActivity.this);
                ArrayList<AttendanceBean> attendanceBeanList = dbAdapter.getAllAttendanceByStudent();
                ((ApplicationContext) MenuActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);
                Intent intent = new Intent(MenuActivity.this, ViewAttendancePerStudentActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
