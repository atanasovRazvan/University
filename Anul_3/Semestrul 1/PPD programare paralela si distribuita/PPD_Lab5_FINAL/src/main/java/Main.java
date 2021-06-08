import data.PolynomialGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {

        Integer p = 8;
        Integer p1 = 3;

        Integer noPolynomials = 10;
        Integer maxGrade = 1000;
        Integer maxMonoms = 50;

//        PolynomialGenerator generator = new PolynomialGenerator(noPolynomials, maxGrade, maxMonoms);
//        generator.generatePolynomials();

        try {
            execute(p, p1, noPolynomials);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    private static void execute(Integer p, Integer p1, Integer noPolynomials) throws IOException {

        MyLinkedList list = new MyLinkedList();
        MyQueue<Monom> queue;
        CyclicBarrier barrier = new CyclicBarrier(p1);

        Long averageTime = 0L;

        for(int K = 0; K < 5; K ++) {

            list = new MyLinkedList();
            queue = new MyQueue<>();

            Long start = System.nanoTime();

            List<Thread> threads = new ArrayList<>();

            int rest = noPolynomials % p1;
            int chunk = noPolynomials / p1;
            int fileIndex = 1;

            for (int i = 0; i < p1; i++) {
                List<String> fileNames = new ArrayList<>();

                int actualChunk = chunk;
                if (rest > 0) {
                    rest--;
                    actualChunk++;
                }

                for (int j = 0; j < actualChunk; j++, fileIndex++) {
                    fileNames.add("src/main/java/data/files/polynomial" + fileIndex + ".txt");
                }

                threads.add(new Reader(fileNames, queue, barrier));
            }

            for (int i = 0; i < p - p1; i++) {
                threads.add(new Worker(list, queue));
            }

            threads.forEach(Thread::start);

            threads.forEach(thread -> {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Long finish = System.nanoTime();

            averageTime += (finish - start);
        }

        System.out.println(averageTime/5);

        FileWriter fw = new FileWriter("src/main/java/result.txt");
        list.allMonoms().forEach(monom -> {
            try {
                fw.write(monom.getExponent() + " " + monom.getCoefficient() + '\n');
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });

        fw.close();

    }

}
