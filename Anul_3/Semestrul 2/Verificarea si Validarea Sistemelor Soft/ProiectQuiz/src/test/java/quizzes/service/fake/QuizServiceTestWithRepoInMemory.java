package quizzes.service.fake;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quizzes.domain.Quiz;
import quizzes.domain.QuizValidator;
import quizzes.repository.IRepository;
import quizzes.repository.RepositoryException;
import quizzes.repository.fake.RepositoryInMemory;
import quizzes.service.QuizService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class QuizServiceTestWithRepoInMemory {

    private QuizService service;

    @BeforeEach
    public void setUp() throws RepositoryException {
        QuizValidator validator= new QuizValidator();
        IRepository repo = new RepositoryInMemory(validator);
        service = new QuizService(repo);
    }

    @Test
    public void test01_add_valid_Quiz() throws  RepositoryException{
        //already exists in repo
//        service.addQuiz(new Quiz("q1", 10, "Easy", 7));
//        service.addQuiz(new Quiz("q2", 15, "Medium", 3));
//        service.addQuiz(new Quiz("q3", 9, "Easy", 9));
//        service.addQuiz(new Quiz("q4", 5, "Hard", 2));
//        service.addQuiz(new Quiz("q5", 20, "Medium", 9));

        assertEquals(5, service.allQuizzes().size());
        service.addQuiz(new Quiz("q6", 5, "Medium", 3));

        //assert examples
        assert true;
        assertEquals(6, service.allQuizzes().size());
        assert 6 == service.allQuizzes().size();
    }


    @Test
    public void test02_add_invalid_Quiz() {
        //already exists in repo
//        service.addQuiz(new Quiz("q1", 10, "Easy", 7));
//        service.addQuiz(new Quiz("q2", 15, "Medium", 3));
//        service.addQuiz(new Quiz("q3", 9, "Easy", 9));
//        service.addQuiz(new Quiz("q4", 5, "Hard", 2));
//        service.addQuiz(new Quiz("q5", 20, "Medium", 9));

        assertEquals(5, service.allQuizzes().size());
        //fails if attempts to add that book
        Assertions.assertThrows(RepositoryException.class, ()->{service.addQuiz(new Quiz("q6", 0, "Medium", 3));});//.fail("Repository Exception not thrown");
    }

    @Test
    public void test03_MaxScoreQuizCounter_validOutput() {
        //already exists in repo
//        service.addQuiz(new Quiz("q1", 10, "Easy", 7));
//        service.addQuiz(new Quiz("q2", 15, "Medium", 3));
//        service.addQuiz(new Quiz("q3", 9, "Easy", 9));
//        service.addQuiz(new Quiz("q4", 5, "Hard", 2));
//        service.addQuiz(new Quiz("q5", 20, "Medium", 9));

        //assert examples
        assertEquals(2, service.maxScoreQuizCounter());
        assert 2 == service.maxScoreQuizCounter();
    }

    @Test
    public void test04_MaxScoreQuizCounter_invalidOutput(){
        //already exists in repo
//        service.addQuiz(new Quiz("q1", 10, "Easy", 7));
//        service.addQuiz(new Quiz("q2", 15, "Medium", 3));
//        service.addQuiz(new Quiz("q3", 9, "Easy", 9));
//        service.addQuiz(new Quiz("q4", 5, "Hard", 2));
//        service.addQuiz(new Quiz("q5", 20, "Medium", 9));

        //assert examples
        assertNotEquals(-1, service.maxScoreQuizCounter());
        assert -1 != service.maxScoreQuizCounter();
    }

    @Test
    public void test05_size_valid() throws  RepositoryException{
        //already exists in repo
//        service.addQuiz(new Quiz("q1", 10, "Easy", 7));
//        service.addQuiz(new Quiz("q2", 15, "Medium", 3));
//        service.addQuiz(new Quiz("q3", 9, "Easy", 9));
//        service.addQuiz(new Quiz("q4", 5, "Hard", 2));
//        service.addQuiz(new Quiz("q5", 20, "Medium", 9));

        //assert examples
        assert true;
        assertEquals(5, service.allQuizzes().size());
        assert 5 == service.allQuizzes().size();
    }

    @AfterEach
    public void tearDown(){
        service = null;
    }

}