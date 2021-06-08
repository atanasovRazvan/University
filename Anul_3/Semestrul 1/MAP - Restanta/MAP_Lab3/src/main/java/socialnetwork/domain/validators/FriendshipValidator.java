package socialnetwork.domain.validators;

import socialnetwork.domain.Prietenie;

public class FriendshipValidator implements Validator<Prietenie> {

    @Override
    public void validate(Prietenie entity) throws ValidationException {
        if(entity == null)
            throw new ValidationException("User can't be null!");
        if(!entity.getFirstUser().toString().matches("[0-9]*"))
            throw new ValidationException("First user ID must contain only digits");
        if(!entity.getSecondUser().toString().matches("[0-9]*"))
            throw new ValidationException("Second user ID must contain only digits");
    }

}
