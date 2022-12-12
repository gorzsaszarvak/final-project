package hu.unideb.inf.finalproject.project;

import hu.unideb.inf.finalproject.project.exception.NoProjectsFoundException;
import hu.unideb.inf.finalproject.project.exception.ProjectAlreadyExistsException;
import hu.unideb.inf.finalproject.project.exception.ProjectNotFoundException;
import hu.unideb.inf.finalproject.student.Student;
import hu.unideb.inf.finalproject.student.exception.NoStudentsFoundException;

import java.util.List;

public interface ProjectService {

    List<Project> listProjects() throws NoProjectsFoundException;

    String getProjectById(int id) throws ProjectNotFoundException;

    void saveProject(Project project) throws ProjectAlreadyExistsException;

    void gradeProject(Long projectId, int grade) throws ProjectNotFoundException;

    Project findProjectById(Long projectId) throws ProjectNotFoundException;

    List<Project> listProjectsWithStudent(Student student) throws NoProjectsFoundException;

    void deleteProjectById(Long projectId) throws ProjectNotFoundException;


    void updateProject(Project project);
}
