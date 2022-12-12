package hu.unideb.inf.finalproject.student.exception.impl;

import hu.unideb.inf.finalproject.project.Project;
import hu.unideb.inf.finalproject.project.exception.NoProjectsFoundException;
import hu.unideb.inf.finalproject.student.Student;
import hu.unideb.inf.finalproject.student.StudentRepository;
import hu.unideb.inf.finalproject.student.StudentService;
import hu.unideb.inf.finalproject.student.exception.NoStudentsFoundException;
import hu.unideb.inf.finalproject.student.exception.StudentAlreadyExistsException;
import hu.unideb.inf.finalproject.student.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public void saveStudent(Student student) throws StudentAlreadyExistsException {
        if (!studentRepository.existsById(student.getId())) {
            studentRepository.save(student);
        } else {
            throw new StudentAlreadyExistsException();
        }
    }

    @Override
    public void deleteStudentById(Long id) throws StudentNotFoundException {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new StudentNotFoundException();
        }
    }

    @Override
    public Student findStudentByName(String firstName, String lastName) {
        Optional<Student> student = studentRepository.findStudentByFirstNameAndLastName(firstName, lastName);
        if (student.isPresent()) {
            return studentRepository.getReferenceById(student.get().getId());
        } else {
            throw new StudentAlreadyExistsException();
        }
    }

    @Override
    public List<Student> findStudentsById(List<Long> studentsId) throws StudentNotFoundException {
        List<Student> students = new ArrayList<>();
        for (Long id : studentsId) {
            Student student = studentRepository.getReferenceById(id);
            if (student != null) {
                students.add(student);
            } else {
                throw new StudentNotFoundException();
            }
        }
        return students;
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
        if (studentRepository.existsById(studentId)) {
            return studentRepository.getReferenceById(studentId);
        } else {
            throw new StudentNotFoundException();
        }
    }

    @Override
    public void updateStudent(Student student){
        studentRepository.save(student);
    }

    @Override
    public List<Student> listStudentsWithProject(Project project)
        throws StudentNotFoundException, NoProjectsFoundException {
        List<Student> students = studentRepository.findAllByProjectsContaining(project);
        if(!students.isEmpty()) {
            return students;
        } else {
            throw new NoStudentsFoundException();
        }
    }

}
