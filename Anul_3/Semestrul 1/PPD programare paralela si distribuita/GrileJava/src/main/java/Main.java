public class Main {

    public static void main(String[] args) {

        MyObject object = new MyObject("object");
        MyRepository.getInstance().addObject(object);
        System.out.println(object);

    }

}
