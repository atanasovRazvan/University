package quizzes.service.stub;

import org.junit.jupiter.api.*;
import quizzes.domain.Quiz;
import quizzes.repository.IRepository;
import quizzes.repository.RepositoryException;
import quizzes.repository.stub.RepositoryStub;
import quizzes.service.QuizService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class QuizServiceTestWithStub {

    private QuizService service;

    @BeforeEach
    public void setUp() throws RepositoryException {
        IRepository repo = new RepositoryStub();
        service = new QuizService(repo);
    }

    @Tag("Add")
    @Tag("ValidQuiz")
    @Test
    public void test01_add_valid_Quiz() throws  RepositoryException{
        //already exists in repo
//        service.addQuiz(new Quiz("q1", 10, "Easy", 7));
//        service.addQuiz(new Quiz("q2", 15, "Medium", 3));
//        service.addQuiz(new Quiz("q3", 9, "Easy", 9));

        assertEquals(3, service.allQuizzes().size());
        service.addQuiz(new Quiz("q6", 5, "Medium", 3));

        //behaviour of the stub for the addQuiz method - no quiz was added
        //assert examples
        assert true;
        assertEquals(3, service.allQuizzes().size());
        assert 3 == service.allQuizzes().size();
    }

    @Tag("Add")
    @Tag("InvalidQuiz")
    @Tag("Quiz-NoException")
    @Test
    public void test02_add_invalid_Quiz() throws RepositoryException{
        System.out.println("test02_add_invalidQuiz() will always fail as the stub does not throw the expected exception" );
        //already exists in repo
//        service.addQuiz(new Quiz("q1", 10, "Easy", 7));
//        service.addQuiz(new Quiz("q2", 15, "Medium", 3));
//        service.addQuiz(new Quiz("q3", 9, "Easy", 9));

        assertEquals(3, service.allQuizzes().size());
        service.addQuiz(new Quiz("q6", 0, "Medium", 3));
        //behaviour of the stub for the addQuiz method - no quiz was added
        //RepositoryStub cannot be used to test the behaviour for invalid quizzes
        //this test will always fail, as no exceptions was thrown by the stub !!!
        //we need to define stub(s) that throw exceptions...
        //Assertions.fail("Repository Exception not thrown");
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