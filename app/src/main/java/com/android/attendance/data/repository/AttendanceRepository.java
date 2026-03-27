import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.android.attendance.data.local.AttendanceDao;
import com.android.attendance.data.model.Attendance;
import java.util.List;

public class AttendanceRepository {
    private final AttendanceDao attendanceDao;
    private final MutableLiveData<List<Attendance>> allAttendance = new MutableLiveData<>();

    public AttendanceRepository(AttendanceDao attendanceDao) {
        this.attendanceDao = attendanceDao;
    }

    public LiveData<List<Attendance>> getAllAttendance() {
        loadAllAttendance();
        return allAttendance;
    }

    private void loadAllAttendance() {
        // Async operation - this should be done in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Attendance> attendanceList = attendanceDao.getAllAttendance();
                allAttendance.postValue(attendanceList);
            }
        }).start();
    }

    public void insert(Attendance attendance) {
        // Implement insert operation - this should also be done in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                attendanceDao.insert(attendance);
            }
        }).start();
    }
}