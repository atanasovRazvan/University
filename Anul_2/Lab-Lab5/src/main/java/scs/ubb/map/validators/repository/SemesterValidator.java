package scs.ubb.map.validators.repository;

import scs.ubb.map.utils.SemesterStructure;
import scs.ubb.map.validators.ValidationException;
import scs.ubb.map.validators.Validator;

public class SemesterValidator implements Validator<SemesterStructure> {
    @Override
    public void validate(SemesterStructure semesterStructure) throws ValidationException {
        String error = "";

        if (semesterStructure.getStartDate().compareTo(semesterStructure.getEndDate()) > 0) {
            error += "Start date should be lower than end date";
        }

        //TODO Validate holiday dates
        if (!error.equals("")) {
            throw new ValidationException(error);
        }
    }
}
