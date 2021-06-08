public class Main {

    public static void main(String[] args) {
        Repository repo = new Repository();
        repo.add(new Student(1, "Atanasov", "Razvan", 20, "1991011270010"));
        repo.update(new Student(1, "Ardeal", "Andrei", 21, "1234"));
        repo.delete(1);
        repo.printAll();

    }

}
