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
import com.android.attendance.bean.FacultyBean;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public class ViewFacultyActivity extends Activity {
    DBAdapter dbAdapter = new DBAdapter(this);
    ArrayList<FacultyBean> facultyBeanList;
    private ArrayAdapter<String> listAdapter;
    private ListView listView;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.__listview_main);
        this.listView = (ListView) findViewById(R.id.listview);
        final ArrayList<String> facultyList = new ArrayList<>();
        this.facultyBeanList = this.dbAdapter.getAllFaculty();
        Iterator<FacultyBean> it = this.facultyBeanList.iterator();
        while (it.hasNext()) {
            FacultyBean facultyBean = it.next();
            String users = " FirstName: " + facultyBean.getFaculty_firstname() + "\nLastname:" + facultyBean.getFaculty_lastname();
            facultyList.add(users);
            Log.d("users: ", users);
        }
        this.listAdapter = new ArrayAdapter<>(this, (int) R.layout.view_faculty_list, (int) R.id.labelF, facultyList);
        this.listView.setAdapter((ListAdapter) this.listAdapter);
        this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.android.attendance.activity.ViewFacultyActivity.1
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewFacultyActivity.this);
                alertDialogBuilder.setTitle(((Object) ViewFacultyActivity.this.getTitle()) + "decision");
                alertDialogBuilder.setMessage("Are you sure?");
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() { // from class: com.android.attendance.activity.ViewFacultyActivity.1.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int id) {
                        facultyList.remove(position);
                        ViewFacultyActivity.this.listAdapter.notifyDataSetChanged();
                        ViewFacultyActivity.this.listAdapter.notifyDataSetInvalidated();
                        ViewFacultyActivity.this.dbAdapter.deleteFaculty(ViewFacultyActivity.this.facultyBeanList.get(position).getFaculty_id());
                        ViewFacultyActivity.this.facultyBeanList = ViewFacultyActivity.this.dbAdapter.getAllFaculty();
                        Iterator<FacultyBean> it2 = ViewFacultyActivity.this.facultyBeanList.iterator();
                        while (it2.hasNext()) {
                            FacultyBean facultyBean2 = it2.next();
                            String users2 = " FirstName: " + facultyBean2.getFaculty_firstname() + "\nLastname:" + facultyBean2.getFaculty_lastname();
                            facultyList.add(users2);
                            Log.d("users: ", users2);
                        }
                    }
                });
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() { // from class: com.android.attendance.activity.ViewFacultyActivity.1.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Toast.makeText(ViewFacultyActivity.this.getApplicationContext(), "You choose cancel", 1).show();
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
