package hu.unideb.inf.finalproject.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/projects")
    public String listProjects(Model model) { //todo handle exception
        Project project = new Project();
        model.addAttribute("project", project);
        model.addAttribute("projects", projectService.listProjects());

        return "projectsPage";

    }

    @PostMapping("/projects")
    public String addProject(@ModelAttribute("project") Project project) { //todo handle exception
        projectService.saveProject(project);

        return "redirect:/projects";
    }

    @PostMapping("projects/update/{id}")
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
