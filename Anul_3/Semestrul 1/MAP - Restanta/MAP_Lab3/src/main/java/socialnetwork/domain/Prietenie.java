package socialnetwork.domain;

import socialnetwork.utils.Utils;

import java.time.LocalDateTime;


public class Prietenie extends Entity<Long> {

    LocalDateTime date;
    Long firstUser;
    Long secondUser;

    public Prietenie(Long firstUser, Long secondUser, LocalDateTime date) {
        this.date = date;
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(Long firstUser) {
        this.firstUser = firstUser;
    }

    public Long getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(Long secondUser) {
        this.secondUser = secondUser;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString(){

        return Utils.ANSI_BLUE + "Friendship: " + this.firstUser + Utils.ANSI_BLUE +" and " +
                this.secondUser + Utils.ANSI_RESET + Utils.ANSI_BLUE + " at " + this.date + Utils.ANSI_RESET;

    }

}
