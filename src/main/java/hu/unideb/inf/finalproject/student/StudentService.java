package hu.unideb.inf.finalproject.student;

import hu.unideb.inf.finalproject.project.Project;
import hu.unideb.inf.finalproject.project.exception.NoProjectsFoundException;
import hu.unideb.inf.finalproject.student.exception.NoStudentsFoundException;
import hu.unideb.inf.finalproject.student.exception.StudentAlreadyExistsException;
import hu.unideb.inf.finalproject.student.exception.StudentNotFoundException;

import java.util.List;

public interface StudentService {

    void saveStudent(Student student) throws StudentAlreadyExistsException;

    void deleteStudentById(Long id) throws StudentNotFoundException;

    Student findStudentByName(String firstName, String lastName);

    List<Student> findStudentsById(List<Long> studentsId) throws StudentNotFoundException;

    List<Student> listStudents() throws NoStudentsFoundException;

    Student findStudentById(Long studentId) throws StudentNotFoundException;

    void updateStudent(Student student);

    List<Student> listStudentsWithProject(Project project) throws NoStudentsFoundException;
}
