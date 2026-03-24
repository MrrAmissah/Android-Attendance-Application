package com.android.attendance.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.android.attendance.bean.StudentBean;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public class AddStudentActivity extends Activity {
    String branch;
    Button registerButton;
    Spinner spinnerbranch;
    Spinner spinneryear;
    EditText textFirstName;
    EditText textLastName;
    EditText textaddress;
    EditText textcontact;
    String userrole;
    String year;
    private String[] branchString = {"cse"};
    private String[] yearString = {"SE", "TE", "BE"};

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstudent);
        this.spinnerbranch = (Spinner) findViewById(R.id.spinnerdept);
        this.spinneryear = (Spinner) findViewById(R.id.spinneryear);
        this.textFirstName = (EditText) findViewById(R.id.editTextFirstName);
        this.textLastName = (EditText) findViewById(R.id.editTextLastName);
        this.textcontact = (EditText) findViewById(R.id.editTextPhone);
        this.textaddress = (EditText) findViewById(R.id.editTextaddr);
        this.registerButton = (Button) findViewById(R.id.RegisterButton);
        this.spinnerbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.android.attendance.activity.AddStudentActivity.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(-1);
                AddStudentActivity addStudentActivity = AddStudentActivity.this;
                addStudentActivity.branch = (String) addStudentActivity.spinnerbranch.getSelectedItem();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        ArrayAdapter<String> adapter_branch = new ArrayAdapter<>(this, 17367048, this.branchString);
        adapter_branch.setDropDownViewResource(17367049);
        this.spinnerbranch.setAdapter((SpinnerAdapter) adapter_branch);
        this.spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.android.attendance.activity.AddStudentActivity.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(-1);
                AddStudentActivity addStudentActivity = AddStudentActivity.this;
                addStudentActivity.year = (String) addStudentActivity.spinneryear.getSelectedItem();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        ArrayAdapter<String> adapter_year = new ArrayAdapter<>(this, 17367048, this.yearString);
        adapter_year.setDropDownViewResource(17367049);
        this.spinneryear.setAdapter((SpinnerAdapter) adapter_year);
        this.registerButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.AddStudentActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                String first_name = AddStudentActivity.this.textFirstName.getText().toString();
                String last_name = AddStudentActivity.this.textLastName.getText().toString();
                String phone_no = AddStudentActivity.this.textcontact.getText().toString();
                String address = AddStudentActivity.this.textaddress.getText().toString();
                if (TextUtils.isEmpty(first_name)) {
                    AddStudentActivity.this.textFirstName.setError("please enter firstname");
                } else if (TextUtils.isEmpty(last_name)) {
                    AddStudentActivity.this.textLastName.setError("please enter lastname");
                } else if (TextUtils.isEmpty(phone_no)) {
                    AddStudentActivity.this.textcontact.setError("please enter phoneno");
                } else if (TextUtils.isEmpty(address)) {
                    AddStudentActivity.this.textaddress.setError("enter address");
                } else {
                    StudentBean studentBean = new StudentBean();
                    studentBean.setStudent_firstname(first_name);
                    studentBean.setStudent_lastname(last_name);
                    studentBean.setStudent_mobilenumber(phone_no);
                    studentBean.setStudent_address(address);
                    studentBean.setStudent_department(AddStudentActivity.this.branch);
                    studentBean.setStudent_class(AddStudentActivity.this.year);
                    DBAdapter dbAdapter = new DBAdapter(AddStudentActivity.this);
                    dbAdapter.addStudent(studentBean);
                    Intent intent = new Intent(AddStudentActivity.this, MenuActivity.class);
                    AddStudentActivity.this.startActivity(intent);
                    Toast.makeText(AddStudentActivity.this.getApplicationContext(), "student added successfully", 0).show();
                }
            }
        });
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
