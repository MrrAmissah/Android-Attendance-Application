package com.android.attendance.data.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface StudentRepository {
    @Insert
    void insertStudent(Student student);

    @Update
    void updateStudent(Student student);

    @Query("SELECT * FROM students WHERE id = :studentId")
    Student getStudentById(int studentId);

    @Query("SELECT * FROM students ORDER BY name ASC")
    List<Student> getAllStudents();
}