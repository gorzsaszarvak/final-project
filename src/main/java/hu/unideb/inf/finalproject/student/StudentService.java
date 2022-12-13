package hu.unideb.inf.finalproject.student;

import hu.unideb.inf.finalproject.student.exception.NoStudentsFoundException;

import java.util.List;

public interface StudentService {

    void saveStudent(Student student);

    void deleteStudentById(Long id);

    List<Student> listStudents() throws NoStudentsFoundException;

    Student findStudentById(Long studentId);

    void updateStudent(Student student);

}
