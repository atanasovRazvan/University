class Square{
    private Integer lenght;

    public Square(Integer l){
        lenght = l;
    }

    public Integer getPerimeter(){
        return 4*lenght;
    }

    public Integer getArea(){
        return lenght*lenght;
    }

}

public class Main {

    public static void main(String[] args) {
        Square s = new Square(5);
        System.out.println(s.getArea());
        System.out.println(s.getPerimeter());
    }

}
