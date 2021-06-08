package socialnetwork.repository.file;

import socialnetwork.domain.FriendRequest;
import socialnetwork.domain.validators.Validator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

public class FriendRequestFile extends AbstractFileRepository<Long, FriendRequest> {

    public FriendRequestFile(String fileName, Validator<FriendRequest> validator) {
        super(fileName, validator);
    }

    @Override
    public FriendRequest extractEntity(List<String> attributes) {
        FriendRequest friendship = new FriendRequest(
                Long.parseLong(attributes.get(1)),
                Long.parseLong(attributes.get(2)));
        friendship.setId(Long.parseLong(attributes.get(0)));
        friendship.setStatus(attributes.get(4));
        friendship.setLdt(LocalDateTime.parse(attributes.get(3)));
        return friendship;
    }

    @Override
    protected String createEntityAsString(FriendRequest entity) {
        return entity.getId()+";"+entity.getSender()+";"+entity.getReceiver()+";"+entity.getLdt()+";"+entity.getStatus();
    }

    @Override
    public FriendRequest delete(Long id){

        FriendRequest e = super.findOne(id);
        e.setStatus("REFUSED");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fileName);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        assert pw != null;
        pw.close();
        this.findAll().forEach(this::writeToFile);
        return e;

    }

}
