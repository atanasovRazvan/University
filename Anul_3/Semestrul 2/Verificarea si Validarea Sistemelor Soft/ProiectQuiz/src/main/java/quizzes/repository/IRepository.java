package quizzes.repository;

import quizzes.domain.Quiz;

import java.util.List;

public interface IRepository {
    void add(Quiz quiz) throws RepositoryException;

    List<Quiz> getAll();

    int size();
}
