package hu.unideb.inf.finalproject.student.exception;

public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException() {
        super("Student already exists");
    }
}
