package com.android.attendance.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.attendance.bean.FacultyBean;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public class AddFacultyActivity extends Activity {
    Button registerButton;
    EditText textFirstName;
    EditText textLastName;
    EditText textaddress;
    EditText textcontact;
    EditText textemail;
    EditText textpassword;
    EditText textusername;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfaculty);
        this.textFirstName = (EditText) findViewById(R.id.editTextFirstName);
        this.textLastName = (EditText) findViewById(R.id.editTextLastName);
        this.textcontact = (EditText) findViewById(R.id.editTextPhone);
        this.textaddress = (EditText) findViewById(R.id.editTextaddr);
        this.textusername = (EditText) findViewById(R.id.editTextUserName);
        this.textpassword = (EditText) findViewById(R.id.editTextPassword);
        this.registerButton = (Button) findViewById(R.id.RegisterButton);
        this.registerButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.AddFacultyActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                String first_name = AddFacultyActivity.this.textFirstName.getText().toString();
                String last_name = AddFacultyActivity.this.textLastName.getText().toString();
                String phone_no = AddFacultyActivity.this.textcontact.getText().toString();
                String address = AddFacultyActivity.this.textaddress.getText().toString();
                String userName = AddFacultyActivity.this.textusername.getText().toString();
                String passWord = AddFacultyActivity.this.textpassword.getText().toString();
                if (TextUtils.isEmpty(first_name)) {
                    AddFacultyActivity.this.textFirstName.setError("please enter firstname");
                } else if (TextUtils.isEmpty(last_name)) {
                    AddFacultyActivity.this.textLastName.setError("please enter lastname");
                } else if (TextUtils.isEmpty(phone_no)) {
                    AddFacultyActivity.this.textcontact.setError("please enter phoneno");
                } else if (TextUtils.isEmpty(address)) {
                    AddFacultyActivity.this.textaddress.setError("enter address");
                } else if (TextUtils.isEmpty(userName)) {
                    AddFacultyActivity.this.textcontact.setError("please enter username");
                } else if (TextUtils.isEmpty(passWord)) {
                    AddFacultyActivity.this.textaddress.setError("enter password");
                } else {
                    FacultyBean facultyBean = new FacultyBean();
                    facultyBean.setFaculty_firstname(first_name);
                    facultyBean.setFaculty_lastname(last_name);
                    facultyBean.setFaculty_mobilenumber(phone_no);
                    facultyBean.setFaculty_address(address);
                    facultyBean.setFaculty_username(userName);
                    facultyBean.setFaculty_password(passWord);
                    DBAdapter dbAdapter = new DBAdapter(AddFacultyActivity.this);
                    dbAdapter.addFaculty(facultyBean);
                    Intent intent = new Intent(AddFacultyActivity.this, MenuActivity.class);
                    AddFacultyActivity.this.startActivity(intent);
                    Toast.makeText(AddFacultyActivity.this.getApplicationContext(), "Faculty added successfully", 0).show();
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
