import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

public class Reader extends Thread {

    private List<String> fileNames;
    private MyQueue<Monom> queue;
    private CyclicBarrier barrier;

    public Reader(List<String> fileNames, MyQueue<Monom> queue, CyclicBarrier barrier){
        this.fileNames = fileNames;
        this.queue = queue;
        this.barrier = barrier;
    }

    @Override
    public void run(){

        try{
            for(String fileName: fileNames){
                Scanner scanner = new Scanner(new File(fileName));
                while(scanner.hasNextInt()){
                    Integer exponent = scanner.nextInt();
                    Integer coefficient = scanner.nextInt();
                    Monom monom = new Monom(exponent, coefficient);
                    queue.push(monom);
                }
                scanner.close();
            }

            barrier.await();
            for(int i = 0; i < 3; i ++)
                queue.push(null);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
