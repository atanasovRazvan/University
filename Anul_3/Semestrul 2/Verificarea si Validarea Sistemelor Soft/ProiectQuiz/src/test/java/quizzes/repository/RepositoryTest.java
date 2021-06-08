package quizzes.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import quizzes.domain.Quiz;
import quizzes.repository.file.FileRepository;
import quizzes.domain.QuizValidator;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositoryTest {

    private FileRepository repo;

    @BeforeEach
    public void setUp(){
        QuizValidator validator= new QuizValidator();
        try {
            repo = new FileRepository("data/quizzes.txt", validator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void add_validQuiz(){
        int counter = repo.getAll().size();
        Quiz quiz = new Quiz("quiz", 11, "Hard", 3);
        repo.save(quiz);
        assertEquals(counter+1, repo.getAll().size());
    }


    @Test
    public void add_invalidQuiz()  {
        int counter = repo.getAll().size();
        Quiz quiz = new Quiz("new_quiz", 0, "Hard", 3);
        repo.save(quiz);
        assertEquals(counter, repo.getAll().size());
    }

    @Disabled
    @Test
    public void add_invalidQuiz1()  {

    }

    @AfterEach
    public void tearDown(){
        repo = null;
    }

}