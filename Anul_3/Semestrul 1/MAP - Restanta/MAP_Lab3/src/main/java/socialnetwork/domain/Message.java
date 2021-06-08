package socialnetwork.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Message extends Entity<Long> {

    private Long id;
    private String message;
    private Long sender, receiver;
    private LocalDateTime ldt;
    private Long reply;

    public Message(String message, Long sender, Long receiver, LocalDateTime ldt) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.ldt = ldt;
        this.reply = Long.parseLong(String.valueOf(0));
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    public void setLdt(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    public Long getReply() {
        return reply;
    }

    public void setReply(Long reply) {
        this.reply = reply;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    @Override
    public String toString(){
        return this.getId() + ". Message from " + this.sender + " to " + this.receiver + ": " + this.message;
    }

}
