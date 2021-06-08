package quizzes.service.dummy.bbt;

import org.junit.jupiter.api.*;
import quizzes.domain.Quiz;
import quizzes.repository.RepositoryException;
import quizzes.repository.file.FileRepository;
import quizzes.domain.QuizValidator;
import quizzes.service.QuizService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuizServiceTestMaxCounter {
    static QuizService service;

    @BeforeAll
    public static void setup(){

    }

    @BeforeEach
    public void setUp() throws Exception {
        QuizValidator validator = new QuizValidator();
        FileRepository repo = new FileRepository(validator);
        service = new QuizService(repo);
    }

    @Tag("ECP")
    @Tag("TC1")
    @Test
    public void TC1()  {
        try {
            service.addQuiz(new Quiz("id_quiz", 10, "Easy", 1));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        assertEquals(1, service.maxScoreQuizCounter());
    }

    //@Disabled
    @Tag("ECP")
    @Tag("TC2")
    @Test
    public void TC2()  {
        //service.addQuiz(new Quiz("id_quiz", 10, "Easy", 1));
        assertEquals(-1, service.maxScoreQuizCounter());
    }


    @AfterEach
    public void tearDown() throws Exception {
        service = null;

    }

    @AfterAll
    public static void teardown(){

    }

}