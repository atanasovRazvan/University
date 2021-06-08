package socialnetwork.service;

import javafx.util.Pair;
import socialnetwork.domain.*;
import socialnetwork.repository.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Service {
    private Repository<Long, Utilizator> userRepository;
    private Repository<Long, Prietenie> friendshipRepository;
    private Repository<Long, FriendRequest> friendRequestRepository;
    private Repository<Long, Message> messageRepository;

    public Service(Repository<Long, Utilizator> userRepository, Repository<Long, Prietenie> friendshipRepository,
                   Repository<Long, FriendRequest> friendRequestRepository,
                   Repository<Long, Message> messageRepository) {

        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
        this.friendRequestRepository = friendRequestRepository;
        this.messageRepository = messageRepository;

    }

    public Utilizator addUser(Utilizator user) {
        return this.userRepository.save(user);
    }

    public Iterable<Utilizator> getAllUsers(){
        return this.userRepository.findAll();
    }

    public Iterable<Prietenie> getAllFriendships(){
        return this.friendshipRepository.findAll();
    }

    public Utilizator removeUser(Long userID){

        Utilizator toReturn = userRepository.delete(userID);
        List<Prietenie> allFriendships = new ArrayList<>();
        this.getAllFriendships().forEach(allFriendships::add);
        allFriendships.forEach(fr -> {
            if(fr.getFirstUser().equals(userID) || fr.getSecondUser().equals(userID))
                friendshipRepository.delete(fr.getId());
        });
        return toReturn;

    }

    public Utilizator getUser(Long userID){
        return userRepository.findOne(userID);
    }

    public Prietenie addFriendship(Prietenie prietenie) {
        return this.friendshipRepository.save(prietenie);
    }

    public Prietenie getFriendship(Long friendshipID) {
        return friendshipRepository.findOne(friendshipID);
    }

    public Prietenie removeFriendship(Utilizator user, Utilizator friend) {

        for(Prietenie p : this.getAllFriendships()){
            if((p.getFirstUser().equals(user.getId()) && p.getSecondUser().equals(friend.getId())) ||
                    (p.getFirstUser().equals(friend.getId()) && p.getSecondUser().equals(user.getId()))) {
                return this.friendshipRepository.delete(p.getId());
            }
        }
        return null;

    }

    public Prietenie removeFriendship(Long id){
        return this.friendshipRepository.delete(id);
    }

    public Integer noCommunities(){

        int noCommunities = 0;

        List<Utilizator> allUsers = new ArrayList<>();
        List<Utilizator> visitedUsers = new ArrayList<>();
        userRepository.findAll().forEach(allUsers::add);

        for(Utilizator user : allUsers){
            if(!visitedUsers.contains(user)){

                noCommunities++;
                List<Utilizator> currentUsers = new ArrayList<>();
                currentUsers.add(user);
                for(int i = 0; i < currentUsers.size(); i ++){

                    int finalI = i;
                    friendshipRepository.findAll().forEach(fr -> {
                        Utilizator firstUser = userRepository.findOne(fr.getFirstUser());
                        Utilizator secondUser = userRepository.findOne(fr.getSecondUser());
                        if(currentUsers.get(finalI) == firstUser || currentUsers.get(finalI) == secondUser) {
                            if (!visitedUsers.contains(firstUser)) visitedUsers.add(firstUser);
                            if (!visitedUsers.contains(secondUser)) visitedUsers.add(secondUser);
                            if (!currentUsers.contains(firstUser)) currentUsers.add(firstUser);
                            if (!currentUsers.contains(secondUser)) currentUsers.add(secondUser);
                        }
                    });

                }

            }
        }

        return noCommunities;

    }

    public List<Utilizator> strongestCommunity(){

        int noCommunities = 0;
        int strongestCommunity = 0;
        List<Utilizator> strongest = new ArrayList<>();

        List<Utilizator> allUsers = new ArrayList<>();
        List<Utilizator> visitedUsers = new ArrayList<>();
        userRepository.findAll().forEach(allUsers::add);

        for(Utilizator user : allUsers){
            if(!visitedUsers.contains(user)){

                noCommunities++;
                List<Utilizator> currentUsers = new ArrayList<>();
                currentUsers.add(user);
                for(int i = 0; i < currentUsers.size(); i ++){

                    int finalI = i;
                    friendshipRepository.findAll().forEach(fr -> {
                        Utilizator firstUser = userRepository.findOne(fr.getFirstUser());
                        Utilizator secondUser = userRepository.findOne(fr.getSecondUser());
                        if(currentUsers.get(finalI) == firstUser || currentUsers.get(finalI) == secondUser) {
                            if (!visitedUsers.contains(firstUser)) visitedUsers.add(firstUser);
                            if (!visitedUsers.contains(secondUser)) visitedUsers.add(secondUser);
                            if (!currentUsers.contains(firstUser)) currentUsers.add(firstUser);
                            if (!currentUsers.contains(secondUser)) currentUsers.add(secondUser);
                        }
                    });

                }
                if(currentUsers.size() > strongestCommunity){
                    strongest.clear();
                    strongestCommunity = currentUsers.size();
                    strongest.addAll(currentUsers);
                }

            }
        }

        return strongest;

    }

    public List<Friend> getFriends(Long id){

        List<Prietenie> friendships = new ArrayList<>();
        friendshipRepository.findAll().forEach(friendships::add);

        return friendships.stream()
                .filter(fr -> fr.getSecondUser().equals(id) || fr.getFirstUser().equals(id))
                .map(fr -> {
                    if(fr.getFirstUser().equals(id)){
                        Utilizator user = userRepository.findOne(fr.getSecondUser());
                        return new Friend(user.getFirstName(), user.getLastName(), fr.getDate());
                    }
                    else{
                        Utilizator user = userRepository.findOne(fr.getFirstUser());
                        return new Friend(user.getFirstName(), user.getLastName(), fr.getDate());
                    }
                })
                .collect(Collectors.toList());

    }

    public List<Friend> getFriends(Long id, Integer month){

        return this.getFriends(id).stream()
                .filter(fr -> fr.getDateTime().getMonthValue() == month)
                .collect(Collectors.toList());
    }

    public FriendRequest addFriendRequest(FriendRequest req) {
        req.setStatus("PENDING");
        AtomicLong requestId = new AtomicLong(1L);
        friendRequestRepository.findAll().forEach(request -> requestId.getAndIncrement());
        req.setId(requestId.get());
        return this.friendRequestRepository.save(req);
    }

    public List<FriendRequest> getFriendRequests(Long userID){

        List<FriendRequest> requests = new ArrayList<>();
        this.friendRequestRepository.findAll().forEach(req -> {
            if(req.getReceiver().equals(userID) && req.getStatus().equals("PENDING")) requests.add(req);
        });
        return requests;

    }

    public List<FriendRequest> getAllFriendRequests(Long userID){

        List<FriendRequest> requests = new ArrayList<>();
        this.friendRequestRepository.findAll().forEach(req -> {
            if(req.getReceiver().equals(userID)) requests.add(req);
        });
        return requests;

    }

    public void handleFriendRequest(Long reqId, String decision) {

        FriendRequest fr = friendRequestRepository.findOne(reqId);
        if(decision.equals("A")) {
            Prietenie prietenie = new Prietenie(fr.getSender(), fr.getReceiver(), LocalDateTime.now());

            AtomicLong friendshipId = new AtomicLong(1L);
            friendshipRepository.findAll().forEach(friendship -> friendshipId.getAndIncrement());
            prietenie.setId(friendshipId.get());

            System.out.println(prietenie.getId());
            friendshipRepository.save(prietenie);
            fr.setStatus("ACCEPTED");
            fr.setLdt(LocalDateTime.now());
            friendRequestRepository.update(fr);
        }
        else
            friendRequestRepository.delete(reqId);

    }

    public Message saveMessage(Message message) {
        AtomicLong msgId = new AtomicLong(1L);
        messageRepository.findAll().forEach(msg -> msgId.getAndIncrement());
        message.setId(msgId.get());
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages(Long userID) {
        List<Message> messages = new ArrayList<>();

        messageRepository.findAll().forEach(msg -> {
            if(msg.getReceiver().equals(userID))
                messages.add(msg);
        });

        return messages;
    }

    public void replyToMessage(Long userID, Long toReply, String msg) {

        Message originalMessage = messageRepository.findOne(toReply);
        Message newMessage = new Message(msg, userID, originalMessage.getSender(), LocalDateTime.now());

        AtomicLong msgId = new AtomicLong(0L);
        messageRepository.findAll().forEach(msgg -> msgId.getAndIncrement());
        newMessage.setReply(msgId.get());

        this.saveMessage(newMessage);

    }

    public void broadcastMessage(String receiversID, String msg, Long sender) {

        List<Long> receiverIDs = new ArrayList<>();
        for(String string : receiversID.split(" "))
            receiverIDs.add(Long.parseLong(string));

        for(Long receiverID : receiverIDs) {
            Message message = new Message(msg, sender, receiverID, LocalDateTime.now());
            this.saveMessage(message);
        }

    }

    public List<Pair<LocalDateTime, String>> showConversation(Long userID, Long friendID) {

        List<Pair<LocalDateTime, String>> messages = new ArrayList<>();

        messageRepository.findAll().forEach(msg -> {

            if((msg.getSender().equals(userID) && msg.getReceiver().equals(friendID)) || (msg.getSender().equals(friendID) && msg.getReceiver().equals(userID))){
                StringBuilder string = new StringBuilder();
                string.append(userRepository.findOne(msg.getSender()).getFirstName())
                        .append(" ")
                        .append(userRepository.findOne(msg.getSender()).getLastName())
                        .append(": \"")
                        .append(msg.getMessage())
                        .append("\", AT ")
                        .append(msg.getLdt().toString());
                messages.add(new Pair(msg.getLdt(), string.toString()));

            }

        });

        messages.sort(Comparator.comparing(Pair::getKey));
        return messages;
    }

    public List<Utilizator> getStrangers(Long id, String criteria) {

        String firstName = "";
        String lastName = "";

        if(criteria.contains(" ")){
            firstName = criteria.split(" ")[0];
            lastName = criteria.split(" ")[1];
        }
        else{
            firstName = criteria;
        }

        List<Utilizator> strangerList = new ArrayList<>();
        List<Long> friendIds = new ArrayList<>();

        this.getAllFriendships().forEach(friendship -> {
            if(friendship.getFirstUser().equals(id))
                friendIds.add(friendship.getSecondUser());
            else
                if(friendship.getSecondUser().equals(id))
                    friendIds.add(friendship.getFirstUser());

        });

        String finalFirstName = firstName;
        String finalLastName = lastName;
        this.getAllUsers().forEach(user -> {
            if(!friendIds.contains(user.getId()) && user.getFirstName().equals(finalFirstName)) {
                if(finalLastName.equals(""))
                    strangerList.add(user);
                else if(user.getLastName().equals(finalLastName))
                    strangerList.add(user);
            }
        });
        return strangerList;
    }

    public String getFriendRequestStatus(Long sender, Long receiver) {
        AtomicReference<String> status = new AtomicReference<>("NONE");
        this.friendRequestRepository.findAll().forEach(req -> {
            if(req.getSender().equals(sender) && req.getReceiver().equals(receiver))
                status.set(req.getStatus());
        });
        return status.toString();
    }

    public void deleteFriendRequest(FriendRequest friendRequest) {
        AtomicReference<Long> id = new AtomicReference<>(0L);
        this.friendRequestRepository.findAll().forEach(req -> {
                if(req.getSender().equals(friendRequest.getSender()) && req.getReceiver().equals(friendRequest.getReceiver()))
                    id.set(req.getId());
        });
        friendRequestRepository.delete(id.get());
    }

    public Utilizator getUserByName(String firstName, String lastName) {

        AtomicReference<Utilizator> user = new AtomicReference<>();
        this.getAllUsers().forEach(u -> {
            if(u.getFirstName().equals(firstName) && u.getLastName().equals(lastName))
                user.set(u);
        });
        return user.get();

    }
}
