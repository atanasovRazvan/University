package quizzes.service.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import quizzes.domain.Quiz;
import quizzes.repository.IRepository;
import quizzes.repository.RepositoryException;
import quizzes.service.QuizService;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

public class QuizServiceTestWithMockConstructor {

    private IRepository repo;

    private QuizService service;

    @BeforeEach
    public void setUp(){
        repo=mock(IRepository.class);
        service = new QuizService(repo);
    }

    @Test
    public void test01_size_quizRepo1() throws RepositoryException{
      Mockito.when(repo.getAll()).thenReturn(new ArrayList<Quiz>());

      assert 0 == service.allQuizzes().size();

      Mockito.verify(repo).getAll();
      Mockito.verify(repo, times(1)).getAll();

      Mockito.verify(repo, never()).add(new Quiz("q6", 5, "Medium", 3));

      //assert examples
      Assertions.assertEquals(0, service.allQuizzes().size());
      assert 0 == service.allQuizzes().size();
    }


    @Test
    public void test02_size_quizRepo2() throws RepositoryException{

        Quiz q1= new Quiz("q1", 10, "Easy", 9);
        Quiz q2= new Quiz("q2", 7, "Medium", 3);
        Mockito.when(repo.getAll()).thenReturn(Arrays.asList(q1,q2));

        assert 2 == service.allQuizzes().size();

        Mockito.verify(repo).getAll();
        Mockito.verify(repo, times(1)).getAll();

        Mockito.verify(repo, never()).add(new Quiz("q6", 5, "Medium", 3));

        //assert examples
        assertEquals(2, service.allQuizzes().size());
        assert 2 == service.allQuizzes().size();

        Mockito.verify(repo, times(3)).getAll();
    }


    @Test
    public void test03_size_quizRepo3() throws RepositoryException {
       Mockito.when(repo.size()).thenReturn(1);

        //assert examples
        assert 1 == service.size();
        assertEquals(1, service.size());
        Assertions.assertTrue(service.size() == 1);

        Mockito.verify(repo, times(3)).size();
    }


    @Test
    public void test04_add_valid_Quiz1() throws RepositoryException {
      Quiz q= new Quiz("q", 5, "Medium", 3);
      Quiz q1= new Quiz("q1", 10, "Easy", 9);
      Mockito.when(repo.getAll()).thenReturn(Arrays.asList(q1));

      service.addQuiz(q);
      service.allQuizzes().forEach(System.out::println);
      assertEquals(1, service.allQuizzes().size());
      Mockito.verify(repo, times(1)).add(q);

      //Mockito.verify(repo, never()).getAll();

      //assert examples
      assert true;
      assertEquals(1, service.allQuizzes().size());
      assert 1 == service.allQuizzes().size();

      //Mockito.verify(repo, times(2)).getAll();
    }


    @Test
    public void test05_add_valid_Quiz2() throws RepositoryException {
        Quiz q= new Quiz("q", 5, "Medium", 3);
        Quiz q1= new Quiz("q1", 10, "Easy", 9);
        Mockito.when(repo.getAll()).thenReturn(Arrays.asList(q1));
        Mockito.doNothing().when(repo).add(q);//same behaviour as test_add_valid_Quiz2

        service.addQuiz(q);

        Mockito.verify(repo, times(1)).add(q);
        Mockito.verify(repo, never()).getAll();

        //assert examples
        assert true;
        assertEquals(1, service.allQuizzes().size());
        assert 1 == service.allQuizzes().size();

        Mockito.verify(repo, times(2)).getAll();
    }


    @Test
    public void test06_add_invalid_Quiz1() throws RepositoryException {
        Quiz q1= new Quiz("q1", 6, "Medium", 5);
        Quiz q2= new Quiz("q2", -3, "Hard", 2);

        Mockito.doThrow(new RepositoryException("invalid quiz")).when(repo).add(q2);
        try{
            service.addQuiz(q2);
        } catch (RepositoryException e) {
            Assertions.assertTrue(e instanceof RepositoryException);
            Assertions.assertTrue(e.getMessage().equals("invalid quiz"));
        }

        //Mockito.verify(repo, times(1)).add(q1);
        //Mockito.verify(repo, times(1)).add(q2);
    }



    @Test//(expected = RepositoryException.class)
    public void test07_add_invalid_Quiz2() throws RepositoryException{
        Quiz q1= new Quiz("q1", 6, "Medium", 5);
        Quiz q2= new Quiz("q2", -3, "Hard", 2);

        Mockito.doThrow(RepositoryException.class).when(repo).add(q2);

        service.addQuiz(q1);

        Mockito.verify(repo, times(1)).add(q1);
        //Mockito.verify(repo, times(1)).add(q2);
    }


    @Test
    public void test08_MaxScoreQuizCounter_validOutput() throws RepositoryException {

        Quiz q1= new Quiz("q1", 15, "Medium", 3);
        Quiz q2= new Quiz("q2", 10, "Hard", 0);
        Quiz q3= new Quiz("q3", 7, "Easy", 6);
        Mockito.when(repo.getAll()).thenReturn(Arrays.asList(q1, q2, q3));

        //assert examples
        assertEquals(1, service.maxScoreQuizCounter());
        assert 1 == service.maxScoreQuizCounter();
        Assertions.assertEquals(service.maxScoreQuizCounter()+"", 1+"");

        Mockito.verify(repo, times(3)).getAll();
    }

    @Test
    public void test09_MaxScoreQuizCounter_invalidOutput(){

        Quiz q1= new Quiz("q1", 15, "Medium", 3);
        Quiz q2= new Quiz("q2", 10, "Hard", 0);
        Quiz q3= new Quiz("q3", 7, "Easy", 6);
        Mockito.when(repo.getAll()).thenReturn(Arrays.asList(q1, q2, q3));

        //assert examples
        assertNotEquals(-1, service.maxScoreQuizCounter());
        assert -1 != service.maxScoreQuizCounter();
        assert (service.maxScoreQuizCounter()+"").equals(-1+"") == false;

        Mockito.verify(repo, times(3)).getAll();
    }

    @AfterEach
    public void tearDown(){
        service = null;
    }
}