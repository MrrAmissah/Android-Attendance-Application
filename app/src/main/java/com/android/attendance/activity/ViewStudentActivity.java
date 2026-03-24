package com.android.attendance.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.example.androidattendancesystem.R;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public class ViewStudentActivity extends Activity {
    String branch;
    Spinner spinnerbranch;
    Spinner spinneryear;
    Button submit;
    String userrole;
    String year;
    private String[] branchString = {"cse"};
    private String[] yearString = {"SE", "TE", "BE"};

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewstudent);
        this.spinnerbranch = (Spinner) findViewById(R.id.spinnerbranchView);
        this.spinneryear = (Spinner) findViewById(R.id.spinneryearView);
        this.spinnerbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.android.attendance.activity.ViewStudentActivity.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(-1);
                ViewStudentActivity viewStudentActivity = ViewStudentActivity.this;
                viewStudentActivity.branch = (String) viewStudentActivity.spinnerbranch.getSelectedItem();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        ArrayAdapter<String> adapter_branch = new ArrayAdapter<>(this, 17367048, this.branchString);
        adapter_branch.setDropDownViewResource(17367049);
        this.spinnerbranch.setAdapter((SpinnerAdapter) adapter_branch);
        this.spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.android.attendance.activity.ViewStudentActivity.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(-1);
                ViewStudentActivity viewStudentActivity = ViewStudentActivity.this;
                viewStudentActivity.year = (String) viewStudentActivity.spinneryear.getSelectedItem();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        ArrayAdapter<String> adapter_year = new ArrayAdapter<>(this, 17367048, this.yearString);
        adapter_year.setDropDownViewResource(17367049);
        this.spinneryear.setAdapter((SpinnerAdapter) adapter_year);
        this.submit = (Button) findViewById(R.id.submitButton);
        this.submit.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.ViewStudentActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View arg0) {
                Intent intent = new Intent(ViewStudentActivity.this, ViewStudentByBranchYear.class);
                intent.putExtra("branch", ViewStudentActivity.this.branch);
                intent.putExtra("year", ViewStudentActivity.this.year);
                ViewStudentActivity.this.startActivity(intent);
            }
        });
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
