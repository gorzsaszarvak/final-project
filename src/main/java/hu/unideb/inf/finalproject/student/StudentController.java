package hu.unideb.inf.finalproject.student;

import hu.unideb.inf.finalproject.student.exception.NoStudentsFoundException;
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
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public String getStudents(Model model) {
        Student student = new Student();
        student.setProjects(List.of());
        model.addAttribute("student", student);

        try {
            model.addAttribute("students", studentService.listStudents());
        } catch (NoStudentsFoundException exception) {
            model.addAttribute("students", List.of());
            model.addAttribute("noStudentsException", exception.getMessage());
        }

        return "studentsPage";
    }

    @PostMapping("/students")
    public String addStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);

        return "redirect:/students";
    }


    @PostMapping("/students/update/{id}")
    public String updateStudent(@ModelAttribute("student") Student student, @PathVariable Long id) {
        studentService.updateStudent(student);

        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);

        return "redirect:/students";
    }
}
