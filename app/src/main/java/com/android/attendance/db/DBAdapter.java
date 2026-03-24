package com.android.attendance.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.android.attendance.bean.AttendanceBean;
import com.android.attendance.bean.AttendanceSessionBean;
import com.android.attendance.bean.FacultyBean;
import com.android.attendance.bean.StudentBean;
import java.util.ArrayList;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public class DBAdapter extends SQLiteOpenHelper {
    private static final String ATTENDANCE_SESSION_TABLE = "attendance_session_table";
    private static final String ATTENDANCE_TABLE = "attendance_table";
    private static final String DATABASE_NAME = "Attendance";
    private static final int DATABASE_VERSION = 1;
    private static final String FACULTY_INFO_TABLE = "faculty_table";
    private static final String KEY_ATTENDANCE_SESSION_CLASS = "attendance_session_class";
    private static final String KEY_ATTENDANCE_SESSION_DATE = "attendance_session_date";
    private static final String KEY_ATTENDANCE_SESSION_DEPARTMENT = "attendance_session_department";
    private static final String KEY_ATTENDANCE_SESSION_FACULTY_ID = "attendance_session_faculty_id";
    private static final String KEY_ATTENDANCE_SESSION_ID = "attendance_session_id";
    private static final String KEY_ATTENDANCE_SESSION_SUBJECT = "attendance_session_subject";
    private static final String KEY_ATTENDANCE_STATUS = "attendance_status";
    private static final String KEY_ATTENDANCE_STUDENT_ID = "attendance_student_id";
    private static final String KEY_FACULTY_ADDRESS = "faculty_address";
    private static final String KEY_FACULTY_FIRSTNAME = "faculty_firstname";
    private static final String KEY_FACULTY_ID = "faculty_id";
    private static final String KEY_FACULTY_LASTNAME = "faculty_Lastname";
    private static final String KEY_FACULTY_MO_NO = "faculty_mobilenumber";
    private static final String KEY_FACULTY_PASSWORD = "faculty_password";
    private static final String KEY_FACULTY_USERNAME = "faculty_username";
    private static final String KEY_SESSION_ID = "attendance_session_id";
    private static final String KEY_STUDENT_ADDRESS = "student_address";
    private static final String KEY_STUDENT_CLASS = "student_class";
    private static final String KEY_STUDENT_DEPARTMENT = "student_department";
    private static final String KEY_STUDENT_FIRSTNAME = "student_firstname";
    private static final String KEY_STUDENT_ID = "student_id";
    private static final String KEY_STUDENT_LASTNAME = "student_lastname";
    private static final String KEY_STUDENT_MO_NO = "student_mobilenumber";
    private static final String STUDENT_INFO_TABLE = "student_table";

    public DBAdapter(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        Log.d("queryFaculty", "CREATE TABLE faculty_table (faculty_id INTEGER PRIMARY KEY AUTOINCREMENT, faculty_firstname TEXT, faculty_Lastname TEXT, faculty_mobilenumber TEXT, faculty_address TEXT,faculty_username TEXT,faculty_password TEXT )");
        Log.d("queryStudent", "CREATE TABLE student_table (student_id INTEGER PRIMARY KEY AUTOINCREMENT, student_firstname TEXT, student_lastname TEXT, student_mobilenumber TEXT, student_address TEXT,student_department TEXT,student_class TEXT )");
        Log.d("queryAttendanceSession", "CREATE TABLE attendance_session_table (attendance_session_id INTEGER PRIMARY KEY AUTOINCREMENT, attendance_session_faculty_id INTEGER, attendance_session_department TEXT, attendance_session_class TEXT, attendance_session_date DATE,attendance_session_subject TEXT)");
        Log.d("queryAttendance", "CREATE TABLE attendance_table (attendance_session_id INTEGER, attendance_student_id INTEGER, attendance_status TEXT )");
        try {
            db.execSQL("CREATE TABLE faculty_table (faculty_id INTEGER PRIMARY KEY AUTOINCREMENT, faculty_firstname TEXT, faculty_Lastname TEXT, faculty_mobilenumber TEXT, faculty_address TEXT,faculty_username TEXT,faculty_password TEXT )");
            db.execSQL("CREATE TABLE student_table (student_id INTEGER PRIMARY KEY AUTOINCREMENT, student_firstname TEXT, student_lastname TEXT, student_mobilenumber TEXT, student_address TEXT,student_department TEXT,student_class TEXT )");
            db.execSQL("CREATE TABLE attendance_session_table (attendance_session_id INTEGER PRIMARY KEY AUTOINCREMENT, attendance_session_faculty_id INTEGER, attendance_session_department TEXT, attendance_session_class TEXT, attendance_session_date DATE,attendance_session_subject TEXT)");
            db.execSQL("CREATE TABLE attendance_table (attendance_session_id INTEGER, attendance_student_id INTEGER, attendance_status TEXT )");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        Log.d("queryFaculty", "CREATE TABLE faculty_table (faculty_id INTEGER PRIMARY KEY AUTOINCREMENT, faculty_firstname TEXT, faculty_Lastname TEXT, faculty_mobilenumber TEXT, faculty_address TEXT,faculty_username TEXT,faculty_password TEXT )");
        Log.d("queryStudent", "CREATE TABLE student_table (student_id INTEGER PRIMARY KEY AUTOINCREMENT, student_firstname TEXT, student_lastname TEXT, student_mobilenumber TEXT, student_address TEXT,student_department TEXT,student_class TEXT )");
        Log.d("queryAttendanceSession", "CREATE TABLE attendance_session_table (attendance_session_id INTEGER PRIMARY KEY AUTOINCREMENT, attendance_session_faculty_id INTEGER, attendance_session_department TEXT, attendance_session_class TEXT, attendance_session_date TEXT,attendance_session_subject TEXT)");
        Log.d("queryAttendance", "CREATE TABLE attendance_table (attendance_session_id INTEGER, attendance_student_id INTEGER, attendance_status TEXT )");
        try {
            db.execSQL("CREATE TABLE faculty_table (faculty_id INTEGER PRIMARY KEY AUTOINCREMENT, faculty_firstname TEXT, faculty_Lastname TEXT, faculty_mobilenumber TEXT, faculty_address TEXT,faculty_username TEXT,faculty_password TEXT )");
            db.execSQL("CREATE TABLE student_table (student_id INTEGER PRIMARY KEY AUTOINCREMENT, student_firstname TEXT, student_lastname TEXT, student_mobilenumber TEXT, student_address TEXT,student_department TEXT,student_class TEXT )");
            db.execSQL("CREATE TABLE attendance_session_table (attendance_session_id INTEGER PRIMARY KEY AUTOINCREMENT, attendance_session_faculty_id INTEGER, attendance_session_department TEXT, attendance_session_class TEXT, attendance_session_date TEXT,attendance_session_subject TEXT)");
            db.execSQL("CREATE TABLE attendance_table (attendance_session_id INTEGER, attendance_student_id INTEGER, attendance_status TEXT )");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }
    }

    public void addFaculty(FacultyBean facultyBean) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "INSERT INTO faculty_table (faculty_firstname,faculty_Lastname,faculty_mobilenumber,faculty_address,faculty_username,faculty_password) values ('" + facultyBean.getFaculty_firstname() + "', '" + facultyBean.getFaculty_lastname() + "', '" + facultyBean.getFaculty_mobilenumber() + "', '" + facultyBean.getFaculty_address() + "', '" + facultyBean.getFaculty_username() + "', '" + facultyBean.getFaculty_password() + "')";
        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }

    public FacultyBean validateFaculty(String userName, String password) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM faculty_table where faculty_username='" + userName + "' and faculty_password='" + password + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (!cursor.moveToFirst()) {
            return null;
        }
        FacultyBean facultyBean = new FacultyBean();
        facultyBean.setFaculty_id(Integer.parseInt(cursor.getString(0)));
        facultyBean.setFaculty_firstname(cursor.getString(1));
        facultyBean.setFaculty_lastname(cursor.getString(2));
        facultyBean.setFaculty_mobilenumber(cursor.getString(3));
        facultyBean.setFaculty_address(cursor.getString(4));
        facultyBean.setFaculty_username(cursor.getString(5));
        facultyBean.setFaculty_password(cursor.getString(6));
        return facultyBean;
    }

    public ArrayList<FacultyBean> getAllFaculty() {
        Log.d("in get all", "in get all");
        ArrayList<FacultyBean> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM faculty_table", null);
        if (cursor.moveToFirst()) {
            do {
                FacultyBean facultyBean = new FacultyBean();
                facultyBean.setFaculty_id(Integer.parseInt(cursor.getString(0)));
                facultyBean.setFaculty_firstname(cursor.getString(1));
                facultyBean.setFaculty_lastname(cursor.getString(2));
                facultyBean.setFaculty_mobilenumber(cursor.getString(3));
                facultyBean.setFaculty_address(cursor.getString(4));
                facultyBean.setFaculty_username(cursor.getString(5));
                facultyBean.setFaculty_password(cursor.getString(6));
                list.add(facultyBean);
            } while (cursor.moveToNext());
            return list;
        }
        return list;
    }

    public void deleteFaculty(int facultyId) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM faculty_table WHERE faculty_id=" + facultyId;
        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }

    public void addStudent(StudentBean studentBean) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "INSERT INTO student_table (student_firstname,student_lastname,student_mobilenumber,student_address,student_department,student_class) values ('" + studentBean.getStudent_firstname() + "', '" + studentBean.getStudent_lastname() + "','" + studentBean.getStudent_mobilenumber() + "', '" + studentBean.getStudent_address() + "', '" + studentBean.getStudent_department() + "', '" + studentBean.getStudent_class() + "')";
        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }

    public ArrayList<StudentBean> getAllStudent() {
        ArrayList<StudentBean> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM student_table", null);
        if (cursor.moveToFirst()) {
            do {
                StudentBean studentBean = new StudentBean();
                studentBean.setStudent_id(Integer.parseInt(cursor.getString(0)));
                studentBean.setStudent_firstname(cursor.getString(1));
                studentBean.setStudent_lastname(cursor.getString(2));
                studentBean.setStudent_mobilenumber(cursor.getString(3));
                studentBean.setStudent_address(cursor.getString(4));
                studentBean.setStudent_department(cursor.getString(5));
                studentBean.setStudent_class(cursor.getString(6));
                list.add(studentBean);
            } while (cursor.moveToNext());
            return list;
        }
        return list;
    }

    public ArrayList<StudentBean> getAllStudentByBranchYear(String branch, String year) {
        ArrayList<StudentBean> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM student_table where student_department='" + branch + "' and student_class='" + year + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                StudentBean studentBean = new StudentBean();
                studentBean.setStudent_id(Integer.parseInt(cursor.getString(0)));
                studentBean.setStudent_firstname(cursor.getString(1));
                studentBean.setStudent_lastname(cursor.getString(2));
                studentBean.setStudent_mobilenumber(cursor.getString(3));
                studentBean.setStudent_address(cursor.getString(4));
                studentBean.setStudent_department(cursor.getString(5));
                studentBean.setStudent_class(cursor.getString(6));
                list.add(studentBean);
            } while (cursor.moveToNext());
            return list;
        }
        return list;
    }

    public StudentBean getStudentById(int studentId) {
        StudentBean studentBean = new StudentBean();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM student_table where student_id=" + studentId;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                studentBean.setStudent_id(Integer.parseInt(cursor.getString(0)));
                studentBean.setStudent_firstname(cursor.getString(1));
                studentBean.setStudent_lastname(cursor.getString(2));
                studentBean.setStudent_mobilenumber(cursor.getString(3));
                studentBean.setStudent_address(cursor.getString(4));
                studentBean.setStudent_department(cursor.getString(5));
                studentBean.setStudent_class(cursor.getString(6));
            } while (cursor.moveToNext());
            return studentBean;
        }
        return studentBean;
    }

    public void deleteStudent(int studentId) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM student_table WHERE student_id=" + studentId;
        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }

    public int addAttendanceSession(AttendanceSessionBean attendanceSessionBean) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "INSERT INTO attendance_session_table (attendance_session_faculty_id,attendance_session_department,attendance_session_class,attendance_session_date,attendance_session_subject) values ('" + attendanceSessionBean.getAttendance_session_faculty_id() + "', '" + attendanceSessionBean.getAttendance_session_department() + "','" + attendanceSessionBean.getAttendance_session_class() + "', '" + attendanceSessionBean.getAttendance_session_date() + "', '" + attendanceSessionBean.getAttendance_session_subject() + "')";
        Log.d("query", query);
        db.execSQL(query);
        Cursor cursor = db.rawQuery("select max(attendance_session_id) from attendance_session_table", null);
        if (cursor.moveToFirst()) {
            int sessionId = Integer.parseInt(cursor.getString(0));
            return sessionId;
        }
        db.close();
        return 0;
    }

    public ArrayList<AttendanceSessionBean> getAllAttendanceSession() {
        ArrayList<AttendanceSessionBean> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM attendance_session_table", null);
        if (cursor.moveToFirst()) {
            do {
                AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
                attendanceSessionBean.setAttendance_session_id(Integer.parseInt(cursor.getString(0)));
                attendanceSessionBean.setAttendance_session_faculty_id(Integer.parseInt(cursor.getString(1)));
                attendanceSessionBean.setAttendance_session_department(cursor.getString(2));
                attendanceSessionBean.setAttendance_session_class(cursor.getString(3));
                attendanceSessionBean.setAttendance_session_date(cursor.getString(4));
                attendanceSessionBean.setAttendance_session_subject(cursor.getString(5));
                list.add(attendanceSessionBean);
            } while (cursor.moveToNext());
            return list;
        }
        return list;
    }

    public void deleteAttendanceSession(int attendanceSessionId) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM attendance_session_table WHERE attendance_session_id=" + attendanceSessionId;
        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }

    public void addNewAttendance(AttendanceBean attendanceBean) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "INSERT INTO attendance_table values (" + attendanceBean.getAttendance_session_id() + ", " + attendanceBean.getAttendance_student_id() + ", '" + attendanceBean.getAttendance_status() + "')";
        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x00bb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x008e A[LOOP:1: B:8:0x008e->B:9:0x00b9, LOOP_START] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.ArrayList<com.android.attendance.bean.AttendanceBean> getAttendanceBySessionID(com.android.attendance.bean.AttendanceSessionBean r11) {
        /*
            r10 = this;
            r0 = 0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.database.sqlite.SQLiteDatabase r2 = r10.getWritableDatabase()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "SELECT * FROM attendance_session_table where attendance_session_faculty_id="
            r3.append(r4)
            int r4 = r11.getAttendance_session_faculty_id()
            r3.append(r4)
            java.lang.String r4 = " AND attendance_session_department='"
            r3.append(r4)
            java.lang.String r4 = r11.getAttendance_session_department()
            r3.append(r4)
            java.lang.String r4 = "' AND attendance_session_class='"
            r3.append(r4)
            java.lang.String r4 = r11.getAttendance_session_class()
            r3.append(r4)
            java.lang.String r4 = "' AND attendance_session_date='"
            r3.append(r4)
            java.lang.String r4 = r11.getAttendance_session_date()
            r3.append(r4)
            java.lang.String r4 = "' AND attendance_session_subject='"
            r3.append(r4)
            java.lang.String r4 = r11.getAttendance_session_subject()
            r3.append(r4)
            java.lang.String r4 = "'"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r4 = 0
            android.database.Cursor r5 = r2.rawQuery(r3, r4)
            boolean r6 = r5.moveToFirst()
            r7 = 0
            if (r6 == 0) goto L6e
        L60:
            java.lang.String r6 = r5.getString(r7)
            int r0 = java.lang.Integer.parseInt(r6)
            boolean r6 = r5.moveToNext()
            if (r6 != 0) goto L60
        L6e:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "SELECT * FROM attendance_table where attendance_session_id="
            r6.append(r8)
            r6.append(r0)
            java.lang.String r8 = " order by attendance_student_id"
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            android.database.Cursor r4 = r2.rawQuery(r6, r4)
            boolean r8 = r4.moveToFirst()
            if (r8 == 0) goto Lbb
        L8e:
            com.android.attendance.bean.AttendanceBean r8 = new com.android.attendance.bean.AttendanceBean
            r8.<init>()
            java.lang.String r9 = r4.getString(r7)
            int r9 = java.lang.Integer.parseInt(r9)
            r8.setAttendance_session_id(r9)
            r9 = 1
            java.lang.String r9 = r4.getString(r9)
            int r9 = java.lang.Integer.parseInt(r9)
            r8.setAttendance_student_id(r9)
            r9 = 2
            java.lang.String r9 = r4.getString(r9)
            r8.setAttendance_status(r9)
            r1.add(r8)
            boolean r8 = r4.moveToNext()
            if (r8 != 0) goto L8e
        Lbb:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.attendance.db.DBAdapter.getAttendanceBySessionID(com.android.attendance.bean.AttendanceSessionBean):java.util.ArrayList");
    }

    public ArrayList<AttendanceBean> getTotalAttendanceBySessionID(AttendanceSessionBean attendanceSessionBean) {
        ArrayList<AttendanceBean> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM attendance_session_table where attendance_session_faculty_id=" + attendanceSessionBean.getAttendance_session_faculty_id() + " AND attendance_session_department='" + attendanceSessionBean.getAttendance_session_department() + "' AND attendance_session_class='" + attendanceSessionBean.getAttendance_session_class() + "' AND attendance_session_subject='" + attendanceSessionBean.getAttendance_session_subject() + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int attendanceSessionId = Integer.parseInt(cursor.getString(0));
                String query1 = "SELECT * FROM attendance_table where attendance_session_id=" + attendanceSessionId + " order by attendance_student_id";
                Cursor cursor1 = db.rawQuery(query1, null);
                if (cursor1.moveToFirst()) {
                    do {
                        AttendanceBean attendanceBean = new AttendanceBean();
                        attendanceBean.setAttendance_session_id(Integer.parseInt(cursor1.getString(0)));
                        attendanceBean.setAttendance_student_id(Integer.parseInt(cursor1.getString(1)));
                        attendanceBean.setAttendance_status(cursor1.getString(2));
                        list.add(attendanceBean);
                    } while (cursor1.moveToNext());
                    AttendanceBean attendanceBean2 = new AttendanceBean();
                    attendanceBean2.setAttendance_session_id(0);
                    attendanceBean2.setAttendance_status("Date : " + cursor.getString(4));
                    list.add(attendanceBean2);
                } else {
                    AttendanceBean attendanceBean22 = new AttendanceBean();
                    attendanceBean22.setAttendance_session_id(0);
                    attendanceBean22.setAttendance_status("Date : " + cursor.getString(4));
                    list.add(attendanceBean22);
                }
            } while (cursor.moveToNext());
            return list;
        }
        return list;
    }

    public ArrayList<AttendanceBean> getAllAttendanceByStudent() {
        ArrayList<AttendanceBean> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Log.d("query", "SELECT attendance_student_id,count(*) FROM attendance_table where attendance_status='P' group by attendance_student_id");
        Cursor cursor = db.rawQuery("SELECT attendance_student_id,count(*) FROM attendance_table where attendance_status='P' group by attendance_student_id", null);
        if (cursor.moveToFirst()) {
            do {
                Log.d("studentId", "studentId:" + cursor.getString(0) + ", Count:" + cursor.getString(1));
                AttendanceBean attendanceBean = new AttendanceBean();
                attendanceBean.setAttendance_student_id(Integer.parseInt(cursor.getString(0)));
                attendanceBean.setAttendance_session_id(Integer.parseInt(cursor.getString(1)));
                list.add(attendanceBean);
            } while (cursor.moveToNext());
            return list;
        }
        return list;
    }
}
