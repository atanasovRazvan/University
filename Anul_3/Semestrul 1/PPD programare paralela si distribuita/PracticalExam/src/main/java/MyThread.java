import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

class MyThread extends Thread{

    private MyDictionary dictionary;
    private List<List<String>> messages;
    private ReentrantLock lock;

    MyThread(MyDictionary dictionary, List<List<String>> messages){
        this.dictionary = dictionary;
        this.messages = messages;
        this.lock = new ReentrantLock();
    }

    @Override
    public void run() {
        for(int i = 0; i < messages.size(); i ++){
            dictionary.insert(messages.get(i));
            System.out.println("Inserted " + messages.get(i).get(0));
        }
    }
}