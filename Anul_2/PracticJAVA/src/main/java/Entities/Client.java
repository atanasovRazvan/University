package Entities;

public class Client implements HasID<Long> {

    private Long clientID;
    private String name;

    public Client(Long clientID, String name) {
        this.clientID = clientID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getID() {
        return clientID;
    }

    @Override
    public void setID(Long aLong) {
        clientID = aLong;
    }
}
