import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int noMessages = 100;

        List<List<String>> messages= new ArrayList<>();
        for(int i = 0; i < noMessages; i ++){
            List<String> segment = new ArrayList<>();
            segment.add("Nume_" + i/10);
            segment.add("Prieten_" + i%10);
            messages.add(segment);
        }

        MyDictionary dictionary = new MyDictionary();
        Integer N = 0;

        Thread t1 = new MyThread(dictionary, messages.subList(0, noMessages/2));
        Thread t2 = new MyThread(dictionary, messages.subList(noMessages/2+1, noMessages));
        Thread t3 = new MyObserverThread(dictionary);

        t1.start(); t2.start(); t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

    }

}
