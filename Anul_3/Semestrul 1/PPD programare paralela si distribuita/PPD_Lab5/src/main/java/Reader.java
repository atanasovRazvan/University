import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Reader extends Thread {
    private List<String> files;
    private SynchronizedQueue<Monom> queue;
    private CyclicBarrier barrier;

    public Reader(List<String> files, SynchronizedQueue<Monom> synchronizedQueue, CyclicBarrier barrier) {
        this.files = files;
        this.queue = synchronizedQueue;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            for (String file : files) {
                Scanner scanner = new Scanner(new File(file));

                while (scanner.hasNextInt()) {
                    int value = scanner.nextInt();
                    int power = scanner.nextInt();

                    Monom monom = new Monom(power, value);
                    queue.push(monom);
                }
                scanner.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        try {
            queue.push(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
