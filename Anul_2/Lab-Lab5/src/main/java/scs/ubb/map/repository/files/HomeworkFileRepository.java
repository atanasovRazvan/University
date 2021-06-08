package scs.ubb.map.repository.files;

import scs.ubb.map.domain.Homework;
import scs.ubb.map.validators.Validator;

public class HomeworkFileRepository extends InFileRepository<Integer, Homework> {
    public HomeworkFileRepository(Validator<Homework> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    Homework getEntity(String line) {
        String[] lines = line.split(";");
        if (lines.length == 4) {
            Homework homework = new Homework(
                    lines[1],
                    Integer.parseInt(lines[2]),
                    Integer.parseInt(lines[3])
            );
            homework.setId(Integer.parseInt(lines[0]));
            return homework;
        } else {
            //TODO Throw exception
        }
        return null;
    }

    @Override
    String getEntityString(Homework entity) {
        return entity.getId() + ";"
                + entity.getDescription() + ";"
                + entity.getStartWeek() + ";"
                + entity.getDeadlineWeek();
    }
}
