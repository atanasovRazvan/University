package chat.services;

import chat.model.User;
import chat.model.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatServices extends Remote {
     void login(User user, IChatObserver client) throws ChatException, RemoteException;
     void sendMessage(Message message) throws ChatException,  RemoteException;
     void logout(User user, IChatObserver client) throws ChatException,  RemoteException;
     User[] getLoggedFriends(User user) throws ChatException,  RemoteException;
}
