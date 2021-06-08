package chat.services;

import chat.model.User;
import chat.model.Message;



public interface IChatServices{
     void login(User user, IChatObserver client) throws ChatException;
     void sendMessage(Message message) throws ChatException;
     void logout(User user, IChatObserver client) throws ChatException;
     User[] getLoggedFriends(User user) throws ChatException;
}
