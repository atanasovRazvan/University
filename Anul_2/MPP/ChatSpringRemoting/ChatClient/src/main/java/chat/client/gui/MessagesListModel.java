package chat.client.gui;

import chat.model.Message;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class MessagesListModel extends AbstractListModel {
    private List<String> messages;
    public MessagesListModel() {
        messages=new ArrayList<String>();
    }

    public int getSize() {
        return messages.size();
    }

    public Object getElementAt(int index) {
        return messages.get(index);
    }

    public void newMessage(String idSender, String mesg){
        String text="["+idSender+"]: "+mesg;
        messages.add(text);
        fireContentsChanged(this, messages.size()-1, messages.size());
    }
}
