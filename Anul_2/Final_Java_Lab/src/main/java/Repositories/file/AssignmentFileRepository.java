package Repositories.file;

import Domain.Assignment;
import Domain.Validators.AssignmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Repository for assignment storage - file data persistence
 */
@Component
public class AssignmentFileRepository extends AbstractFileRepository<Integer, Assignment> {
    @Autowired
    public AssignmentFileRepository(AssignmentValidator validator, @Value("${data.catalog.assignments}") String fileName) {
        super(validator, fileName, true);
    }

    /**
     * parses an assignment from a file string
     *
     * @param lineToParse - the assignment to be parsed - String
     * @return assignment - Assignment
     */
    @Override
    Assignment parseEntity(String lineToParse) {
        String[] args = lineToParse.split("/");
        Assignment assignment = new Assignment(Integer.parseInt(args[0]), args[1], Integer.parseInt(args[3]));
        assignment.setStartWeek(Integer.parseInt(args[2]));
        return assignment;
    }
}
