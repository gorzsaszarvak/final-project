package hu.unideb.inf.finalproject.mapper;

import hu.unideb.inf.finalproject.project.Project;
import hu.unideb.inf.finalproject.project.exception.NoProjectsFoundException;
import hu.unideb.inf.finalproject.project.exception.ProjectNotFoundException;
import hu.unideb.inf.finalproject.student.Student;
import hu.unideb.inf.finalproject.student.exception.NoStudentsFoundException;
import hu.unideb.inf.finalproject.student.exception.StudentAlreadyExistsException;
import hu.unideb.inf.finalproject.student.exception.StudentNotFoundException;

import java.util.List;

public interface MapperService {

    Student mapToStudent(String firstName, String lastName) throws StudentAlreadyExistsException;

    Project mapToProject(String topic, List<Long> studentsId) throws StudentNotFoundException;

    void assignProjectToStudents(List<Long> studentId, Long projectId)
        throws StudentNotFoundException, ProjectNotFoundException;

    List<Project> getStudentProjects(Long studentId) throws StudentNotFoundException, NoProjectsFoundException;

    List<Student> getProjectStudents(Long projectId) throws ProjectNotFoundException, NoStudentsFoundException;
}
