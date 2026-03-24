package com.android.attendance.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.example.androidattendancesystem.R;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public class ViewAttandanceActivity extends Activity {
    String branch;
    Spinner spinnerbranch;
    Spinner spinneryear;
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
        this.spinnerbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.android.attendance.activity.ViewAttandanceActivity.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(-1);
                ViewAttandanceActivity viewAttandanceActivity = ViewAttandanceActivity.this;
                viewAttandanceActivity.branch = (String) viewAttandanceActivity.spinnerbranch.getSelectedItem();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        ArrayAdapter<String> adapter_branch = new ArrayAdapter<>(this, 17367048, this.branchString);
        adapter_branch.setDropDownViewResource(17367049);
        this.spinnerbranch.setAdapter((SpinnerAdapter) adapter_branch);
        this.spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.android.attendance.activity.ViewAttandanceActivity.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(-1);
                ViewAttandanceActivity viewAttandanceActivity = ViewAttandanceActivity.this;
                viewAttandanceActivity.year = (String) viewAttandanceActivity.spinneryear.getSelectedItem();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        ArrayAdapter<String> adapter_year = new ArrayAdapter<>(this, 17367048, this.yearString);
        adapter_year.setDropDownViewResource(17367049);
        this.spinneryear.setAdapter((SpinnerAdapter) adapter_year);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
