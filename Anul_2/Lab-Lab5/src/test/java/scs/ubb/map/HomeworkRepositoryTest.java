package scs.ubb.map;

import com.google.common.collect.Iterables;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import scs.ubb.map.domain.Homework;
import scs.ubb.map.repository.HomeworkRepository;
import scs.ubb.map.validators.repository.HomeworkValidator;
import scs.ubb.map.validators.ValidationException;

@RunWith(JUnit4.class)
public class HomeworkRepositoryTest {
    private HomeworkRepository homeworkRepository;
    private Homework invalidHomework;
    private Homework validHomework;
    
    @Before
    public void before() {
        homeworkRepository = new HomeworkRepository(new HomeworkValidator());
        invalidHomework = new Homework("", 15, 15);
        validHomework = new Homework("1", 1, 4);
        invalidHomework.setId(0);
        validHomework.setId(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveHomeworkWithNullShouldThrowException() {
        homeworkRepository.save(null);
    }

    @Test(expected = ValidationException.class)
    public void saveInvalidHomeworkShouldThrowValidationException() {
        homeworkRepository.save(invalidHomework);
    }

    @Test
    public void saveAlreadySavedHomeworkShouldReturnOldValue() {
        homeworkRepository.save(validHomework);
        Homework returnedHomework = homeworkRepository.save(validHomework);

        Assert.assertEquals(validHomework, returnedHomework);
    }

    @Test
    public void saveNonExistingHomeworkShouldReturnNull() {
        Homework returnedHomework = homeworkRepository.save(validHomework);
        Assert.assertNull(returnedHomework);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findNullIdShouldReturnIllegalArgumentException() {
        homeworkRepository.findOne(null);
    }

    @Test
    public void findNonExistentIdShouldReturnNull() {
        Assert.assertNull(homeworkRepository.findOne(0));
    }

    @Test
    public void findExistentIdShouldReturnHomework() {
        homeworkRepository.save(validHomework);
        Assert.assertEquals(validHomework, homeworkRepository.findOne(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteHomeworkWithNullIdShouldThrowIllegalArgumentException() {
        homeworkRepository.delete(null);
    }

    @Test
    public void deleteNonExistentIdShouldReturnNull() {
        Assert.assertNull(homeworkRepository.delete(0));
    }

    @Test
    public void deleteHomeworkShouldReturnOldHomeworkAndRemoveIt() {
        homeworkRepository.save(validHomework);
        Assert.assertEquals(validHomework, homeworkRepository.delete(1));
        Assert.assertNull(homeworkRepository.findOne(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNullHomeworkShouldThrowIllegalArgumentException() {
        homeworkRepository.update(null);
    }

    @Test
    public void updateNonExistentHomeworkShouldReturnSameHomework() {
        Assert.assertEquals(validHomework, homeworkRepository.update(validHomework));
    }

    @Test
    public void updateExistingHomeworkShouldReturnNull() {
        homeworkRepository.save(validHomework);
        Assert.assertNull(homeworkRepository.update(validHomework));
    }

    @Test
    public void findAllShouldReturnAllHomework() {
        homeworkRepository.save(validHomework);
        Assert.assertEquals(1, Iterables.size(homeworkRepository.findAll()));
    }

}