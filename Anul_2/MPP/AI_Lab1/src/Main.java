import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.StrictMath.sqrt;

class Pair<T, U>{
    private T key;
    private U value;
    public Pair(T Key, U Value){
        key=Key;
        value=Value;
    }
    public T getKey(){
        return key;
    }
    public U getValue(){
        return value;
    }
}

class Tests{

    public static void testDistantaEuclidiana(){

        assert Main.distantaEuclideana(1,5,4,1).equals(5.0);
        assert Main.distantaEuclideana(1,5,1,6).equals(1.0);
        assert Main.distantaEuclideana(3,7,1,7).equals(2.0);
        assert Main.distantaEuclideana(71,50,20,50).equals(51.0);
        assert Main.distantaEuclideana(13,281,13,581).equals(300.0);

    }

    public static void testProdusScalar(){

        Double[] V1 = {1.0, 2.0, 0.0, 4.0, 0.0};
        Double[] V2 = {0.0, 0.0, 3.0, 1.0, 0.0};
        Double[] U1 = {0.0, 2.0, 3.0, 4.0, 0.0};
        Double[] U2 = {5.0, 123.0, 3.0, 7.0, 0.0};

        assert Main.produsScalar(V1, U1).equals(20.0);
        assert Main.produsScalar(V2, U2).equals(16.0);
        assert Main.produsScalar(V1, U2).equals(279.0);

    }

    public static void testLinieMaxima1(){

        Integer[][] M1 = {{0,0,0,1,1},{0,1,1,1,1},{0,0,1,1,1}};
        assert Main.linieMaxima1(M1).equals(2);
        Integer[][] M2 = {{0,0,0,1,1},{0,1,1,1,1},{0,1,1,1,1}};
        assert Main.linieMaxima1(M1).equals(2);
        Integer[][] M3 = {{0,0,0,1,1},{0,1,1,1,1},{1,1,1,1,1}};
        assert Main.linieMaxima1(M1).equals(3);

    }

    public static void testLinieMaxima2(){

        Integer[][] M1 = {{0,0,0,1,1},{0,1,1,1,1},{0,0,1,1,1}};
        assert Main.linieMaxima2(M1).equals(2);
        Integer[][] M2 = {{0,0,0,1,1},{0,1,1,1,1},{0,1,1,1,1}};
        assert Main.linieMaxima2(M1).equals(2);
        Integer[][] M3 = {{0,0,0,1,1},{0,1,1,1,1},{1,1,1,1,1}};
        assert Main.linieMaxima2(M1).equals(3);

    }

    public static void testPb5(){

        Integer[] V1 = {1,2,3,4,2};
        assert Main.pb5(V1).equals(2);
        Integer[] V2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10};
        assert Main.pb5(V2).equals(10);
        Integer[] V3 = {1, 1, 2, 3, 4, 5, 6, 7};
        assert Main.pb5(V3).equals(1);

    }

}

public class Main {

    public static Double distantaEuclideana(Integer A1, Integer A2, Integer B1, Integer B2){
        // O(1)
        // Returneaza Double (distanta Euclideana dintre cele doua puncte)
        return sqrt((A1-A2)*(A1-A2)+(B1-B2)*(B1-B2));
    }

    public static Double produsScalar(Double[] U, Double[] V){

        // O(N)
        // Returneaza Double (produsul scalar al vectorilor)
        Double s = 0.0;
        for(int i = 0; i < U.length; i++)
            s += U[i]*V[i];
        return s;

    }

    public static Integer linieMaxima1(Integer[][] M){

        // O(N*N) timp
        // O(N) memorie
        // Returneaza Integer (linia cu cei mai multi de 1)
        Integer[] primaAparitie = {};
        boolean ok = false;
        for(int i = 0; i < M.length; i++)
            for(int j = 0; j < M[i].length; j++) {
                ok = false;
                if (M[i][j].equals(1)) {
                    primaAparitie[i] = j;
                    ok = true;
                }
            }

        Integer minim = M.length;

        for(int i = 0; i < primaAparitie.length; i++){
            if(primaAparitie[i] < primaAparitie[minim])
                minim = i;
        }

        return minim;

    }

    public static Integer linieMaxima2(Integer[][] M){

        // O(n*n) timp
        // O(1) spatiu
        // returneaza int linia cu cei mai multi de 1
        Integer minim = M.length;
        boolean ok = false;
        for(int i = 0; i < M.length; i ++)
            for(int j = 0; j < M.length && !ok; i ++) {
                ok = false;
                if (M[i][j] == 1 && minim > i) {
                    minim = i;
                    ok=true;
                }
            }
        return minim;

    }

    public static Integer pb5(Integer[] V){

        // O(n) timp
        // O(1) spatiu
        // Returneaza int numarul duplicat
        Integer sum = (V.length - 1) * (V.length - 2) / 2;
        for (Integer integer : V) sum -= integer;
        return -sum;

    }

    public static void main(String[] args) {

        Tests.testDistantaEuclidiana();
        Tests.testProdusScalar();
        Tests.testLinieMaxima1();
        Tests.testLinieMaxima2();
        Tests.testPb5();
        //executeDistantaEuclideana();

    }

}
