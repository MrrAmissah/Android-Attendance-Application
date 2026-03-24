package com.android.attendance.context;

import android.app.Application;
import com.android.attendance.bean.AttendanceBean;
import com.android.attendance.bean.AttendanceSessionBean;
import com.android.attendance.bean.FacultyBean;
import com.android.attendance.bean.StudentBean;
import java.util.ArrayList;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public class ApplicationContext extends Application {
    private ArrayList<AttendanceBean> attendanceBeanList;
    private AttendanceSessionBean attendanceSessionBean;
    private FacultyBean facultyBean;
    private ArrayList<StudentBean> studentBeanList;

    public FacultyBean getFacultyBean() {
        return this.facultyBean;
    }

    public void setFacultyBean(FacultyBean facultyBean) {
        this.facultyBean = facultyBean;
    }

    public AttendanceSessionBean getAttendanceSessionBean() {
        return this.attendanceSessionBean;
    }

    public void setAttendanceSessionBean(AttendanceSessionBean attendanceSessionBean) {
        this.attendanceSessionBean = attendanceSessionBean;
    }

    public ArrayList<StudentBean> getStudentBeanList() {
        return this.studentBeanList;
    }

    public void setStudentBeanList(ArrayList<StudentBean> studentBeanList) {
        this.studentBeanList = studentBeanList;
    }

    public ArrayList<AttendanceBean> getAttendanceBeanList() {
        return this.attendanceBeanList;
    }

    public void setAttendanceBeanList(ArrayList<AttendanceBean> attendanceBeanList) {
        this.attendanceBeanList = attendanceBeanList;
    }
}
