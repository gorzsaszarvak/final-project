package hu.unideb.inf.finalproject.project.exception;

public class NoProjectsFoundException extends RuntimeException {
    public NoProjectsFoundException() {
        super("No projects found");
    }
}
