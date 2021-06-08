import org.junit.Test;

import static org.junit.Assert.*;

public class SquareTest {

    @Test
    public void getPerimeter() {
        Square s = new Square(5);
        //assert(s.getPerimeter().equals(20));
        assertEquals((int)s.getPerimeter(), 20);
    }

    @Test
    public void getArea() {
        Square s = new Square(5);
        //assert(s.getArea().equals(25));
        assertEquals((int)s.getArea(), 25);
    }
}