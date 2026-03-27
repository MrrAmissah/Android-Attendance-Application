package com.android.attendance.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface AttendanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAttendance(Attendance attendance);

    @Update
    void updateAttendance(Attendance attendance);

    @Query("SELECT * FROM attendance")
    List<Attendance> getAllAttendance();

    @Query("SELECT * FROM attendance WHERE id = :id")
    Attendance getAttendanceById(int id);

    @Query("DELETE FROM attendance")
    void deleteAllAttendance();
}