package quizzes.service.wbt;

import org.junit.jupiter.api.*;
import quizzes.domain.Quiz;
import quizzes.repository.file.FileRepository;
import quizzes.domain.QuizValidator;
import quizzes.service.QuizService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuizServiceTest_MaxScoreQuizCounter {

    QuizService service;

    @BeforeAll
    public static void setup(){

    }

    @BeforeEach
    public void setUp() throws Exception {
        QuizValidator validator = new QuizValidator();
        FileRepository repository = new FileRepository(validator);
        service = new QuizService(repository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        service = null;

    }

    @Tag("LC")
    @Tag("Niterations")
    @Test
    public void TC1() throws Exception {
        service.addQuiz(new Quiz("q1", 15, "Easy", 7));
        service.addQuiz(new Quiz("q2", 25, "Medium", 8));
        service.addQuiz(new Quiz("q3", 10, "Hard", 8));

        assertEquals(2, service.maxScoreQuizCounter());
    }

    @Tag("LC")
    @Tag("0iterations")
    @Test
    public void TC2() throws Exception {
        Assertions.assertEquals(-1, service.maxScoreQuizCounter());
    }

    @AfterAll
    public static void teardown(){

    }

}