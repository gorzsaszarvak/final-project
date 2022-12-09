package hu.unideb.inf.finalproject.mapper;

import hu.unideb.inf.finalproject.project.Project;
import hu.unideb.inf.finalproject.project.ProjectService;
import hu.unideb.inf.finalproject.project.exception.NoProjectsFoundException;
import hu.unideb.inf.finalproject.project.exception.ProjectAlreadyExistsException;
import hu.unideb.inf.finalproject.project.exception.ProjectNotFoundException;
import hu.unideb.inf.finalproject.student.Student;
import hu.unideb.inf.finalproject.student.StudentService;
import hu.unideb.inf.finalproject.student.exception.NoStudentsFoundException;
import hu.unideb.inf.finalproject.student.exception.StudentAlreadyExistsException;
import hu.unideb.inf.finalproject.student.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MapperController {

    private final MapperService mapperService;
    private final StudentService studentService;
    private final ProjectService projectService;

    @PostMapping("/projects/newProject")
    public ResponseEntity<Void> createProject(@ModelAttribute("topic") String topic, @ModelAttribute("studentsId") List<Long> studentsId) {
        try {
            Project project = mapperService.mapToProject(topic, studentsId);
            projectService.saveProject(project);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (StudentNotFoundException | ProjectAlreadyExistsException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/students/newStudent")
    public ResponseEntity<Void> createStudent(@ModelAttribute("firstName") String firstName, @ModelAttribute("lastName") String lastName) {
        try {
            Student student = mapperService.mapToStudent(firstName, lastName);
            studentService.saveStudent(student);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (StudentAlreadyExistsException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Void> getStudentProjects(Model model, @PathVariable String id) {
        try {
            List<Project> projects = mapperService.getStudentProjects(Long.getLong(id));
            model.addAttribute("studentProjects", projects);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (StudentNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoProjectsFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<Void> getProjectStudents(Model model, @PathVariable String id) {
        try {
            List<Student> students = mapperService.getProjectStudents(Long.getLong(id));
            model.addAttribute("projectStudents", students);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ProjectNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoStudentsFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
