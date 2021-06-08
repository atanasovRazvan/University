package scs.ubb.map.repository.files;

import scs.ubb.map.domain.Student;
import scs.ubb.map.validators.Validator;

public class StudentFileRepository extends InFileRepository<Long, Student> {
    public StudentFileRepository(Validator<Student> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    Student getEntity(String line) {
        String[] lines = line.split(";");
        if (lines.length == 5) {
            Student student = new Student(
                    lines[1],
                    lines[2],
                    lines[3],
                    Integer.parseInt(lines[4])
            );
            student.setId(Long.parseLong(lines[0]));
            return student;
        } else {
            //TODO Throw exception
        }

        return null;
    }

    @Override
    String getEntityString(Student entity) {
        return entity.getId() + ";"
                + entity.getFirstName() + ";"
                + entity.getLastName() + ";"
                + entity.getEmail() + ";"
                + entity.getGroup();
    }
}
