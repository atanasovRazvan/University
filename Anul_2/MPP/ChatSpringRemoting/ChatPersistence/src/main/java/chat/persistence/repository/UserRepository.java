package chat.persistence.repository;

import chat.model.User;

public interface UserRepository {
     boolean verifyUser(User user);
     User[] getFriends(User user);
   
}
