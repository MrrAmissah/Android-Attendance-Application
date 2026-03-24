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
import com.android.attendance.bean.FacultyBean;
import com.android.attendance.context.ApplicationContext;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public class LoginActivity extends Activity {
    Button login;
    EditText password;
    Spinner spinnerloginas;
    private String[] userRoleString = {"admin", "faculty"};
    EditText username;
    String userrole;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.login = (Button) findViewById(R.id.buttonlogin);
        this.username = (EditText) findViewById(R.id.editTextusername);
        this.password = (EditText) findViewById(R.id.editTextpassword);
        this.spinnerloginas = (Spinner) findViewById(R.id.spinnerloginas);
        this.spinnerloginas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.android.attendance.activity.LoginActivity.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(-1);
                LoginActivity loginActivity = LoginActivity.this;
                loginActivity.userrole = (String) loginActivity.spinnerloginas.getSelectedItem();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        ArrayAdapter<String> adapter_role = new ArrayAdapter<>(this, 17367048, this.userRoleString);
        adapter_role.setDropDownViewResource(17367049);
        this.spinnerloginas.setAdapter((SpinnerAdapter) adapter_role);
        this.login.setOnClickListener(new View.OnClickListener() { // from class: com.android.attendance.activity.LoginActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (LoginActivity.this.userrole.equals("admin")) {
                    String user_name = LoginActivity.this.username.getText().toString();
                    String pass_word = LoginActivity.this.password.getText().toString();
                    if (TextUtils.isEmpty(user_name)) {
                        LoginActivity.this.username.setError("Invalid User Name");
                        return;
                    } else if (TextUtils.isEmpty(pass_word)) {
                        LoginActivity.this.password.setError("enter password");
                        return;
                    } else if (user_name.equals("admin") & pass_word.equals("admin123")) {
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        LoginActivity.this.startActivity(intent);
                        Toast.makeText(LoginActivity.this.getApplicationContext(), "Login successful", 0).show();
                        return;
                    } else {
                        Toast.makeText(LoginActivity.this.getApplicationContext(), "Login failed", 0).show();
                        return;
                    }
                }
                String user_name2 = LoginActivity.this.username.getText().toString();
                String pass_word2 = LoginActivity.this.password.getText().toString();
                if (TextUtils.isEmpty(user_name2)) {
                    LoginActivity.this.username.setError("Invalid User Name");
                } else if (TextUtils.isEmpty(pass_word2)) {
                    LoginActivity.this.password.setError("enter password");
                }
                DBAdapter dbAdapter = new DBAdapter(LoginActivity.this);
                FacultyBean facultyBean = dbAdapter.validateFaculty(user_name2, pass_word2);
                if (facultyBean != null) {
                    Intent intent2 = new Intent(LoginActivity.this, AddAttandanceSessionActivity.class);
                    LoginActivity.this.startActivity(intent2);
                    ((ApplicationContext) LoginActivity.this.getApplicationContext()).setFacultyBean(facultyBean);
                    Toast.makeText(LoginActivity.this.getApplicationContext(), "Login successful", 0).show();
                    return;
                }
                Toast.makeText(LoginActivity.this.getApplicationContext(), "Login failed", 0).show();
            }
        });
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
