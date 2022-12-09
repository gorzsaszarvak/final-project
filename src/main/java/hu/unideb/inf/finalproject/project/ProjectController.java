package hu.unideb.inf.finalproject.project;

import hu.unideb.inf.finalproject.project.exception.NoProjectsFoundException;
import hu.unideb.inf.finalproject.project.exception.ProjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/projects")
    public ResponseEntity<Void> listProjects(Model model) {
        try {
            model.addAttribute("projects", projectService.listProjects());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoProjectsFoundException exception) {
            //todo handle exception better
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
