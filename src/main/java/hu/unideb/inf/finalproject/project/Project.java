package hu.unideb.inf.finalproject.project;

import hu.unideb.inf.finalproject.student.Student;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToMany
    @JoinTable(name = "STUDENTS_PROJECTS",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;
    @Column(name = "topic", unique = true)
    private String topic;
    @Column(name = "grade")
    private Integer grade;

    public String getStudentsAsString() {
        List<String> studentsString =
            students.stream().map(x -> String.join(" ", x.getFirstName(), x.getLastName())).toList();
        if (studentsString.isEmpty()) {
            return "No students assigned yet";
        }
        return String.join(", ", studentsString);
    }

    public String getGradeAsString() {
        if (grade != null) {
            return grade.toString();
        } else {
            return "Not yet graded";
        }
    }
}