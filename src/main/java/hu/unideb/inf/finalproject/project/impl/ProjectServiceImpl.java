package hu.unideb.inf.finalproject.project.impl;

import hu.unideb.inf.finalproject.project.Project;
import hu.unideb.inf.finalproject.project.ProjectRepository;
import hu.unideb.inf.finalproject.project.ProjectService;
import hu.unideb.inf.finalproject.project.exception.NoProjectsFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public List<Project> listProjects() throws NoProjectsFoundException {
        List<Project> projects = projectRepository.findAll();
        if(!projects.isEmpty()) {
            return projects;
        } else {
            throw new NoProjectsFoundException();
        }
    }

    @Override
    public void saveProject(Project project){
        if(!projectRepository.existsById(project.getId())) {
            projectRepository.save(project);
        }
    }


    @Override
    public Project findProjectById(Long projectId){
        return projectRepository.getReferenceById(projectId);
    }


    @Override
    public void deleteProjectById(Long projectId){
        projectRepository.deleteById(projectId);
    }

    @Override
    public void updateProject(Project project) {
        projectRepository.save(project);
    }


}
