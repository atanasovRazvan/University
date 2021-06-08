package socialnetwork.repository.file;

import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.Validator;

import java.time.LocalDateTime;
import java.util.List;

public class FriendshipFile extends AbstractFileRepository<Long, Prietenie>{

    public FriendshipFile(String fileName, Validator<Prietenie> validator) {
        super(fileName, validator);
    }

    @Override
    public Prietenie extractEntity(List<String> attributes) {
        Prietenie friendship = new Prietenie(
                Long.parseLong(attributes.get(1)),
                Long.parseLong(attributes.get(2)),
                LocalDateTime.parse(attributes.get(3)));
        friendship.setId(Long.parseLong(attributes.get(0)));

        return friendship;
    }

    @Override
    protected String createEntityAsString(Prietenie entity) {
        return entity.getId()+";"+entity.getFirstUser()+";"+entity.getSecondUser()+";"+entity.getDate();
    }

}
