package Validators;

import Entities.Tema;

public class ValidatorTema implements Validator<Tema> {

    /**
     * the validator for a homework
     * @param t - an instance of class Tema
     * @throws ValidationException
     */
    public void validate(Tema t) throws ValidationException{
        if(t.getID() == null)
            throw new ValidationException("ID invalid.\n");
        if(t.getDeadline() < 1 || t.getDeadline() > 14)
            throw new ValidationException("Deadline invalid.\n");
        if(t.getSaptPredare() < 1 || t.getSaptPredare() > 14)
            throw new ValidationException("Saptamana predare invalida.\n");
        if(t.getSaptPredare() >= t.getDeadline())
            throw new ValidationException("Nu se poate seta deadline-ul inainte de a preda tema.");
        if(t.getDescriere().isEmpty())
            throw new ValidationException("Descriere invalida.\n");
    }
}
