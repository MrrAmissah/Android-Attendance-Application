import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class StudentViewModel extends ViewModel {

    private final StudentRepository studentRepository;
    private final MutableLiveData<List<Student>> students;

    public StudentViewModel() {
        studentRepository = new StudentRepository();
        students = new MutableLiveData<>();
        loadStudents();
    }

    private void loadStudents() {
        students.setValue(studentRepository.getAllStudents());
    }

    public LiveData<List<Student>> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        studentRepository.addStudent(student);
        loadStudents();
    }

    public void updateStudent(Student student) {
        studentRepository.updateStudent(student);
        loadStudents();
    }

    public void deleteStudent(Student student) {
        studentRepository.deleteStudent(student);
        loadStudents();
    }
}