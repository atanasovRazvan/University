package chat.services;

import chat.model.Message;
import chat.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatObserver extends Remote {
     void messageReceived(Message message) throws ChatException, RemoteException;
     void friendLoggedIn(User friend) throws ChatException,  RemoteException;
     void friendLoggedOut(User friend) throws ChatException,  RemoteException;
    
}
