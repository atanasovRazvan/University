import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Utils {

    public static void createFile(String fname, Integer size, Integer min, Integer max){

        try {
            File file = new File(fname);
            if (file.createNewFile())
                System.out.println("File created");
            else
                System.out.println("File already exists");

            FileWriter writer = new FileWriter(fname);
            Random random = new Random();
            for(int i = 0; i < size; i ++){
                int value = random.nextInt(max-min) + min;
                writer.write(value + "\n");
            }

            writer.close();
        }
        catch(IOException exception){
            exception.printStackTrace();
        }

    }

    public static boolean checkEquality(String fname1, String fname2, String type){

        try {
            File file1 = new File(fname1);
            File file2 = new File(fname2);

            if(file1.length() != file2.length())
                return false;

            Scanner f1scanner = new Scanner(file1);
            Scanner f2scanner = new Scanner(file2);

            if (type.equals("INT")) {

                while(f1scanner.hasNextInt())
                    if(f1scanner.nextInt() != f2scanner.nextInt())
                        return false;

            } else if (type.equals("DOUBLE")) {

                while(f1scanner.hasNextDouble())
                    if(f1scanner.nextDouble() != f2scanner.nextDouble())
                        return false;

            } else System.out.println("Incorrect type");

            f1scanner.close();
            f2scanner.close();

            return true;

        }
        catch(IOException exception){
            exception.printStackTrace();
        }
        return false;
    }

}
