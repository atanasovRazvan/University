package Domain.Validators;

import Domain.Assignment;
import Exceptions.InvalidAssignmentException;
import Exceptions.ValidationException;

/**
 * Class for validating assignment entities
 */
public class AssignmentValidator implements Validator<Assignment> {
    /**
     * validates a assignment
     *
     * @param assignment - Assignment to be validated
     * @throws ValidationException if assignment is not valid
     */
    @Override
    public void validate(Assignment assignment) throws ValidationException {
        String error = "";

        if (assignment.getId() < 1) {
            error += "Id must be greater than 0. ";
        }

        if (assignment.getDescription().isEmpty()) {
            error += "Description must not be empty. ";
        }

        if (assignment.getStartWeek() >= assignment.getDeadlineWeek()) {
            error += "Start week cannot precede or be the same as deadline week. ";
        }

        if (assignment.getDeadlineWeek() < 1 || assignment.getDeadlineWeek() > 14) {
            error += "Deadline week must take a value between 1 and 14. ";
        }

        if (!error.isEmpty()) {
            throw new InvalidAssignmentException(error);
        }
    }
}
