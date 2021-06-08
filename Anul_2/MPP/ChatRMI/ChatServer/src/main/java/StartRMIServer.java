import chat.persistence.MessageRepository;
import chat.persistence.UserRepository;
import chat.persistence.repository.jdbc.MessageRepositoryJdbc;
import chat.persistence.repository.jdbc.UserRepositoryJdbc;
import chat.services.IChatServices;
import chat.server.ChatServerImpl;

import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

public class StartRMIServer {
    public static void main(String[] args) {

        Properties serverProps=new Properties();
        try {
            serverProps.load(StartRMIServer.class.getResourceAsStream("/chatserver.properties"));
            System.out.println("Server properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find chatserver.properties "+e);
            return;
        }
        // UserRepository repo=new UserRepositoryMock();
        UserRepository userRepo=new UserRepositoryJdbc(serverProps);
        MessageRepository messRepo=new MessageRepositoryJdbc(serverProps);
        IChatServices chatServerImpl=new ChatServerImpl(userRepo, messRepo);

        /*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }*/
        try {
            String name = serverProps.getProperty("chat.rmi.serverID","Chat");
            IChatServices stub =(IChatServices) UnicastRemoteObject.exportObject(chatServerImpl, 0);
            Registry registry = LocateRegistry.getRegistry();
            System.out.println("before binding");
            registry.rebind(name, stub);
            System.out.println("Chat server   bound");
        } catch (Exception e) {
            System.err.println("Chat server exception:"+e);
            e.printStackTrace();
        }

    }

}
