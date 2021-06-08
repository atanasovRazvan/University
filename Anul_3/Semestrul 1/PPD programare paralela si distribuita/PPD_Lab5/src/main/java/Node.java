import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Node {
    private Monom monom;
    private Node next;
    private ReentrantReadWriteLock lock;

    public Node(Monom monom, Node next) {
        this.monom = monom;
        this.next = next;
        this.lock = new ReentrantReadWriteLock();
    }

    public void writeLock() {
        lock.writeLock().lock();
    }

    public void writeUnlock() {
        lock.writeLock().unlock();
    }

    public Monom getMonom() {
        return monom;
    }

    public void setMonom(Monom monom) {
        this.monom = monom;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
