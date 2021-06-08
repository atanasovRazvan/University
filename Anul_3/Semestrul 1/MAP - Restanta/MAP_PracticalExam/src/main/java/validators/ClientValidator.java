package validators;

import model.Client;

import javax.xml.bind.ValidationException;

public class ClientValidator implements Validator<Client>{
    @Override
    public void validate(Client entity) throws ValidationException {
        if(entity.getId().equals(""))
            throw new ValidationException("Username can't be null");
        if(entity.getName().equals(""))
            throw new ValidationException("Name can't be null");
    }
}
