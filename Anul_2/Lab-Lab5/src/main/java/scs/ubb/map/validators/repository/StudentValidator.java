package scs.ubb.map.validators.repository;

import scs.ubb.map.domain.Student;
import scs.ubb.map.validators.ValidationException;
import scs.ubb.map.validators.Validator;

public class StudentValidator implements Validator<Student> {
    @Override
    public void validate(Student entity) throws ValidationException {
        String errors = "";
        if (entity.getLastName().equals(""))
            errors += "Studnet fara nume!\n";
        if (entity.getEmail().equals(""))
            errors += "Student cu mail incorect!\n";
        if (entity.getFirstName().equals(""))
            errors += "Student fara prenume!\n";
        if (entity.getGroup() == 0)
            errors += "Grupa invalida!\n";

        if (!errors.equals("")) {
            throw new ValidationException(errors);
        }
    }
}