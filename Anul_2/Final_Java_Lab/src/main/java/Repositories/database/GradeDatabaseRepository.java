package Repositories.database;

import Domain.Assignment;
import Domain.Grade;
import Domain.Student;
import Domain.Validators.GradeValidator;
import Exceptions.ValidationException;
import Repositories.CrudRepository;
import Repositories.GradeRepository;
import Utils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for grade storage - database data persistence
 */
@Component
public class GradeDatabaseRepository implements CrudRepository<Pair<String, Integer>, Grade>, GradeRepository {

    private GradeValidator gradeValidator;
    private Connection connection;
    private CrudRepository<String, Student> studentRepo;
    private CrudRepository<Integer, Assignment> assignmentRepo;

    @Autowired
    public GradeDatabaseRepository(GradeValidator gradeValidator, @Value("${data.db.connectionString}") String connectionString, @Value("${data.db.userName}") String userName, @Value("${data.db.password}") String password, @Qualifier("studentDatabaseRepository") CrudRepository<String, Student> studentRepo, @Qualifier("assignmentDatabaseRepository") CrudRepository<Integer, Assignment> assignmentRepo) {
        this.studentRepo = studentRepo;
        this.assignmentRepo = assignmentRepo;
        this.gradeValidator = gradeValidator;
        this.connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection(connectionString, userName, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * finds a grade in the repository
     *
     * @param gradeId - id of the grade - Pair of String and int
     * @return the found grade - Grade, or null if the grade was not found
     */
    @Override
    public Grade findOne(Pair<String, Integer> gradeId) {
        String studentId, professor;
        float value;
        LocalDate date;
        int assignmentId;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM grades WHERE studentid = ? AND assignmentid = ?;")) {
            stmt.setString(1, gradeId.getFirst());
            stmt.setInt(2, gradeId.getSecond());
            ResultSet resultSet = stmt.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            studentId = resultSet.getString("studentId");
            Student student = studentRepo.findOne(studentId);
            assignmentId = resultSet.getInt("assignmentId");
            Assignment assignment = assignmentRepo.findOne(assignmentId);
            date = resultSet.getDate("date").toLocalDate();
            value = resultSet.getFloat("value");
            professor = resultSet.getString("professor");
            Grade res = new Grade(student, assignment, value, professor);
            res.setDate(date);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * returns a list of all grades in the repository
     *
     * @return allGrades - iterable of grades
     */
    @Override
    public Iterable<Grade> findAll() {
        List<Grade> allGrades = new ArrayList<>();
        String studentId, professor;
        float value;
        LocalDate date;
        int assignmentId;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM grades;")) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                studentId = resultSet.getString("studentId");
                Student student = studentRepo.findOne(studentId);
                assignmentId = resultSet.getInt("assignmentId");
                Assignment assignment = assignmentRepo.findOne(assignmentId);
                date = resultSet.getDate("date").toLocalDate();
                value = resultSet.getFloat("value");
                professor = resultSet.getString("professor");
                Grade grade = new Grade(student, assignment, value, professor);
                grade.setDate(date);
                allGrades.add(grade);
            }
            return allGrades;
        } catch (SQLException e) {
            e.printStackTrace();
            return allGrades;
        }
    }

    /**
     * saves a grade in the repository
     *
     * @param grade - the grade to be saved - Grade
     * @return null if the grade was saved or foundGrade - Grade if a grade with the given id already exists
     * @throws ValidationException      if the grade is not valid
     * @throws IllegalArgumentException if the grade is null
     */
    @Override
    public Grade save(Grade grade) throws ValidationException, IllegalArgumentException {
        if (grade == null) {
            throw new IllegalArgumentException("Grade cannot be null.");
        }
        gradeValidator.validate(grade);
        Grade gr = findOne(grade.getId());
        if (gr != null) {
            return gr;
        }
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO grades(studentid, assignmentid, date, value, professor) VALUES(?,?,?,?,?);")) {
            stmt.setString(1, grade.getId().getFirst());
            stmt.setInt(2, grade.getId().getSecond());
            stmt.setDate(3, Date.valueOf(grade.getDate()));
            stmt.setFloat(4, grade.getValue());
            stmt.setString(5, grade.getProfessor());
            stmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * removes a grade from the repository
     *
     * @param gradeId - the id of the grade to be removed - Pair of String and int
     * @return the removed grade - Grade, or null if the grade with the given id does not exist
     */
    @Override
    public Grade delete(Pair<String, Integer> gradeId) {
        Grade grade = findOne(gradeId);
        if (grade == null) {
            return null;
        }
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM grades WHERE studentid = ? AND assignmentid = ?;")) {
            stmt.setString(1, gradeId.getFirst());
            stmt.setInt(2, gradeId.getSecond());
            stmt.executeUpdate();
            return grade;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * updates a grade in the repository
     *
     * @param grade - the new version of the grade - Grade
     * @return the old version of the grade - Grade, or null if a grade with the given id does not exist
     * @throws ValidationException      if the new version of the grade is not valid
     * @throws IllegalArgumentException if the new version of the grade is null
     */
    @Override
    public Grade update(Grade grade) throws ValidationException, IllegalArgumentException {
        if (grade == null) {
            throw new IllegalArgumentException("Grade cannot be null.");
        }
        gradeValidator.validate(grade);
        Grade gr = findOne(grade.getId());
        if (gr == null) {
            return null;
        }
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE grades SET date = ?, value = ?, professor = ? WHERE studentid = ? AND assignmentid = ?")) {
            stmt.setDate(1, Date.valueOf(grade.getDate()));
            stmt.setFloat(2, grade.getValue());
            stmt.setString(3, grade.getProfessor());
            stmt.setString(4, grade.getId().getFirst());
            stmt.setInt(5, grade.getId().getSecond());
            stmt.executeUpdate();
            return gr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * returns the student repository
     *
     * @return studentRepo - Student Crud Repository
     */
    @Override
    public CrudRepository<String, Student> getStudentRepo() {
        return studentRepo;
    }

    /**
     * sets the student repository
     *
     * @param studentRepo - Student Crud Repository
     */
    public void setStudentRepo(CrudRepository<String, Student> studentRepo) {
        this.studentRepo = studentRepo;
    }

    /**
     * returns the assignment repository
     *
     * @return assignmentRepo - Assignment Crud Repository
     */
    @Override
    public CrudRepository<Integer, Assignment> getAssignmentRepo() {
        return assignmentRepo;
    }

    /**
     * sets the assignment repository
     *
     * @param assignmentRepo - Assignment Crud Repository
     */
    public void setAssignmentRepo(CrudRepository<Integer, Assignment> assignmentRepo) {
        this.assignmentRepo = assignmentRepo;
    }
}
