package hu.unideb.inf.finalproject.project.exception;

public class ProjectAlreadyExistsException extends RuntimeException {
    public ProjectAlreadyExistsException() {
        super("Project already exists");
    }
}
