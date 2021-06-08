package chat.server;

import chat.services.ChatException;
import chat.model.User;
import chat.model.Message;
import chat.persistence.repository.UserRepository;
import chat.services.IChatObserver;
import chat.services.IChatServices;

import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.*;

public class ChatServerImpl implements IChatServices{
    private UserRepository userRepository;
    private Map<String, IChatObserver> loggedClients;


    public ChatServerImpl(UserRepository repo) {
        userRepository= repo;
        loggedClients=new ConcurrentHashMap<>();;
    }

    public synchronized void login(User user, IChatObserver client) throws ChatException {
        boolean loginOk=userRepository.verifyUser(user);
        if (loginOk){
            if(loggedClients.get(user.getId())!=null)
                throw new ChatException("User already logged in.");
            loggedClients.put(user.getId(), client);
            notifyFriendsLoggedIn(user);
        }else
            throw new ChatException("Authentication failed.");
    }

    private boolean isLogged(User u){
        return loggedClients.get(u.getId())!=null;
    }
    private void notifyFriendsLoggedIn(User user) throws ChatException{
        User[] friends=userRepository.getFriends(user);
        System.out.println("Logged "+friends);

        ExecutorService executor= Executors.newFixedThreadPool(friends.length);
        for(User us :friends){
            IChatObserver chatClient=loggedClients.get(us.getId());
            if (chatClient!=null)
                executor.execute(() -> {
                    try {
                        System.out.println("Notifying [" + us.getId()+ "] friend ["+user.getId()+"] logged in.");
                        chatClient.friendLoggedIn(user);
                    } catch (ChatException|RemoteException e) {
                        System.out.println("Error notifying friend " + e);
                    }
                });
        }

        executor.shutdown();
    }

    private void notifyFriendsLoggedOut(User user) throws ChatException{
        User[] friends=userRepository.getFriends(user);
        ExecutorService executor= Executors.newFixedThreadPool(friends.length);

        for(User us :friends){
            IChatObserver chatClient=loggedClients.get(us.getId());
            if (chatClient!=null)
                executor.execute(() -> {
                    try {
                        System.out.println("Notifying ["+us.getId()+"] friend ["+user.getId()+"] logged out.");
                        chatClient.friendLoggedOut(user);
                    } catch (ChatException|RemoteException e) {
                        System.out.println("Error notifying friend " + e);
                    }
                });

        }
        executor.shutdown();
    }

    public synchronized void sendMessage(Message message) throws ChatException {
        String id_receiver=message.getReceiver().getId();
        IChatObserver receiverClient=loggedClients.get(id_receiver);
        if (receiverClient!=null)       //the receiver is logged in

            try {
                System.out.println("Notifying "+id_receiver+" new message");
                receiverClient.messageReceived(message);
            } catch (RemoteException e) {
                throw new ChatException("Error ",e);
            }
        else
            throw new ChatException("User "+id_receiver+" not logged in.");
    }

    public synchronized void logout(User user, IChatObserver client) throws ChatException {
        IChatObserver localClient=loggedClients.remove(user.getId());
        if (localClient==null)
            throw new ChatException("User "+user.getId()+" is not logged in.");
        notifyFriendsLoggedOut(user);
    }

    public synchronized User[] getLoggedFriends(User user) throws ChatException {
        User[] friends=userRepository.getFriends(user);
        Set<User> result=new TreeSet<User>();
        System.out.println("Logged friends for: "+user.getId());
        for (User friend : friends){
            if (loggedClients.containsKey(friend.getId())){    //the friend is logged in
                result.add(new User(friend.getId()));
                System.out.println("+"+friend.getId());
            }
        }
        System.out.println("Size "+result.size());
        return result.toArray(new User[result.size()]);
    }
}
