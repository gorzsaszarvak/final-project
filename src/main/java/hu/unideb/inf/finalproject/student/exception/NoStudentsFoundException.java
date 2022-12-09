package hu.unideb.inf.finalproject.student.exception;

public class NoStudentsFoundException extends RuntimeException {
    public NoStudentsFoundException() {
        super("No students found");
    }
}
