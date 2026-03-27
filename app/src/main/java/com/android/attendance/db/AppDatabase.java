import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {StudentEntity.class, AttendanceEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract StudentDao studentDao();
    public abstract AttendanceDao attendanceDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "attendance_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}