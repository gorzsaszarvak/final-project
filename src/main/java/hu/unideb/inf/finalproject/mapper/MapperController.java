package hu.unideb.inf.finalproject.mapper;

import hu.unideb.inf.finalproject.project.Project;
import hu.unideb.inf.finalproject.project.ProjectService;
import hu.unideb.inf.finalproject.project.exception.NoProjectsFoundException;
import hu.unideb.inf.finalproject.student.Student;
import hu.unideb.inf.finalproject.student.StudentService;
import hu.unideb.inf.finalproject.student.exception.NoStudentsFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MapperController {

    private final StudentService studentService;
    private final ProjectService projectService;

    @GetMapping("/students/{id}")
    public String getStudentDetails(Model model, @PathVariable Long id) {
        Student student = studentService.findStudentById(id);
        model.addAttribute("student", student);

        List<Project> projects;
        try {
            projects = projectService.listProjects();
        } catch (NoProjectsFoundException exception) {
            projects = List.of();
            model.addAttribute("noProjectsException", exception.getMessage());
        }
        model.addAttribute("allProjects", projects);

        return "studentEditPage";
    }

    @GetMapping("projects/{id}")
    public String getProjectDetails(Model model, @PathVariable Long id) {
        Project project = projectService.findProjectById(id);
        model.addAttribute("project", project);

        List<Student> students;
        try {
            students = studentService.listStudents();
        } catch (NoStudentsFoundException exception) {
            students = List.of();
            model.addAttribute("noStudentsException", exception.getMessage());
        }
        model.addAttribute("allStudents", students);

        return "projectEditPage";
    }

}
