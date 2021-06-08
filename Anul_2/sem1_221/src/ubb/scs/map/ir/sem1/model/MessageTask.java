package ubb.scs.map.ir.sem1.model;

import ubb.scs.map.ir.sem1.utils.Constants;

import java.time.LocalDateTime;

public class MessageTask extends Task {
    private String message, from, to;
    LocalDateTime date;

    public MessageTask(String tastkID, String descriere, String message, String from, String to, LocalDateTime date) {
        super(tastkID, descriere);
        this.message = message;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    @Override
    public void execute() {
        System.out.println(message);
    }

    @Override
    public String toString() {

        return message + " " + date.format(Constants.DATE_TIME_FORMATER);
    }
}
