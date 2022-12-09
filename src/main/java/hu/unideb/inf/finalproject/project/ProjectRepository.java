package hu.unideb.inf.finalproject.project;

import hu.unideb.inf.finalproject.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsById(Long projectId);

    List<Project> findAllByStudentsContaining(Student student);
}
