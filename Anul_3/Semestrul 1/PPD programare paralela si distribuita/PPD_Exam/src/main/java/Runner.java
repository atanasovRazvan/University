
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private final int n = 20;
    private final int p = 3;
    private final int c = 2;
    private final MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(n);
    private final List<Integer> list = new ArrayList<>();
    private int noConsumersFinished = 0;
    private int noProducersFinished = 0;
    private ReentrantLock lock = new ReentrantLock();

    class Producer extends Thread{
        @Override
        public void run() {
            Integer k = 0;
            for(int i = 0; i < 100; i ++){
                try {
                    sleep(100);
                    if(noConsumersFinished == c)
                        break;
                    lock.lock();
                    for (int j = 0; j < 4; j++) {
                        queue.push(k + 1);
                        k = k + 1;
                    }
                    lock.unlock();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            synchronized (this) {
                noProducersFinished++;
            }
            System.out.println("PRODUCER DONE");
        }
    }

    class Consumer extends Thread{
        @Override
        public void run() {
            for(int i = 0; i < 100; i ++){
                try{
                    sleep(80);
                    lock.lock();
                    for (int j = 0; j < 3; j++) {
                        Integer k = queue.pop();
                        synchronized (list) {
                            list.add(k);
                        }
                    }
                    lock.unlock();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            synchronized (this) {
                noConsumersFinished++;
            }
            System.out.println("CONSUMER DONE");
        }
    }

    class Queryer extends Thread{
        @Override
        public void run() {
            while(noConsumersFinished + noProducersFinished < p + c){
                try {
                    sleep(200);
                    synchronized (list) {
                        System.out.println("Query");
                        list.forEach(el -> {});
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void run() {
        List<Thread> robots = new ArrayList<>();
        for(int i = 0; i < p; i ++)
            robots.add(new Producer());
        for(int i = 0; i < c; i ++)
            robots.add(new Consumer());
        robots.add(new Queryer());
        robots.forEach(Thread::start);
        robots.forEach(robot -> {
            try {
                robot.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
