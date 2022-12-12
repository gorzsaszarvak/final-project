package hu.unideb.inf.finalproject.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public String getStudents(Model model) { //todo handle exception
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("students", studentService.listStudents());

        return "studentsPage";
    }

    @PostMapping("/students")
    public String addStudent(@ModelAttribute("student") Student student) { //todo handle exception
        studentService.saveStudent(student);

        return "redirect:/students";
    }


    @PostMapping("students/update/{id}")
    public String updateStudent(@ModelAttribute("student") Student student, @PathVariable Long id) {
        studentService.updateStudent(student);

        return "redirect:/students";
    }

    @GetMapping("student/delete/{id}")
    public String deleteStudent(@PathVariable Long id) { //todo handle exception
        studentService.deleteStudentById(id);

        return "redirect:/students";
    }
}
