package socialnetwork.repository.file;

import socialnetwork.domain.Message;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.Validator;

import java.time.LocalDateTime;
import java.util.List;

public class MessageFile extends AbstractFileRepository<Long, Message> {

    public MessageFile(String fileName, Validator<Message> validator) {
        super(fileName, validator);
    }

    @Override
    public Message extractEntity(List<String> attributes) {
        Message message = new Message(
                attributes.get(3),
                Long.parseLong(attributes.get(1)),
                Long.parseLong(attributes.get(2)),
                LocalDateTime.parse(attributes.get(5))
        );
        message.setId(Long.parseLong(attributes.get(0)));
        message.setReply(Long.parseLong(attributes.get(4)));
        return message;
    }

    @Override
    protected String createEntityAsString(Message entity) {
        return entity.getId()+";"+entity.getSender()+";"+entity.getReceiver()+";"+entity.getMessage()+";"+entity.getReply()
                +";"+entity.getLdt();
    }
}
