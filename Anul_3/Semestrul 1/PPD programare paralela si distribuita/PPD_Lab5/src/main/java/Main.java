import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) throws IOException {
        String prefix = args[0];
        int polinaome = Integer.parseInt(args[1]);
        int p = Integer.parseInt(args[2]);

        int readThreads = p / 2;

        if (readThreads > polinaome) {
            readThreads = polinaome;
        }
        int rest = p - readThreads;

        LinkedList linkedList = new LinkedList();
        SynchronizedQueue<Monom> queue = new SynchronizedQueue<>(1, rest);
        CyclicBarrier barrier = new CyclicBarrier(readThreads);

        Thread[] executors = new Thread[rest];
        Thread[] readers = new Thread[readThreads];

        double start = System.nanoTime();
        for (int i = 0; i < rest; ++i) {
            executors[i] = new Executor(linkedList, queue);
            executors[i].start();
        }

        int index = 1;
        int chunk = polinaome / readThreads;
        int restChunk = polinaome % readThreads;

        for (int i = 0; i < readThreads; ++i) {
            List<String> files = new ArrayList<>();

            for (int j = 0; j < chunk; ++j) {
                files.add(prefix + index + ".txt");
                index += 1;
            }

            if (restChunk > 0) {
                restChunk -= 1;
                files.add(prefix + index + ".txt");
                index += 1;
            }

            readers[i] = new Reader(files, queue, barrier);
            readers[i].start();
        }

        double finish = System.nanoTime();
        writeToFile(linkedList);
        System.out.println(finish - start);
    }

    private static void writeToFile(LinkedList linkedList) throws IOException {
        FileWriter file = new FileWriter("output.txt");

        for (Monom monom : linkedList.getAll()) {
            file.write(monom.getValue() + " " + monom.getPower() + "\n");
        }
        file.close();
    }
}
