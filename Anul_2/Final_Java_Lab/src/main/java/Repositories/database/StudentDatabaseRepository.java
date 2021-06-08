package Repositories.database;

import Domain.Student;
import Domain.Validators.StudentValidator;
import Exceptions.ValidationException;
import Repositories.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for student storage - database data persistence
 */
@Component
public class StudentDatabaseRepository implements CrudRepository<String, Student> {

    private StudentValidator studentValidator;
    private Connection connection;

    @Autowired
    public StudentDatabaseRepository(StudentValidator studentValidator, @Value("${data.db.connectionString}") String connectionString, @Value("${data.db.userName}") String userName, @Value("${data.db.password}") String password) {
        this.studentValidator = studentValidator;
        this.connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager
                    .getConnection(connectionString, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * finds a student in the repository
     *
     * @param studentID - id of the student - String
     * @return the found student - Student, or null if the student was not found
     */
    @Override
    public Student findOne(String studentID) {
        String studentId, firstName, lastName, email, coordinator;
        int group;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM students WHERE studentid = ?;")) {
            stmt.setString(1, studentID);
            ResultSet resultSet = stmt.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            studentId = resultSet.getString("studentId");
            firstName = resultSet.getString("firstName");
            lastName = resultSet.getString("lastName");
            group = resultSet.getInt("groupId");
            email = resultSet.getString("email");
            coordinator = resultSet.getString("coordinator");
            return new Student(studentId, firstName, lastName, group, email, coordinator);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * returns a list of all students in the repository
     *
     * @return allStudents - iterable of students
     */
    @Override
    public Iterable<Student> findAll() {
        List<Student> allStudents = new ArrayList<>();
        Student student;
        String studentId, firstName, lastName, email, coordinator;
        int group;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Students;")) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                studentId = resultSet.getString("studentId");
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                group = resultSet.getInt("groupId");
                email = resultSet.getString("email");
                coordinator = resultSet.getString("coordinator");
                student = new Student(studentId, firstName, lastName, group, email, coordinator);
                allStudents.add(student);
            }
            return allStudents;
        } catch (SQLException e) {
            e.printStackTrace();
            return allStudents;
        }
    }

    /**
     * saves a student in the repository
     *
     * @param student - the student to be saved - Student
     * @return null if the student was saved or foudStudent - Student if a student with the given id already exists
     * @throws ValidationException      if the student is not valid
     * @throws IllegalArgumentException if the student is null
     */
    @Override
    public Student save(Student student) throws ValidationException, IllegalArgumentException {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null!!");
        }
        studentValidator.validate(student);
        Student st = findOne(student.getId());
        if (st != null) {
            return st;
        }
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO Students(studentId, firstName, lastName, groupId, email, coordinator) VALUES(?,?,?,?,?,?);")) {
            stmt.setString(1, student.getId());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getLastName());
            stmt.setInt(4, student.getGroup());
            stmt.setString(5, student.getEmail());
            stmt.setString(6, student.getCoordinator());
            stmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * removes a student from the repository
     *
     * @param studentId - the id of the student to be removed - String
     * @return the removed student - Student, or null if the student with the given id does not exist
     */
    @Override
    public Student delete(String studentId) {
        Student student = findOne(studentId);
        if (student == null) {
            return null;
        }
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM students WHERE studentid = ?;")) {
            stmt.setString(1, studentId);
            stmt.executeUpdate();
            return student;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * updates a student in the repository
     *
     * @param student - the new version of the student - Student
     * @return the old version of the student - Student, or null if a student with the given id does not exist
     * @throws ValidationException      if the new version of the student is not valid
     * @throws IllegalArgumentException if the new version of the student is null
     */
    @Override
    public Student update(Student student) throws ValidationException, IllegalArgumentException {
        studentValidator.validate(student);
        Student st = findOne(student.getId());
        if (st == null) {
            return null;
        }
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE students SET firstname = ?, lastname = ?, groupid = ?, email = ?, coordinator = ? WHERE studentid = ?")) {
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setInt(3, student.getGroup());
            stmt.setString(4, student.getEmail());
            stmt.setString(5, student.getCoordinator());
            stmt.setString(6, student.getId());
            stmt.executeUpdate();
            return st;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
