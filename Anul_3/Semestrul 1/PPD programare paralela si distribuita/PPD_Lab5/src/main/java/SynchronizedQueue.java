import java.util.LinkedList;
import java.util.Queue;

public class SynchronizedQueue<T> {
    private final Queue<T> queue;
    private int noOfWorkers;
    private int capacity;
    private int notFinished;

    public SynchronizedQueue(int noOfProducers, int noOfWorkers) {
        this.noOfWorkers = noOfWorkers;
        this.capacity = noOfProducers * 10;
        this.notFinished = noOfProducers;
        this.queue = new LinkedList<>();
    }

    public synchronized void push(T element) throws InterruptedException {
        while (queue.size() == capacity)
            wait();

        queue.add(element);
        notifyAll();
    }

    public synchronized T pop() throws InterruptedException {
        while (queue.isEmpty())
            wait();

        T item = queue.remove();
        notifyAll();
        return item;
    }

    public synchronized void stop() {
        this.notFinished -= 1;
        if (this.notFinished == 0) {
            for (int i = 0; i < noOfWorkers; ++i) {
                queue.add(null);
            }
        }
    }
}
