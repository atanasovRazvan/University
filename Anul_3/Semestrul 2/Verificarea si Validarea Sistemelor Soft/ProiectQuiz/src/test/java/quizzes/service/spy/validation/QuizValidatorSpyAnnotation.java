package quizzes.service.spy.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import quizzes.domain.Quiz;
import quizzes.domain.QuizValidator;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.spy;

public class QuizValidatorSpyAnnotation {

    @Spy
    private QuizValidator validator;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test01_spy() {
        //invalid Quiz "Hardest" instead of "Hard"
        Quiz q1= new Quiz("q1", 7, "Hardest", 3);

        //the real validate method is called
        assert (validator.validate(q1).equals(Arrays.asList("Difficulty field: [invalid difficulty not in {easy, medium, hard}]\n")));

        //the behaviour of validate for object q2 is stubbed to consider it a valid Quiz
        Mockito.when(validator.validate(q1)).thenReturn(new ArrayList<String>());

        assert validator.validate(q1).equals(new ArrayList<String>());

        //invalid Quiz "Hardest" instead of "Hard"
        Quiz q2= new Quiz("q2", 7, "Hardest", 3);

        //assert examples
        assert validator.validate(q2).equals(new ArrayList<String>()) == false;
        assert validator.validate(q2).equals(Arrays.asList("Difficulty field: [invalid difficulty not in {easy, medium, hard}]\n"));
    }

    @AfterEach
    public void tearDown(){
        validator = null;
    }
}