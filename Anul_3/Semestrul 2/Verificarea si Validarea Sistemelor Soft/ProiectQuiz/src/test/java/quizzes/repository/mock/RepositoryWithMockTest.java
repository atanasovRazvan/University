package quizzes.repository.mock;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import quizzes.domain.Quiz;
import quizzes.domain.QuizValidator;
import quizzes.repository.RepositoryException;
import quizzes.repository.fake.RepositoryInMemory;



import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class RepositoryWithMockTest {

    @InjectMocks
    private RepositoryInMemory repo;

    @Mock
    private QuizValidator validator;

    private Quiz quiz;

    @BeforeEach
    public void setUp(){

        MockitoAnnotations.initMocks(this);
//        try {
//            repo = new RepositoryInMemory(validator);
//        } catch (RepositoryException e) {
//            e.printStackTrace();
//        }
        quiz=mock(Quiz.class);
    }

    @Test
    public void add_validQuiz(){
        assert 5 == repo.getAll().size();
        System.out.println(repo.getAll().toString());
        Mockito.when(validator.validate(quiz)).thenReturn(new ArrayList<String>(Collections.singleton("abc")));

        try {
            repo.add(quiz);
        } catch (RepositoryException e) {
            e.printStackTrace();
            assert 5 == repo.size();
            System.out.println(repo.getAll().toString());
        }
        //assert 6 == repo.size();
        //System.out.println(repo.getAll().toString());

        Mockito.verify(validator, times(1)).validate(quiz);

    }

    @AfterEach
    public void tearDown(){
        repo = null;
        quiz=null;
    }

}