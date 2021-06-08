import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class S1_inClass {

    public static class MyThread extends Thread{

        double[] a;
        double[] b;
        double[] c;
        int start;
        int stop;
        BinaryOperator<Double> operator;

        public MyThread(double[] a, double[] b, double[] c, int start, int stop, BinaryOperator<Double> operator) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.start = start;
            this.stop = stop;
            this.operator = operator;

        }

        public void run(){
            for(int i = start; i < stop; i++){
                c[i] = operator.apply(a[i], b[i]);
            }
        }

    }

    public static void parallelAdd(double[] a, double[] b, double[] c, int N, int P, BinaryOperator<Double> operator){

        long beginning = System.nanoTime();

        Thread[] threads = new MyThread[P];

        int chunk = N/P;
        int reminder = N%P;
        int start = 0;
        int end = 0;

        for(int i = 0; i < P; i ++){

            if(reminder > 0){
                end = start + chunk + 1;
                reminder -= 1;
            }
            else end = start + chunk;

            threads[i] = new MyThread(a, b, c, start, end, operator);
            start = end;


        }

        long overheadTime = System.nanoTime();
        System.out.println("Overhead: " + (overheadTime-beginning));

        for(int i = 0; i < P; i ++){
            threads[i].start();
        }

        for(int i = 0; i < P; i ++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sequencialAdd(double[] a, double[] b, double[] c, int N, BinaryOperator<Double> operator){

        for(int i = 0; i < N; i ++){
            c[i] = operator.apply(a[i], b[i]);
        }

    }

    public static void main(String[] args) {

        int N = 10000000;
        int P = 4;

        double[] a = new double[N];
        double[] b = new double[N];
        double[] c = new double[N];

        for(int i = 0; i < N; i ++){
            a[i] = 1;
            b[i] = 2;
        }

        long start = System.nanoTime();
        parallelAdd(a, b, c, N, P, (x,y)->Math.sqrt(x*x*x + y*y*y));
        long stop = System.nanoTime();
        System.out.println("Parallel time: " + (stop - start));

        start = System.nanoTime();
        sequencialAdd(a, b, c, N, (x,y)->Math.sqrt(x*x*x + y*y*y));
        stop = System.nanoTime();
        System.out.println("Secvential time: " + (stop - start));

    }

}
