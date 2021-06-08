package quizzes.service.spy.validation;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import quizzes.domain.Quiz;
import quizzes.domain.QuizValidator;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

public class QuizValidatorPartialMock {

    private QuizValidator validator;

    @BeforeEach
    public void setUp(){
        validator= new QuizValidator();
    }

    @Test
    public void test01_partialMock() {
        QuizValidator spiedValidator=spy(validator);

        Quiz q1= new Quiz("q1", 10, "Easy", 9);

        //the real validate method is called
        assert (spiedValidator.validate(q1).equals(new ArrayList<String>()));

        //invalid Quiz "Hardest" instead of "Hard"
        Quiz q2= new Quiz("q2", 7, "Hardest", 3);

        //the behaviour of the validate method for object q2 is stubbed to consider it a valid Quiz
        Mockito.when(spiedValidator.validate(q2)).thenReturn(new ArrayList<String>());

        //the fake validate method is called, with param q2
        assert spiedValidator.validate(q2).equals(new ArrayList<String>());

        //call again the real validate method, with param q1
        assert (spiedValidator.validate(q1).equals(new ArrayList<String>()));

        Mockito.verify(spiedValidator, times(2)).validate(q1);

        //the real validator is used to validate q2
        assert validator.validate(q2).equals(Arrays.asList("Difficulty field: [invalid difficulty not in {easy, medium, hard}]\n"));

        //the real validator is used to validate q1
        assert (validator.validate(q1).equals(new ArrayList<String>()));
    }

    @AfterEach
    public void tearDown(){
        validator = null;
    }
}