package data;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PolynomialGenerator {

    private final Integer maxGrade;
    private final Integer maxSize;
    private final Integer polynomialNumber;

    public PolynomialGenerator(Integer polynomialNumber, Integer maxGrade, Integer maxSize) {
        this.polynomialNumber = polynomialNumber;
        this.maxGrade = maxGrade;
        this.maxSize = maxSize;
    }

    private void createFile(Integer id){

        String fName = "src/main/java/data/files/polynomial" + id + ".txt";

        try{
            File file = new File(fName);
            boolean ignored = file.createNewFile();
            FileWriter fileWriter = new FileWriter(fName);

            Random random = new Random();
            int size = random.nextInt() % maxSize;
            if(size<0) size=-size;
            size++;

            List<Integer> exponents = new ArrayList<>();
            for(int i = 0; i < size;){
                int exponent = random.nextInt() % (maxGrade+1);
                if(exponent < 0) exponent = -exponent;
                if(!exponents.contains(exponent)) {
                    exponents.add(exponent);
                    i++;
                }
            }
            exponents.sort(Integer::compare);

            List<Integer> coeficients = new ArrayList<>();
            for(int i = 0; i < size;){
                Integer coef = random.nextInt()%1000;
                while(coef.equals(0)) coef = random.nextInt()%1000;
                if(!coeficients.contains(coef)) {
                    coeficients.add(coef);
                    i++;
                }
            }

            for(int i = 0; i < exponents.size(); i ++){
                String member = exponents.get(i) + " " + coeficients.get(i);
                fileWriter.write(member + '\n');
            }

            fileWriter.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public void generatePolynomials(){


        for(int i = 0; i < this.polynomialNumber; i ++)
            createFile(i+1);

    }

}

