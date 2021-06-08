package chat.client;

import chat.services.IChatServices;
import chat.client.gui.ChatClientCtrl;
import chat.client.gui.LoginWindow;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;


public class StartRMIClient {
    private static String defaultServer="localhost";
    public static void main(String[] args) {

        Properties clientProps=new Properties();
        try {
            clientProps.load(StartRMIClient.class.getResourceAsStream("/chatclient.properties"));
            System.out.println("Client properties set. ");
            clientProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find chatclient.properties "+e);
            return;
        }

       /* if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }*/
        String name=clientProps.getProperty("chat.rmi.serverID","Chat");
        String serverIP=clientProps.getProperty("chat.server.host",defaultServer);
        try {

            Registry registry = LocateRegistry.getRegistry(serverIP);
            IChatServices server = (IChatServices) registry.lookup(name);
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
