package socialnetwork.domain;

import java.time.LocalDateTime;

public class FriendRequest extends Entity<Long>{

    private Long sender, receiver;
    private String status;
    private LocalDateTime ldt;

    public FriendRequest(){}

    public FriendRequest(Long sender, Long receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    public void setLdt(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }
}
