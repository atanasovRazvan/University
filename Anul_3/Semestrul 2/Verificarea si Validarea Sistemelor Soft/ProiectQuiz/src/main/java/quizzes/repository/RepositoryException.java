package quizzes.repository;

public class RepositoryException extends Exception {
    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException() {
        super();
    }
}
