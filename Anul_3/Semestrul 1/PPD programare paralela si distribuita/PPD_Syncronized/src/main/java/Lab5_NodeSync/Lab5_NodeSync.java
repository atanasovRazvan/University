package Lab5_NodeSync;

import data.PolynomialGenerator;
import sequencial.Sequencial;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lab5_NodeSync {

    private static void validate() throws IOException {

        BufferedReader sequencialReader = new BufferedReader(new FileReader("result.txt"));
        BufferedReader parallelReader= new BufferedReader(new FileReader("resultParallelLab5.txt"));

        String seqLine = sequencialReader.readLine();
        String parLine = parallelReader.readLine();

        while(seqLine != null && parLine != null) {
            assert seqLine.equals(parLine);
            seqLine = sequencialReader.readLine();
            parLine = parallelReader.readLine();
        }

    }

    public static void main(String[] args) {

        // 10 1000 50
        // 5 10000 100

        // p : 4, 6, 8

        //PolynomialGenerator pg = new PolynomialGenerator(10, 1000, 50);
        //pg.generatePolynomials();

        long average = 0;

        for(int i = 0; i < 5; i ++) {
            long start = System.nanoTime();

            //Sequencial.solve(10);
            Parallel.solve(10, 8, 3);

            long end = System.nanoTime();

            average += (end - start);
            }

        System.out.println(average/5);

//        try{
//            validate();
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }

    }

}
