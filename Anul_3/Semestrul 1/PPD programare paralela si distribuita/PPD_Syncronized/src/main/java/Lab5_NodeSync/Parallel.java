package Lab5_NodeSync;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Parallel {

    private static Map<Integer, Integer> result = new HashMap<>();
    private static LinkedList<Map<Integer, Integer>> queue = new LinkedList<>();
    private static Integer noPolynomials;
    private static Integer noPolynomialsAdded;

    public static class MyReaderThread extends Thread {

        private Integer nrPolynomialsToRead;

        public MyReaderThread(Integer nrPolynomialsToRead){
            super();
            this.nrPolynomialsToRead = nrPolynomialsToRead;
        }

        public void run(){

            try {

                for (int i = 0; i < nrPolynomialsToRead; i++) {

                    String fName = "src/main/java/data/files/polynomial" + (i + 1) + ".txt";
                    BufferedReader scanner = new BufferedReader(new FileReader(fName));

                    Map<Integer, Integer> polynomial = new HashMap<>();

                    String line = scanner.readLine();
                    while (line != null) {
                        String[] values = line.split(" ");
                        Integer exponent = Integer.parseInt(values[0]);
                        Integer coef = Integer.parseInt(values[1]);

                        if (polynomial.containsKey(exponent))
                            polynomial.put(exponent, polynomial.get(exponent) + coef);
                        else
                            polynomial.put(exponent, coef);

                        line = scanner.readLine();
                    }

                    synchronized (queue) {
                        queue.add(polynomial);
                    }

                    synchronized (noPolynomialsAdded) {
                        noPolynomialsAdded++;
                    }
                }

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    public static class MyComputerThread extends Thread{

        public void run() {

            while (true) {
                // Daca coada este goala dar s-au citit toate elementele, terminam executia

                if (queue.isEmpty() && noPolynomialsAdded.equals(noPolynomials)) {
                    return;
                }

                // Altfel, facem calculul sincronizand obiectul "result"

                Map<Integer, Integer> polynomial;

                synchronized (queue) {
                    polynomial = queue.poll();
                }

                if(polynomial != null)
                    for (Map.Entry<Integer, Integer> entry : polynomial.entrySet()) {
                        boolean computed = false;
                        if(result.get(entry.getKey()) == null) {
                            result.put(entry.getKey(), 0);
                            computed = true;
                        }
                        synchronized (result.get(entry.getKey())) {
                            if(!computed)
                                result.put(entry.getKey(), result.get(entry.getKey()) + entry.getValue());
                        }
                    }

            }
        }

    }

    public static void solve(Integer noPols, Integer p, Integer p1) {

        noPolynomials = noPols;
        noPolynomialsAdded = 0;

        List<Thread> threads = new ArrayList<>();
        int remaining = noPols % p1;

        for(int i = 0; i < p1; i ++) {
            Integer noPolynomialsToRead = noPols / p1;
            if(remaining > 0) noPolynomialsToRead++;
            MyReaderThread readerThread = new MyReaderThread(noPolynomialsToRead);
            remaining --;
            threads.add(readerThread);
        }

        for(int i = p1; i < p; i ++){
            MyComputerThread computerThread = new MyComputerThread();
            threads.add(computerThread);
        }

        for(Thread t : threads)
            t.start();

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            File file = new File("resultParallelLab5.txt");
            boolean ignored = file.createNewFile();
            FileWriter fw = new FileWriter(file);

            Map<Integer, Integer> sortedResult = new TreeMap<>(result);

            for (Map.Entry<Integer, Integer> entry : sortedResult.entrySet()) {
                if(!entry.getValue().equals(0))
                    fw.write(entry.getKey() + " " + entry.getValue() + '\n');
            }
            fw.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
