import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Node {

    private Monom monom;
    private Node nextNode;
    private ReentrantReadWriteLock lock;

    public Node(Monom monom, Node nextNode){
        this.monom = monom;
        this.nextNode = nextNode;
        this.lock = new ReentrantReadWriteLock();
    }

    public void lock(){
        lock.writeLock().lock();
    }

    public void unlock(){
        lock.writeLock().unlock();
    }

    public Monom getMonom() {
        return monom;
    }

    public void setMonom(Monom monom) {
        this.monom = monom;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
}
