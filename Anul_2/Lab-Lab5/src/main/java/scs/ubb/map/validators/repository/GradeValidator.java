package scs.ubb.map.validators.repository;

import scs.ubb.map.domain.Grade;
import scs.ubb.map.domain.Homework;
import scs.ubb.map.domain.Student;
import scs.ubb.map.repository.CrudRepository;
import scs.ubb.map.validators.ValidationException;
import scs.ubb.map.validators.Validator;

public class GradeValidator implements Validator<Grade> {
    private CrudRepository<Long, Student> studentCrudRepository;
    private CrudRepository<Integer, Homework> homeworkCrudRepository;

    public GradeValidator(CrudRepository<Long, Student> studentCrudRepository,
                          CrudRepository<Integer, Homework> homeworkCrudRepository) {
        this.homeworkCrudRepository = homeworkCrudRepository;
        this.studentCrudRepository = studentCrudRepository;
    }

    @Override
    public void validate(Grade entity) throws ValidationException {
        String errors = "";
        if (entity.getGrade() > 10 || entity.getGrade() < 1) {
            errors += "Grade should be between 1 and 10\n";
        }

        if(studentCrudRepository.findOne(entity.getStudentId()) == null) {
            errors += "Student could not be found\n";
        }

        if(homeworkCrudRepository.findOne(entity.getHomeworkId()) == null) {
            errors += "Homework could not be found\n";
        }

        if (!errors.equals("")) {
            throw new ValidationException(errors);
        }
    }
}
