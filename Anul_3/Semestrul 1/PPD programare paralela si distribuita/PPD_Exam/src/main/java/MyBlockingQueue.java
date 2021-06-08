import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {

    private final Queue<T> queue;
    private int capacity;

    public MyBlockingQueue(int capacity){
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void push(T object) throws InterruptedException {
        while(queue.size() >= capacity)
            wait();
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