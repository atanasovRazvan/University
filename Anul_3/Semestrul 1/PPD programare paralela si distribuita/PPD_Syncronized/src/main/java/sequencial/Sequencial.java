package sequencial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Sequencial {

    public static void solve(Integer noPolynomials){

        Map<Integer, Integer> result = new HashMap<>();

        try {

            for (int i = 0; i < noPolynomials; i++) {

                String fName = "src/main/java/data/files/polynomial" + (i+1) + ".txt";
                BufferedReader scanner = new BufferedReader(new FileReader(fName));

                String line = scanner.readLine();
                while(line != null){
                    String[] values = line.split(" ");
                    Integer exponent = Integer.parseInt(values[0]);
                    Integer coef = Integer.parseInt(values[1]);

                    if(result.containsKey(exponent))
                        result.put(exponent, result.get(exponent)+coef);
                    else
                        result.put(exponent, coef);

                    line = scanner.readLine();
                }

            }

            File file = new File("result.txt");
            boolean ignored = file.createNewFile();
            FileWriter fw = new FileWriter(file);

            Map<Integer, Integer> sortedResult = new TreeMap<>(result);

            for(Map.Entry<Integer, Integer> entry : sortedResult.entrySet()){
                if(!entry.getValue().equals(0))
                    fw.write(entry.getKey() + " " + entry.getValue() + '\n');
            }
            fw.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
