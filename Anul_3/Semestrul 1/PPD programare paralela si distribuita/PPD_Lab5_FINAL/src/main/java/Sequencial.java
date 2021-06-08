import java.io.File;
import java.util.*;

public class Sequencial {

    public static void main(String[] args) {

        try {
            int noPolynomials = 10;

            Queue<Monom> queue = new LinkedList<>();
            TreeMap<Integer, Integer> monoms = new TreeMap<>();

            long averageTime = 0L;

            for (int K = 0; K < 5; K++) {

                Long start = System.nanoTime();

                for (int i = 1; i <= noPolynomials; i++) {

                    Scanner scanner = new Scanner(new File("src/main/java/data/files/polynomial" + i + ".txt"));
                    while (scanner.hasNextInt()) {
                        Integer exponent = scanner.nextInt();
                        Integer coefficient = scanner.nextInt();
                        Monom monom = new Monom(exponent, coefficient);
                        queue.add(monom);
                    }
                    scanner.close();

                    while(!queue.isEmpty()){
                        Monom monom = queue.poll();
                        Integer value = monoms.putIfAbsent(monom.getExponent(), monom.getExponent());
                        if(value != null)
                            monoms.put(monom.getExponent(), monom.getExponent() + value);
                    }

                }

                if(K<4){
                    monoms.clear();
                    queue.clear();
                }

                Long finish = System.nanoTime();
                averageTime += (finish - start);

            }

            System.out.println(averageTime / 5);

            Scanner scanner = new Scanner(new File("src/main/java/result.txt"));
            for(Map.Entry<Integer, Integer> entry : monoms.entrySet()){
                assert scanner.nextInt() == entry.getKey() : "Incorrect";
                assert scanner.nextInt() == entry.getValue() : "Incorrect";
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
