package chat.client;

import chat.services.IChatServices;
import chat.client.gui.ChatClientCtrl;
import chat.client.gui.LoginWindow;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StartClient {
    public static void main(String[] args) {

        try {
            /*String name = "Chat";
            Registry registry = LocateRegistry.getRegistry("localhost");
            IChatServices server = (IChatServices) registry.lookup(name);*/

            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
            IChatServices server=(IChatServices)factory.getBean("chatService");
            System.out.println("Obtained a reference to remote chat server");
            ChatClientCtrl ctrl=new ChatClientCtrl(server);


            LoginWindow logWin=new LoginWindow("Chat XYZ", ctrl);
            logWin.setSize(200,200);
            logWin.setLocation(150,150);
            logWin.setVisible(true);

        } catch (Exception e) {
            System.err.println("Chat Initialization  exception:"+e);
            e.printStackTrace();
        }

    }
}
