package hu.unideb.inf.finalproject.project;

import hu.unideb.inf.finalproject.student.Student;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "PROJECTS_TABLE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue
    private long id;
    @ManyToMany(mappedBy = "projects")
    private List<Student> students;
    @Column(name = "topic")
    private String topic;
    @Column(name = "grade")
    private Integer grade;
}