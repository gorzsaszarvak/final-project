package hu.unideb.inf.finalproject.mapper.impl;

import hu.unideb.inf.finalproject.mapper.MapperService;
import hu.unideb.inf.finalproject.project.Project;
import hu.unideb.inf.finalproject.project.ProjectService;
import hu.unideb.inf.finalproject.project.exception.NoProjectsFoundException;
import hu.unideb.inf.finalproject.project.exception.ProjectNotFoundException;
import hu.unideb.inf.finalproject.student.Student;
import hu.unideb.inf.finalproject.student.StudentService;
import hu.unideb.inf.finalproject.student.exception.NoStudentsFoundException;
import hu.unideb.inf.finalproject.student.exception.StudentAlreadyExistsException;
import hu.unideb.inf.finalproject.student.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapperServiceImpl implements MapperService {

    private final StudentService studentService;
    private final ProjectService projectService;


    @Override
    public Student mapToStudent(String firstName, String lastName) throws StudentAlreadyExistsException{
        return Student.builder()
            .firstName(firstName)
            .lastName(lastName)
            .projects(List.of())
            .build();
    }

    @Override
    public Project mapToProject(String topic, List<Long> studentsId) throws StudentNotFoundException {
        List<Student> students = studentService.findStudentsById(studentsId);

        return Project.builder()
            .topic(topic)
            .students(students)
            .build();
    }

    @Override
    public void assignProjectToStudents(List<Long> studentsId, Long projectId)
        throws StudentNotFoundException, ProjectNotFoundException {
        List<Student> students = studentService.findStudentsById(studentsId);
        Project project = projectService.findProjectById(projectId);
    }

    @Override
    public List<Project> getStudentProjects(Long studentId) throws StudentNotFoundException, NoProjectsFoundException {
        Student student = studentService.findStudentById(studentId);
        List<Project> projects = projectService.listProjectsWithStudent(student);
        return projects;
    }

    @Override
    public List<Student> getProjectStudents(Long projectId) throws ProjectNotFoundException, NoStudentsFoundException {
        return null;
    }


}
