package Repositories.file;

import Domain.Student;
import Domain.Validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Repository for student storage - file data persistence
 */
@Component
public class StudentFileRepository extends AbstractFileRepository<String, Student> {
    @Autowired
    public StudentFileRepository(Validator<Student> validator, @Value("${data.catalog.students}") String fileName) {
        super(validator, fileName, true);
    }

    /**
     * parses a student from a file string
     *
     * @param lineToParse - the student to be parsed - String
     * @return student - Student
     */
    @Override
    Student parseEntity(String lineToParse) {
        String[] args = lineToParse.split("/");
        return new Student(args[0], args[1], args[2], Integer.parseInt(args[3]), args[4], args[5]);
    }
}
