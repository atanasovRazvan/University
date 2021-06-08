package model;

public class Client implements Entity<String> {

    private String username;
    private String name;

    public Client(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return this.username;
    }

    @Override
    public void setId(String s) {
        this.username = s;
    }
}
