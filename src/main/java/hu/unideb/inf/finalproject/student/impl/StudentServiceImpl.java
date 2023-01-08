package hu.unideb.inf.finalproject.student.impl;

import hu.unideb.inf.finalproject.student.Student;
import hu.unideb.inf.finalproject.student.StudentRepository;
import hu.unideb.inf.finalproject.student.StudentService;
import hu.unideb.inf.finalproject.student.exception.NoStudentsFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public void saveStudent(Student student){
        studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }


    @Override
    public List<Student> listStudents() throws NoStudentsFoundException {
        List<Student> students = studentRepository.findAll();
        if (!students.isEmpty()) {
            return students;
        } else {
            throw new NoStudentsFoundException();
        }
    }

    @Override
    public Student findStudentById(Long studentId) {
        return studentRepository.getReferenceById(studentId);

    }

    @Override
    public void updateStudent(Student student){
        studentRepository.save(student);
    }

}
