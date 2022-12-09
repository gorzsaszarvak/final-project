package hu.unideb.inf.finalproject.project.impl;

import hu.unideb.inf.finalproject.project.Project;
import hu.unideb.inf.finalproject.project.ProjectRepository;
import hu.unideb.inf.finalproject.project.ProjectService;
import hu.unideb.inf.finalproject.project.exception.NoProjectsFoundException;
import hu.unideb.inf.finalproject.project.exception.ProjectAlreadyExistsException;
import hu.unideb.inf.finalproject.project.exception.ProjectNotFoundException;
import hu.unideb.inf.finalproject.student.Student;
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
    public String getProjectById(int id) throws ProjectNotFoundException {
        Project project = findProjectById((long)id);
        StringBuilder sb = new StringBuilder();
        sb.append("Project id: ").append(project.getId());
        if(project.getTopic() != null) {
            sb.append(", topic: ").append(project.getTopic());
        } else {
            sb.append(" topic not yet chosen");
        }
        if(project.getStudents() != null) {
            sb.append(", students: ");
            project.getStudents().forEach(x -> sb.append(String.format("%s %s, ", x.getFirstName(), x.getLastName())));
        } else {
            sb.append(", students not yet assigned, ");
        }
        if(project.getGrade() != null) {
            sb.append(String.format("grade: %d", project.getGrade()));
        } else {
            sb.append("project not graded yet.");
        }

        return sb.toString();
    }

    @Override
    public void saveProject(Project project) throws ProjectAlreadyExistsException {
        if(!projectRepository.existsById(project.getId())) {
            projectRepository.save(project);
        } else {
            throw new ProjectAlreadyExistsException();
        }
    }


    @Override
    public void gradeProject(Long projectId, int grade) throws ProjectNotFoundException {
        if (projectRepository.existsById(projectId)) {
            Project project = projectRepository.getReferenceById(projectId);
            project.setGrade(grade);
        } else {
            throw new ProjectNotFoundException();
        }
    }

    @Override
    public Project findProjectById(Long projectId) throws ProjectNotFoundException {
        if(projectRepository.existsById(projectId)) {
            return projectRepository.getReferenceById(projectId);
        } else {
            throw new ProjectNotFoundException();
        }
    }

    @Override
    public List<Project> listProjectsByStudent(Student student) throws NoProjectsFoundException {
        List<Project> projects = projectRepository.findAllByStudentsContaining(student);
        if(!projects.isEmpty()) {
            return projects;
        } else {
            throw new NoProjectsFoundException();
        }
    }


}
