public class MyObserverThread extends Thread {

    private MyDictionary dictionary;

    public MyObserverThread(MyDictionary dictionary){
        this.dictionary = dictionary;
    }

    @Override
    public void run() {
        while(true){
            dictionary.iterate();
        }
    }
}
