package chat.client.gui;

import chat.services.IChatObserver;
import chat.services.ChatException;
import chat.services.IChatServices;
import chat.model.Message;
import chat.model.User;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.Serializable;

public class ChatClientCtrl extends UnicastRemoteObject implements IChatObserver, Serializable {

    private  FriendsListModel friendsModel;
    private  MessagesListModel messagesModel;
    private IChatServices server;
    private User user;

    public ChatClientCtrl(IChatServices server) throws RemoteException{
        this.server = server;
        friendsModel=new FriendsListModel();
        messagesModel=new MessagesListModel();
    }

    public ListModel getFriendsModel(){
        return friendsModel;
    }

    public ListModel getMessagesModel(){
        return messagesModel;
    }

    public void messageReceived(Message message) throws ChatException {
        messagesModel.newMessage(message.getSender().getId(), message.getText());
    }

    public void friendLoggedIn(User friend) throws ChatException {
        friendsModel.friendLoggedIn(friend.getId());
    }

    public void friendLoggedOut(User friend) throws ChatException {
        friendsModel.friendLoggedOut(friend.getId());
    }

    public void logout() {
        try {
            server.logout(user, this);
        } catch (ChatException e) {
            System.err.println("Logout error "+e);
        }
    }

    public void login(String id, String pass) throws ChatException {
        User userL= new User(id,pass,"");

        server.login(userL,this);
        user=userL;
        User[] loggedInFriends=server.getLoggedFriends(user);
        System.out.println("Logged friends "+loggedInFriends.length);
        for(User us : loggedInFriends){
            friendsModel.friendLoggedIn(us.getId());
        }
    }

    public void sendMessage(int indexFriend, String txtMsg) throws ChatException{
        if (indexFriend<friendsModel.getSize()){
            messagesModel.newMessage(user.getId(), txtMsg);
            User sender=new User(user.getId());
            User receiver=new User((String)friendsModel.getElementAt(indexFriend));
            Message message=new Message(sender,txtMsg,receiver);
            server.sendMessage(message);
        }
        else
            throw new ChatException("There is no friend logged in. ");
    }
}
