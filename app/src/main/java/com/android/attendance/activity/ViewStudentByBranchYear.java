package com.android.attendance.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.attendance.bean.StudentBean;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public class ViewStudentByBranchYear extends Activity {
    String branch;
    DBAdapter dbAdapter = new DBAdapter(this);
    private ArrayAdapter<String> listAdapter;
    private ListView listView;
    ArrayList<StudentBean> studentBeanList;
    String year;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.__listview_main);
        this.listView = (ListView) findViewById(R.id.listview);
        final ArrayList<String> studentList = new ArrayList<>();
        this.branch = getIntent().getExtras().getString("branch");
        this.year = getIntent().getExtras().getString("year");
        this.studentBeanList = this.dbAdapter.getAllStudentByBranchYear(this.branch, this.year);
        Iterator<StudentBean> it = this.studentBeanList.iterator();
        while (it.hasNext()) {
            StudentBean studentBean = it.next();
            String users = studentBean.getStudent_firstname() + "," + studentBean.getStudent_lastname();
            studentList.add(users);
            Log.d("users: ", users);
        }
        this.listAdapter = new ArrayAdapter<>(this, (int) R.layout.view_student_list, (int) R.id.label, studentList);
        this.listView.setAdapter((ListAdapter) this.listAdapter);
        this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.android.attendance.activity.ViewStudentByBranchYear.1
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewStudentByBranchYear.this);
                alertDialogBuilder.setTitle(((Object) ViewStudentByBranchYear.this.getTitle()) + "decision");
                alertDialogBuilder.setMessage("Are you sure?");
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() { // from class: com.android.attendance.activity.ViewStudentByBranchYear.1.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int id) {
                        studentList.remove(position);
                        ViewStudentByBranchYear.this.listAdapter.notifyDataSetChanged();
                        ViewStudentByBranchYear.this.listAdapter.notifyDataSetInvalidated();
                        ViewStudentByBranchYear.this.dbAdapter.deleteStudent(ViewStudentByBranchYear.this.studentBeanList.get(position).getStudent_id());
                        ViewStudentByBranchYear.this.studentBeanList = ViewStudentByBranchYear.this.dbAdapter.getAllStudentByBranchYear(ViewStudentByBranchYear.this.branch, ViewStudentByBranchYear.this.year);
                        Iterator<StudentBean> it2 = ViewStudentByBranchYear.this.studentBeanList.iterator();
                        while (it2.hasNext()) {
                            StudentBean studentBean2 = it2.next();
                            String users2 = " FirstName: " + studentBean2.getStudent_firstname() + "\nLastname:" + studentBean2.getStudent_lastname();
                            studentList.add(users2);
                            Log.d("users: ", users2);
                        }
                    }
                });
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() { // from class: com.android.attendance.activity.ViewStudentByBranchYear.1.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Toast.makeText(ViewStudentByBranchYear.this.getApplicationContext(), "You choose cancel", 1).show();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return false;
            }
        });
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
