package com.android.attendance.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.android.attendance.bean.AttendanceBean;
import com.android.attendance.bean.AttendanceSessionBean;
import com.android.attendance.bean.FacultyBean;
import com.android.attendance.bean.StudentBean;
import com.android.attendance.context.ApplicationContext;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;
import java.util.ArrayList;
import java.util.Calendar;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public class AddAttandanceSessionActivity<AddAttandanceActivity> extends Activity {
    AttendanceSessionBean attendanceSessionBean;
    private Calendar cal;
    private ImageButton date;
    private EditText dateEditText;
    private int day;
    private int dyear;
    private int month;
    Spinner spinnerSubject;
    Spinner spinnerbranch;
    Spinner spinneryear;
    Button submit;
    Button viewAttendance;
    Button viewTotalAttendance;
    String branch = "cse";
    String year = "SE";
    String subject = "SC";
    private String[] branchString = {"cse"};
    private String[] yearString = {"SE", "TE", "BE"};
    private String[] subjectSEString = {"SC", "MC"};
    private String[] subjectTEString = {"GT", "CN"};
    private String[] subjectBEString = {"DS", "NS"};
    private String[] subjectFinal = {"M3", "DS", "M4", "CN", "M5", "NS"};
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() { // from class: com.android.attendance.activity.AddAttandanceSessionActivity.8
        @Override // android.app.DatePickerDialog.OnDateSetListener
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            EditText editText = AddAttandanceSessionActivity.this.dateEditText;
            editText.setText(selectedDay + " / " + (selectedMonth + 1) + " / " + selectedYear);
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.add_attandance);
        this.spinnerbranch = (Spinner) findViewById(R.id.spinner1);
        this.spinneryear = (Spinner) findViewById(R.id.spinneryear);
        this.spinnerSubject = (Spinner) findViewById(R.id.spinnerSE);
        ArrayAdapter<String> adapter_branch = new ArrayAdapter<>(this, 17367048, this.branchString);
        adapter_branch.setDropDownViewResource(17367049);
        this.spinnerbranch.setAdapter((SpinnerAdapter) adapter_branch);
        this.spinnerbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.android.attendance.activity.AddAttandanceSessionActivity.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(-1);
                AddAttandanceSessionActivity addAttandanceSessionActivity = AddAttandanceSessionActivity.this;
                addAttandanceSessionActivity.branch = (String) addAttandanceSessionActivity.spinnerbranch.getSelectedItem();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        ArrayAdapter<String> adapter_year = new ArrayAdapter<>(this, 17367048, this.yearString);
        adapter_year.setDropDownViewResource(17367049);
        this.spinneryear.setAdapter((SpinnerAdapter) adapter_year);
        this.spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.android.attendance.activity.AddAttandanceSessionActivity.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(-1);
                AddAttandanceSessionActivity addAttandanceSessionActivity = AddAttandanceSessionActivity.this;
                addAttandanceSessionActivity.year = (String) addAttandanceSessionActivity.spinneryear.getSelectedItem();
                Context applicationContext = AddAttandanceSessionActivity.this.getApplicationContext();
                Toast.makeText(applicationContext, "year:" + AddAttandanceSessionActivity.this.year, 0).show();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        ArrayAdapter<String> adapter_subject = new ArrayAdapter<>(this, 17367048, this.subjectFinal);
        adapter_subject.setDropDownViewResource(17367049);
        this.spinnerSubject.setAdapter((SpinnerAdapter) adapter_subject);
        this.spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.android.attendance.activity.AddAttandanceSessionActivity.3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(-1);
                AddAttandanceSessionActivity addAttandanceSessionActivity = AddAttandanceSessionActivity.this;
                addAttandanceSessionActivity.subject = (String) addAttandanceSessionActivity.spinnerSubject.getSelectedItem();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        this.date = (ImageButton) findViewById(R.id.DateImageButton);
        this.cal = Calendar.getInstance();
        this.day = this.cal.get(5);
        this.month = this.cal.get(2);
        this.dyear = this.cal.get(1);
        this.dateEditText = (EditText) findViewById(R.id.DateEditText);
        this.date.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.AddAttandanceSessionActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View arg0) {
                AddAttandanceSessionActivity.this.showDialog(0);
            }
        });
        this.submit = (Button) findViewById(R.id.buttonsubmit);
        this.submit.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.AddAttandanceSessionActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View arg0) {
                AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
                FacultyBean bean = ((ApplicationContext) AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();
                attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
                attendanceSessionBean.setAttendance_session_department(AddAttandanceSessionActivity.this.branch);
                attendanceSessionBean.setAttendance_session_class(AddAttandanceSessionActivity.this.year);
                attendanceSessionBean.setAttendance_session_date(AddAttandanceSessionActivity.this.dateEditText.getText().toString());
                attendanceSessionBean.setAttendance_session_subject(AddAttandanceSessionActivity.this.subject);
                DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);
                int sessionId = dbAdapter.addAttendanceSession(attendanceSessionBean);
                ArrayList<StudentBean> studentBeanList = dbAdapter.getAllStudentByBranchYear(AddAttandanceSessionActivity.this.branch, AddAttandanceSessionActivity.this.year);
                ((ApplicationContext) AddAttandanceSessionActivity.this.getApplicationContext()).setStudentBeanList(studentBeanList);
                Intent intent = new Intent(AddAttandanceSessionActivity.this, AddAttendanceActivity.class);
                intent.putExtra("sessionId", sessionId);
                AddAttandanceSessionActivity.this.startActivity(intent);
            }
        });
        this.viewAttendance = (Button) findViewById(R.id.viewAttendancebutton);
        this.viewAttendance.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.AddAttandanceSessionActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View arg0) {
                AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
                FacultyBean bean = ((ApplicationContext) AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();
                attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
                attendanceSessionBean.setAttendance_session_department(AddAttandanceSessionActivity.this.branch);
                attendanceSessionBean.setAttendance_session_class(AddAttandanceSessionActivity.this.year);
                attendanceSessionBean.setAttendance_session_date(AddAttandanceSessionActivity.this.dateEditText.getText().toString());
                attendanceSessionBean.setAttendance_session_subject(AddAttandanceSessionActivity.this.subject);
                DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);
                ArrayList<AttendanceBean> attendanceBeanList = dbAdapter.getAttendanceBySessionID(attendanceSessionBean);
                ((ApplicationContext) AddAttandanceSessionActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);
                Intent intent = new Intent(AddAttandanceSessionActivity.this, ViewAttendanceByFacultyActivity.class);
                AddAttandanceSessionActivity.this.startActivity(intent);
            }
        });
        this.viewTotalAttendance = (Button) findViewById(R.id.viewTotalAttendanceButton);
        this.viewTotalAttendance.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.AddAttandanceSessionActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View arg0) {
                AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
                FacultyBean bean = ((ApplicationContext) AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();
                attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
                attendanceSessionBean.setAttendance_session_department(AddAttandanceSessionActivity.this.branch);
                attendanceSessionBean.setAttendance_session_class(AddAttandanceSessionActivity.this.year);
                attendanceSessionBean.setAttendance_session_subject(AddAttandanceSessionActivity.this.subject);
                DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);
                ArrayList<AttendanceBean> attendanceBeanList = dbAdapter.getTotalAttendanceBySessionID(attendanceSessionBean);
                ((ApplicationContext) AddAttandanceSessionActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);
                Intent intent = new Intent(AddAttandanceSessionActivity.this, ViewAttendanceByFacultyActivity.class);
                AddAttandanceSessionActivity.this.startActivity(intent);
            }
        });
    }

    @Override // android.app.Activity
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, this.datePickerListener, this.dyear, this.month, this.day);
    }
}
