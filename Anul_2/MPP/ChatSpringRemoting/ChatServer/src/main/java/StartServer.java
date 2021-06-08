
import chat.persistence.repository.UserRepository;
import chat.persistence.repository.mock.UserRepositoryMock;
import chat.services.IChatServices;
import chat.server.ChatServerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class StartServer {
    public static void main(String[] args) {
        /*UserRepository repo=new UserRepositoryMock();
        IChatServices chatServerImpl=new ChatServerImpl(repo);

        try {
            String name = "Chat";
            IChatServices stub =(IChatServices) UnicastRemoteObject.exportObject(chatServerImpl, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("Chat server   bound");
        } catch (Exception e) {
            System.err.println("Chat server exception:"+e);
            e.printStackTrace();
        }*/

        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");


    }

}
