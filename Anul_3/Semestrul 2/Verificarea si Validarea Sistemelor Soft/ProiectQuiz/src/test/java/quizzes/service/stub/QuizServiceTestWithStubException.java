package quizzes.service.stub;

import org.junit.jupiter.api.*;
import quizzes.domain.Quiz;
import quizzes.repository.IRepository;
import quizzes.repository.RepositoryException;
import quizzes.repository.stub.RepositoryStubException;
import quizzes.service.QuizService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class QuizServiceTestWithStubException {

    private QuizService service;

    @BeforeEach
    public void setUp() throws RepositoryException {
        IRepository repo = new RepositoryStubException();
        service = new QuizService(repo);
    }

    @Tag("Add")
    @Tag("ValidQuiz")
    @Tag("Exception")
    @Test
    public void test01_add_valid_Quiz() {
        //already exists in repo
//        service.addQuiz(new Quiz("q1", 10, "Easy", 7));
//        service.addQuiz(new Quiz("q2", 15, "Medium", 3));
//        service.addQuiz(new Quiz("q3", 9, "Easy", 9));

        assertEquals(3, service.allQuizzes().size());
        try {
            service.addQuiz(new Quiz("q6", 5, "Medium", 3));
            Assertions.fail("No exception was thrown");
        } catch (RepositoryException e) {
            //behaviour of the stub for the addQuiz method - an exception is thrown
            //assert examples
            assert true;
            assertEquals(3, service.allQuizzes().size());
            assert 3 == service.allQuizzes().size();
        }
    }

    @Tag("Add")
    @Tag("InvalidQuiz")
    @Tag("Exception")
    @Test
    public void test02_add_invalid_Quiz() throws RepositoryException{
        //already exists in repo
//        service.addQuiz(new Quiz("q1", 10, "Easy", 7));
//        service.addQuiz(new Quiz("q2", 15, "Medium", 3));
//        service.addQuiz(new Quiz("q3", 9, "Easy", 9));

        assertEquals(3, service.allQuizzes().size());
        Assertions.assertThrows(RepositoryException.class, ()->{service.addQuiz(new Quiz("q6", 0, "Medium", 3));});
        //behaviour of the stub for the addQuiz method - an exception was thrown
    }

    @Tag("Valid")
    @Tag("OutputMaxScoreQuizCounter")

    @Test
    public void test03_MaxScoreQuizCounter_validOutput() {
        //already exists in repo
//        service.addQuiz(new Quiz("q1", 10, "Easy", 7));
//        service.addQuiz(new Quiz("q2", 15, "Medium", 3));
//        service.addQuiz(new Quiz("q3", 9, "Easy", 9));

        //assert examples
        assertEquals(1, service.maxScoreQuizCounter());
        assert 1 == service.maxScoreQuizCounter();
    }

    @Tag("Invalid")
    @Tag("OutputMaxScoreQuizCounter")
    @Test
    public void test04_MaxScoreQuizCounter_invalidOutput(){
        //already exists in repo
//        service.addQuiz(new Quiz("q1", 10, "Easy", 7));
//        service.addQuiz(new Quiz("q2", 15, "Medium", 3));
//        service.addQuiz(new Quiz("q3", 9, "Easy", 9));

        //assert examples
        assertNotEquals(-1, service.maxScoreQuizCounter());
        assert -1 != service.maxScoreQuizCounter();
    }

    @AfterEach
    public void tearDown(){
        service = null;
    }
}