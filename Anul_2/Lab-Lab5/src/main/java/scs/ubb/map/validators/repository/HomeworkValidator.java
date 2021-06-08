package scs.ubb.map.validators.repository;

import scs.ubb.map.domain.Homework;
import scs.ubb.map.validators.ValidationException;
import scs.ubb.map.validators.Validator;

public class HomeworkValidator implements Validator<Homework> {

    @Override
    public void validate(Homework entity) throws ValidationException {
        if (entity.getId() <= 0)
            throw new ValidationException("Id incorect!");
        if (entity.getDescription() == "")
            throw new ValidationException("Descriere inexistenta!");
        if (entity.getStartWeek() < 1 || entity.getStartWeek() > 14)
            throw new ValidationException("Saptamana de inceput incorecta!");
        if (entity.getDeadlineWeek() < 1 || entity.getDeadlineWeek() > 14)
            throw new ValidationException("Saptamana deadline incorecta!");
        if (entity.getStartWeek() > entity.getDeadlineWeek())
            throw new ValidationException("Saptamana de inceput este dupa saptamana de deadline!");
    }
}
