package chat.model;

import java.util.Set;
import java.util.TreeSet;
import java.io.Serializable;

public class User implements Comparable<User>, Serializable{
    private String Id, passwd, name;
    private Set<User> friends;

    public User(String id) {
        this(id,"","");
    }

    public User(String id, String passwd) {
        this(id,passwd,"");
    }

    public User(String id, String passwd, String name) {
        Id = id;
        this.passwd = passwd;
        this.name = name;
        friends=new TreeSet<User>();
    }

    public String getPasswd() {
        return passwd;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public User[] getFriends() {
            return friends.toArray(new User[friends.size()]);
   }

    public void addFriend(User u){
        friends.add(u);

    }
    public void removeFriend(User u){
        friends.add(u);
    }


    public int compareTo(User o) {
        return Id.compareTo(o.Id);  
    }

    public boolean equals(Object obj) {
        if (obj instanceof User){
            User u=(User)obj;
            return Id.equals(u.Id);
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Id != null ? Id.hashCode() : 0;
    }
}
