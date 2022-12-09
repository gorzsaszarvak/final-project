package hu.unideb.inf.finalproject.student;

import hu.unideb.inf.finalproject.student.exception.NoStudentsFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<Void> getStudents(Model model) {
        try {
            model.addAttribute("students", studentService.listStudents());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoStudentsFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
}
