package Validators;

import Entities.Nota;

public class ValidatorNota implements Validator<Nota> {

    /**
     * the validator for a grade
     * @param n - an instance of class Nota
     * @throws ValidationException
     */
    public void validate(Nota n) throws ValidationException{

        if(n.get_grade()<1 || n.get_grade()>10 || (Integer)n.get_grade()== null)
            throw new ValidationException("Nota invalida. Trebuie sa fie intre 1 si 10");
        if(n.getData() == null)
            throw new ValidationException("Data invalida.");
        if((Integer)n.getStudentId() == null)
            throw new ValidationException("ID de student invalid");
        if((Integer)n.getTemaId() == null)
            throw new ValidationException("Numarul temei este invalid!");
        if(n.getFeedback()=="")
            throw new ValidationException("Numele profesorului incorect!");
        if((Integer)n.getSaptPredata()==null)
            throw new ValidationException("Saptamana predata este invalida");

    }

}
