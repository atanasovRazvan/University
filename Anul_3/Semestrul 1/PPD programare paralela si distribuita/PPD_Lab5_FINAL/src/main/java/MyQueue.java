import java.util.LinkedList;
import java.util.Queue;

public class MyQueue<T> {

    private Queue<T> queue;

    public MyQueue(){
        this.queue = new LinkedList<>();
    }

    public synchronized void push(T object){
        queue.add(object);
        notifyAll();
    }

    public synchronized T pop() throws InterruptedException {
        while(queue.isEmpty()) {
            wait();
        }

        T object = queue.remove();
        notifyAll();
        return object;
    }

}
