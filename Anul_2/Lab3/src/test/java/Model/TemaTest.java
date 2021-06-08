package Model;


import org.junit.Test;

class TemaTest {
    private Tema t = new Tema(1,5,4,"lab3");

    /**
     * tests the getter for homework ID
     */
    @Test
    void getID() {
        assert t.getID()==1;
    }

    /**
     * tests the setter for homework ID
     */
    @Test
    void setID() {
        t.setID(2);
        assert t.getID()==2;
    }

    /**
     * tests the getter for homework deadline
     */
    @Test
    void getDeadline() {
        assert t.getDeadline()==5;
    }

    /**
     * tests the setter for homework deadline
     */
    @Test
    void setDeadline() {
        t.setDeadline(6);
        assert t.getDeadline()==6;
    }

    /**
     * tests the getter for homework presentation week
     */
    @Test
    void getSaptPredare() {
        assert t.getSaptPredare()==4;
    }

    /**
     * tests the setter for homework presentation week
     */
    @Test
    void setSaptPredare() {
        t.setSaptPredare(5);
        assert t.getSaptPredare()==5;
    }

    /**
     * tests the getter for homework description
     */
    @Test
    void getDescriere() {
        assert t.getDescriere()=="lab3";
    }

    /**
     * tests the setter for homework description
     */
    @Test
    void setDescriere() {
        t.setDescriere("lab4");
        assert t.getDescriere()=="lab4";
    }

    /**
     * tests the getter for homework deadline extension
     */
    @Test
    void prelungireDeadline() {
        t.prelungireDeadline(4);
        assert t.getDeadline()==6;
    }
}