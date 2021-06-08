
public class Executor extends Thread {
    private LinkedList linkedList;
    private SynchronizedQueue<Monom> queue;

    public Executor(LinkedList linkedList, SynchronizedQueue<Monom> queue) {
        this.linkedList = linkedList;
        this.queue = queue;
    }

    @Override
    public void run() {
        Monom monom = null;
        try {
            monom = queue.pop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (monom != null) {
            linkedList.addMonom(monom);
            try {
                monom = queue.pop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
