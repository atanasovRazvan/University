package quizzes.service.dummy.bbt;

import org.junit.jupiter.api.*;
import quizzes.domain.Quiz;
import quizzes.repository.RepositoryException;
import quizzes.repository.file.FileRepository;
import quizzes.domain.QuizValidator;
import quizzes.service.QuizService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("BBT")
public class QuizServiceTest {

    private QuizService service;

    @BeforeEach
    public void setUp(){
        QuizValidator validator= new QuizValidator();
        FileRepository repo = new FileRepository(validator);
        service = new QuizService(repo);
    }

    @Tag("ECP")
    @Tag("validmaxScoreQuizCounter")
    @Test
    public void test_valid_maxScoreQuizCounter() {
        try {
        service.addQuiz(new Quiz("q1", 10, "Easy", 7));
        service.addQuiz(new Quiz("q2", 15, "Medium", 3));
        service.addQuiz(new Quiz("q3", 9, "Easy", 9));
        service.addQuiz(new Quiz("q4", 5, "Hard", 2));
        service.addQuiz(new Quiz("q5", 20, "Medium", 9));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        assertEquals(2, service.maxScoreQuizCounter());
    }

    @Tag("ECP")
    @Tag("validQNumber")
    @Test
    public void test_valid_Quiz_QNumber() {
        try {
            service.addQuiz(new Quiz("q6", 5, "Medium", 3));
            assertEquals(1, service.allQuizzes().size());
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        assertEquals(1, service.maxScoreQuizCounter());
    }

    @Tag("ECP")
    @Tag("invalidQNumber")
    @Test
    public void test_invalid_Quiz_QNumber() {
        try {
            service.addQuiz(new Quiz("q6", 0, "Medium", 3));
            Assertions.fail("Repository Exception not thrown");

        } catch (RepositoryException e) {
            //System.out.println(e.getMessage());
            Assertions.assertTrue(e.getMessage().equals("[No. of questions field: [negative no. of questions]\n" +
                    ", No. of correct answers field: [no. of correct answers > no. of questions]\n" +
                    "]"));
        }
        assertEquals(-1, service.maxScoreQuizCounter());
    }

    @Tag("BVA")
    @Tag("invalidMaxScoreQuizCounter")
    @Test
    public void test_invalid_MaxScoreQuizCounter(){
        assertEquals(-1, service.maxScoreQuizCounter());
    }

    @AfterEach
    public void tearDown(){
        service = null;
    }
}