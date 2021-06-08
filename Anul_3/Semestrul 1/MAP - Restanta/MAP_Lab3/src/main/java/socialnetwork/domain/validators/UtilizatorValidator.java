package socialnetwork.domain.validators;

import socialnetwork.domain.Utilizator;

public class UtilizatorValidator implements Validator<Utilizator> {

    @Override
    public void validate(Utilizator entity) throws ValidationException {
        if(entity == null)
            throw new ValidationException("Friendship can't be null!");
        if(!entity.getFirstName().matches("[a-zA-Z]*"))
            throw new ValidationException("First name should contain only letters");
        if(!entity.getLastName().matches("[a-zA-Z]*"))
            throw new ValidationException("Last name should contain only letters");
    }
}
