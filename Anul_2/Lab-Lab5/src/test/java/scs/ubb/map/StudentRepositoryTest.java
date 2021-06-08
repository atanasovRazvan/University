package scs.ubb.map;


import com.google.common.collect.Iterables;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import scs.ubb.map.domain.Student;
import scs.ubb.map.repository.StudentRepository;
import scs.ubb.map.validators.repository.StudentValidator;
import scs.ubb.map.validators.ValidationException;

@RunWith(JUnit4.class)
public class StudentRepositoryTest {
    private StudentRepository studentRepository;
    private Student invalidStudent;
    private Student validStudent;

    @Before
    public void before() {
        studentRepository = new StudentRepository(new StudentValidator());
        invalidStudent = new Student("", "", "", 0);
        validStudent = new Student("Ion", "Vasile", "a.a", 1);
        invalidStudent.setId(0L);
        validStudent.setId(1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveStudentWithNullShouldThrowException() {
        studentRepository.save(null);
    }

    @Test(expected = ValidationException.class)
    public void saveInvalidStudentShouldThrowValidationException() {
        studentRepository.save(invalidStudent);
    }

    @Test
    public void saveAlreadySavedStudentShouldReturnOldValue() {
        studentRepository.save(validStudent);
        Student returnedStudent = studentRepository.save(validStudent);

        Assert.assertEquals(validStudent, returnedStudent);
    }

    @Test
    public void saveNonExistingStudentShouldReturnNull() {
        Student returnedStudent = studentRepository.save(validStudent);
        Assert.assertNull(returnedStudent);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findNullIdShouldReturnIllegalArgumentException() {
        studentRepository.findOne(null);
    }

    @Test
    public void findNonExistentIdShouldReturnNull() {
        Assert.assertNull(studentRepository.findOne(0L));
    }

    @Test
    public void findExistentIdShouldReturnStudent() {
        studentRepository.save(validStudent);
        Assert.assertEquals(validStudent, studentRepository.findOne(1L));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteStudentWithNullIdShouldThrowIllegalArgumentException() {
        studentRepository.delete(null);
    }

    @Test
    public void deleteNonExistentIdShouldReturnNull() {
        Assert.assertNull(studentRepository.delete(0L));
    }

    @Test
    public void deleteStudentShouldReturnOldStudentAndRemoveIt() {
        studentRepository.save(validStudent);
        Assert.assertEquals(validStudent, studentRepository.delete(1L));
        Assert.assertNull(studentRepository.findOne(1L));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNullStudentShouldThrowIllegalArgumentException() {
        studentRepository.update(null);
    }

    @Test
    public void updateNonExistentStudentShouldReturnSameStudent() {
        Assert.assertEquals(validStudent, studentRepository.update(validStudent));
    }

    @Test
    public void updateExistingStudentShouldReturnNull() {
        studentRepository.save(validStudent);
        Assert.assertNull(studentRepository.update(validStudent));
    }

    @Test
    public void findAllShouldReturnAllStudents() {
        studentRepository.save(validStudent);
        Assert.assertEquals(1, Iterables.size(studentRepository.findAll()));
    }
}
