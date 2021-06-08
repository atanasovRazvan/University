package Repositories.database;

import Domain.Assignment;
import Domain.Validators.AssignmentValidator;
import Exceptions.ValidationException;
import Repositories.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for assignment storage - database data persistence
 */
@Component
public class AssignmentDatabaseRepository implements CrudRepository<Integer, Assignment> {
    private AssignmentValidator assignmentValidator;
    private Connection connection;

    @Autowired
    public AssignmentDatabaseRepository(AssignmentValidator assignmentValidator, @Value("${data.db.connectionString}") String connectionString, @Value("${data.db.userName}") String userName, @Value("${data.db.password}") String password) {
        this.assignmentValidator = assignmentValidator;
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
     * finds an assignment in the repository
     *
     * @param assignmentId - id of the assignment - int
     * @return the found assignment - Assignment, or null if the assignment was not found
     */
    @Override
    public Assignment findOne(Integer assignmentId) {
        String assignmentDescription;
        int assgnId, deadlineWeek, startWeek;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM assignments WHERE assignmentid = ?;")) {
            stmt.setInt(1, assignmentId);
            ResultSet resultSet = stmt.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            assgnId = resultSet.getInt("assignmentId");
            startWeek = resultSet.getInt("startWeek");
            deadlineWeek = resultSet.getInt("deadlineWeek");
            assignmentDescription = resultSet.getString("description");
            Assignment res = new Assignment(assgnId, assignmentDescription, deadlineWeek);
            res.setStartWeek(startWeek);
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * returns a list of all assignments in the repository
     *
     * @return allAssignments - iterable of assignments
     */
    @Override
    public Iterable<Assignment> findAll() {
        List<Assignment> allAssignmnets = new ArrayList<>();
        Assignment assignment;
        String assignmentDescription;
        int assgnId, deadlineWeek, startWeek;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM assignments;")) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                assgnId = resultSet.getInt("assignmentId");
                startWeek = resultSet.getInt("startWeek");
                deadlineWeek = resultSet.getInt("deadlineWeek");
                assignmentDescription = resultSet.getString("description");
                assignment = new Assignment(assgnId, assignmentDescription, deadlineWeek);
                assignment.setStartWeek(startWeek);
                allAssignmnets.add(assignment);
            }
            return allAssignmnets;
        } catch (SQLException e) {
            e.printStackTrace();
            return allAssignmnets;
        }
    }

    /**
     * saves an assignment in the repository
     *
     * @param assignment - the assignment to be saved - Assignment
     * @return null if the assignment was saved or foundAssignment - Assignment if an assignment with the given id already exists
     * @throws ValidationException      if the assignment is not valid
     * @throws IllegalArgumentException if the assignment is null
     */
    @Override
    public Assignment save(Assignment assignment) throws ValidationException, IllegalArgumentException {
        if (assignment == null) {
            throw new IllegalArgumentException("Assignment cannot be null.");
        }
        assignmentValidator.validate(assignment);
        Assignment assgn = findOne(assignment.getId());
        if (assgn != null) {
            return assgn;
        }
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO assignments(assignmentid, description, startweek, deadlineweek) VALUES(?,?,?,?);")) {
            stmt.setInt(1, assignment.getId());
            stmt.setString(2, assignment.getDescription());
            stmt.setInt(3, assignment.getStartWeek());
            stmt.setInt(4, assignment.getDeadlineWeek());
            stmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * removes an assignment from the repository
     *
     * @param assignmentId - the id of the assignment to be removed - int
     * @return the removed assignment - Assignment, or null if the assignment with the given id does not exist
     */
    @Override
    public Assignment delete(Integer assignmentId) {
        Assignment assignment = findOne(assignmentId);
        if (assignment == null) {
            return null;
        }
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM assignments WHERE assignmentid = ?;")) {
            stmt.setInt(1, assignmentId);
            stmt.executeUpdate();
            return assignment;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * updates an assignment in the repository
     *
     * @param assignment - the new version of the assignment - Assignment
     * @return the old version of the assignment - Assignment, or null if an assignment with the given id does not exist
     * @throws ValidationException      if the new version of the assignment is not valid
     * @throws IllegalArgumentException if the new version of the assignment is null
     */
    @Override
    public Assignment update(Assignment assignment) throws ValidationException, IllegalArgumentException {
        if (assignment == null) {
            throw new IllegalArgumentException("Assignment cannot be null.");
        }
        assignmentValidator.validate(assignment);
        Assignment assgn = findOne(assignment.getId());
        if (assgn == null) {
            return null;
        }
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE assignments SET description = ?, startweek = ?, deadlineweek = ? WHERE assignmentid = ?")) {
            stmt.setString(1, assignment.getDescription());
            stmt.setInt(2, assignment.getStartWeek());
            stmt.setInt(3, assignment.getDeadlineWeek());
            stmt.setInt(4, assignment.getId());
            stmt.executeUpdate();
            return assgn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
