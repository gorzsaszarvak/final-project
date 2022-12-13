package hu.unideb.inf.finalproject.project;

import hu.unideb.inf.finalproject.project.exception.NoProjectsFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/projects")
    public String listProjects(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        try {
            model.addAttribute("projects", projectService.listProjects());
        } catch (NoProjectsFoundException exception) {
            model.addAttribute("projects", List.of());
            model.addAttribute("noProjectsException", exception.getMessage());
        }

        return "projectsPage";

    }

    @PostMapping("/projects")
    public String addProject(@ModelAttribute("project") Project project) {
        projectService.saveProject(project);

        return "redirect:/projects";
    }

    @PostMapping("/projects/update/{id}")
    public String updateProject(@ModelAttribute("project") Project project, @PathVariable Long id){
        projectService.updateProject(project);

        return "redirect:/projects";
    }

    @GetMapping("/projects/delete/{id}")
    public String deleteProject(@PathVariable Long id) { //todo handle exception
        projectService.deleteProjectById(id);

        return "redirect:/projects";
    }
}
