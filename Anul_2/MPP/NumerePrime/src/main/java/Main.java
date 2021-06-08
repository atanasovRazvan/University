public class Main {

    public static void main(String[] args) {

        NumberPair numberPair1 = new NumberPair(5, 10);
        NumberPair numberPair2 = new NumberPair(3, 10);
        NumberPair numberPair3 = new NumberPair(2, 10);
        System.out.println(numberPair1.getCMMDC() == 1);
        System.out.println(numberPair2.getCMMDC() == 1);
        System.out.println(numberPair3.getCMMDC() == 1);

    }

}
