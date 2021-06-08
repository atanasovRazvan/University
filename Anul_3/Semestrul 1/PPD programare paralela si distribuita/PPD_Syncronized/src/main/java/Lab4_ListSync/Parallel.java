package Lab4_ListSync;

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

        public void run(){

            try {

                for (int i = 0; i < noPolynomials; i++) {

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

                    noPolynomialsAdded++;
                    synchronized (queue) {
                        queue.add(polynomial);
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

                synchronized (queue){
                    if (queue.isEmpty() && noPolynomialsAdded.equals(noPolynomials)) {
                        return;
                    }
                }

                // Altfel, facem calculul sincronizand obiectul "result"

                Map<Integer, Integer> polynomial;

                synchronized (queue) {
                    polynomial = queue.poll();
                }

                if(polynomial != null)
                    for (Map.Entry<Integer, Integer> entry : polynomial.entrySet()) {
                        synchronized (result) {
                            if (result.containsKey(entry.getKey()))
                                result.put(entry.getKey(), result.get(entry.getKey()) + entry.getValue());
                            else
                                result.put(entry.getKey(), entry.getValue());
                        }
                    }

            }
        }

    }

    public static void solve(Integer noPols, Integer p) {

        noPolynomials = noPols;
        noPolynomialsAdded = 0;

        List<Thread> threads = new ArrayList<>();

        MyReaderThread readerThread = new MyReaderThread();
        threads.add(readerThread);

        for(int i = 1; i < p; i ++){
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
            File file = new File("resultParallelLab4.txt");
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
