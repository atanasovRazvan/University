package socialnetwork.ui;

import socialnetwork.domain.FriendRequest;
import socialnetwork.domain.Message;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;
import socialnetwork.service.Service;
import socialnetwork.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Service service;

    public UserInterface(Service service){
        this.service = service;
    }

    public void run() throws Exception {

        boolean appContinues = true;

        while(appContinues){

            System.out.println("\n1.1. Add user");
            System.out.println("1.2. Remove user");
            System.out.println("2.1. Add friendship");
            System.out.println("2.2. Remove friendship");
            System.out.println("3. Communities number");
            System.out.println("4. Strongest community");
            System.out.println("5. Print all users");
            System.out.println("6. Print all friendships");
            System.out.println("7. Show friends");
            System.out.println("8. Show friends from a certain month");
            System.out.println("9. Friend requests");
            System.out.println("10. Messenger");
            System.out.println(Utils.ANSI_PURPLE + "11. EXIT" + Utils.ANSI_RESET);

            String cmd;
            Scanner scanner = new Scanner(System.in);
            cmd = scanner.nextLine();

            switch(cmd){
                case "1.1.": addUser(); break;
                case "1.2.": removeUser(); break;
                case "2.1.": addFriendship(); break;
                case "2.2.": removeFriendship(); break;
                case "3": communitiesNumber(); break;
                case "4": strongestCommunity(); break;
                case "5": printUsers(); break;
                case "6": printFriendships(); break;
                case "7": showFriends(); break;
                case "8": showFriendsFromMonth(); break;
                case "9": {
                    System.out.println("Who are you? Insert ID: ");
                    Long userId = Long.parseLong(scanner.nextLine());
                    friendRequests(userId);
                } break;
                case "10": {
                    System.out.println("Who are you? Insert ID: ");
                    Long userId = Long.parseLong(scanner.nextLine());
                    messenger(userId);
                } break;
                case "11": {
                    appContinues = false;
                    break;
                }
                default : {
                    System.out.println(Utils.ANSI_RED + "err: Invalid input\n" + Utils.ANSI_RESET);
                    break;
                }
            }

        }

    }

    private void messenger(Long userID) {

        boolean keepLooping = true;

        while(keepLooping) {

            System.out.println('\n');
            System.out.println("1. Send message");
            System.out.println("2. Reply to a message");
            System.out.println("3. Conversation");
            System.out.println(Utils.ANSI_PURPLE + "4. BACK" + Utils.ANSI_RESET);

            String cmd;
            Scanner scanner = new Scanner(System.in);
            cmd = scanner.nextLine();

            switch (cmd) {

                case "1": {
                    System.out.println("ID of receivers (separated with space): ");
                    String receiversID = scanner.nextLine();
                    System.out.println("Your message: ");
                    String msg = scanner.nextLine();
                    service.broadcastMessage(receiversID, msg, userID);
                } break;

                case "2": {
                    System.out.println("\nYour inbox:");
                    service.getAllMessages(userID).forEach(msg -> System.out.println(msg.toString()));

                    System.out.println("Reply to which? Insert ID:");
                    Long toReply = Long.parseLong(scanner.nextLine());
                    System.out.println("Your message: ");
                    String msg = scanner.nextLine();

                    service.replyToMessage(userID, toReply, msg);

                } break;

                case "3":{
                    System.out.println("\nInsert friend's ID: ");
                    Long friendID = Long.parseLong(scanner.nextLine());
                    System.out.println("Your conversation: \n");

                    service.showConversation(userID, friendID).forEach(msg -> {
                        System.out.println(msg.getValue());
                    });

                }

                case "4": {
                    keepLooping = false;
                } break;

                default: {
                    System.out.println(Utils.ANSI_RED + "err: Invalid input\n" + Utils.ANSI_RESET);
                    break;
                }

            }
        }

    }

    private void friendRequests(Long userID) {

        boolean keepLooping = true;

        while(keepLooping) {
            System.out.println("\n1. Send friend request");
            System.out.println("2. Accept / Refuse friend request");
            System.out.println(Utils.ANSI_PURPLE + "3. BACK" + Utils.ANSI_RESET);

            String cmd;
            Scanner scanner = new Scanner(System.in);
            cmd = scanner.nextLine();

            switch (cmd) {
                case "1": {
                    System.out.println("FriendRequest ID: ");
                    Long reqID = Long.parseLong(scanner.nextLine());
                    System.out.println("Insert " + Utils.ANSI_CYAN + " user ID to send request to:" + Utils.ANSI_RESET);
                    Long userIDtoSend = Long.parseLong(scanner.nextLine());
                    FriendRequest req = new FriendRequest(userID, userIDtoSend);
                    req.setId(reqID);
                    service.addFriendRequest(req);
                } break;
                case "2": {
                    service.getFriendRequests(userID).forEach(req -> {
                        System.out.println(req.getId() + ". From " + this.service.getUser(req.getSender()).getFirstName());
                    });
                    System.out.println("request ID: ");
                    Long reqId = Long.parseLong(scanner.nextLine());
                    System.out.println("A for accept, R for refuse");
                    String decision = scanner.nextLine();
                    service.handleFriendRequest(reqId, decision);

                } break;
                case "3": {
                    keepLooping = false;
                    break;
                }
                default: {
                    System.out.println(Utils.ANSI_RED + "err: Invalid input\n" + Utils.ANSI_RESET);
                    break;
                }
            }
        }

    }

    private void showFriendsFromMonth() throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert" + Utils.ANSI_CYAN + " user ID" + Utils.ANSI_RESET + ":");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Insert" + Utils.ANSI_CYAN + " month" + Utils.ANSI_RESET + ":");
        Integer month = Integer.parseInt(scanner.nextLine());
        if(1 > month || month > 12){
            throw new Exception("Month has to be in 1-12 range!");
        }
        System.out.println();
        System.out.println(id + "'s friends from month " + month + ":");
        service.getFriends(id, month).forEach(System.out::println);

    }

    private void showFriends() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert" + Utils.ANSI_CYAN + " user ID" + Utils.ANSI_RESET + ":");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println();
        System.out.println(id + "'s friends:");
        service.getFriends(id).forEach(System.out::println);

    }

    private void removeFriendship() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert" + Utils.ANSI_CYAN + " ID" + Utils.ANSI_RESET + ":");
        Long friendshipID = Long.parseLong(scanner.nextLine());

        System.out.println(Utils.ANSI_GREEN + "Removing " + Utils.ANSI_RESET + service.getFriendship(friendshipID));
        service.removeFriendship(friendshipID);

    }

    private void removeUser() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert" + Utils.ANSI_CYAN + " ID" + Utils.ANSI_RESET + ":");
        Long userID = Long.parseLong(scanner.nextLine());

        System.out.println(Utils.ANSI_GREEN + "Removing " + Utils.ANSI_RESET + service.getUser(userID));
        service.removeUser(userID);

    }

    private void printFriendships() {

        System.out.println("\n" + Utils.ANSI_YELLOW+"Printing friendships..." + Utils.ANSI_RESET);
        service.getAllFriendships().forEach(System.out::println);

    }

    private void printUsers() {
        System.out.println("\n" + Utils.ANSI_YELLOW + "Printing users..." + Utils.ANSI_RESET);
        service.getAllUsers().forEach(System.out::println);
    }

    private void strongestCommunity() {

        System.out.println("\nStrongest community: " + Utils.ANSI_BLUE + service.strongestCommunity() + Utils.ANSI_RESET);

    }

    private void communitiesNumber() {

        System.out.println("\nNumber of communities: " + Utils.ANSI_BLUE + service.noCommunities() + Utils.ANSI_RESET);

    }

    private void addFriendship() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert" + Utils.ANSI_CYAN + " friendship ID:" + Utils.ANSI_RESET);
        Long friendshipID = Long.parseLong(scanner.nextLine());

        System.out.println("Insert" + Utils.ANSI_CYAN + " first user ID:" + Utils.ANSI_RESET);
        Long firstUserID = Long.parseLong(scanner.nextLine());

        System.out.println("Insert" + Utils.ANSI_CYAN + " second user ID:" + Utils.ANSI_RESET);
        Long secondUserID = Long.parseLong(scanner.nextLine());

        Prietenie prietenie = new Prietenie(firstUserID, secondUserID, LocalDateTime.now());
        prietenie.setId(friendshipID);
        service.addFriendship(prietenie);

    }

    private void addUser() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert" + Utils.ANSI_CYAN + " ID:" + Utils.ANSI_RESET);
        Long userID = Long.parseLong(scanner.nextLine());

        System.out.println("Insert" + Utils.ANSI_CYAN + " last name:" + Utils.ANSI_RESET);
        String lastName = scanner.nextLine();

        System.out.println("Insert" + Utils.ANSI_CYAN + " first name:" + Utils.ANSI_RESET);
        String firstName = scanner.nextLine();

        Utilizator user = new Utilizator(firstName, lastName);
        user.setId(userID);
        service.addUser(user);

    }

}
