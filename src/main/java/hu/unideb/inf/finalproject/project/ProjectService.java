package hu.unideb.inf.finalproject.project;

import hu.unideb.inf.finalproject.project.exception.NoProjectsFoundException;

import java.util.List;

public interface ProjectService {

    List<Project> listProjects() throws NoProjectsFoundException;

    void saveProject(Project project);

    Project findProjectById(Long projectId);

    void deleteProjectById(Long projectId);


    void updateProject(Project project);
}
