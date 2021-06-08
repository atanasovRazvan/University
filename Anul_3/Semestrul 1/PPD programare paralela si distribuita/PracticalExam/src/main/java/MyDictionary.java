import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class MyDictionary {

    private List<Pair<String, List<String>>> dictionary;
    private Integer noProcessed = 0;

    public MyDictionary(){
        dictionary = new ArrayList<>();
    }

    public synchronized void insert(List<String> message){

        int i = 0;
        int passed = 0;
        noProcessed ++;
        if(noProcessed%10 == 0)
            notify();
        for( ; i < dictionary.size(); i ++){
            Pair<String, List<String>> fr = dictionary.get(i);

            if(fr.getKey().equals(message.get(0))){
                List<String> friends = fr.getValue();
                friends.add(message.get(1));
                Pair<String, List<String>> pair = new Pair<>(fr.getKey(), friends);
                dictionary.set(i, pair);
                return;
            }

            if(message.get(0).compareTo(fr.getKey()) < 0) {
                passed = 1;
                break;
            }
        }

        if(passed == 1){
            List<String> friends = new ArrayList<>();
            friends.add(message.get(1));
            dictionary.add(i, new Pair<>(message.get(0), friends));
        }

        else{
            List<String> friends = new ArrayList<>();
            friends.add(message.get(1));
            dictionary.add(new Pair<>(message.get(0), friends));
        }

    }

    public synchronized void iterate(){

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Pair<String, List<String>> stringListPair : dictionary) {
            System.out.println(stringListPair.getKey() + " : " + stringListPair.getValue());
        }

    }

}
