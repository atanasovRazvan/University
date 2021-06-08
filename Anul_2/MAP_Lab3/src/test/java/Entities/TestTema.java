package Entities;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestTema {
    private Tema t = new Tema(1, 0,8,"lab3");

    /**
     * tests the getter for homework ID
     */
    @Test
    public void getID() {
        assert t.getID()==1;
    }

    /**
     * tests the setter for homework ID
     */
    @Test
    public void setID() {
        t.setID(2);
        assert t.getID()==2;
    }

    /**
     * tests the getter for homework deadline
     */
    @Test
    public void getDeadline() {
        assert t.getDeadline()==8;
    }

    /**
     * tests the setter for homework deadline
     */
    @Test
    public void setDeadline() {
        t.setDeadline(13);
        assert t.getDeadline()==13;
    }

    /**
     * tests the getter for homework description
     */
    @Test
    public void getDescriere() {
        assert t.getDescriere()=="lab3";
    }

    /**
     * tests the setter for homework description
     */
    @Test
    public void setDescriere() {
        t.setDescriere("lab4");
        assert t.getDescriere()=="lab4";
    }

    /**
     * Tests the getter for the week of the presentation of the homework
     */
    @Test
    public void getSaptPredare(){
        t.setSaptPredare(5);
        assert t.getSaptPredare() == 5;
    }

    /**
     * Tests for the toString method
     */
    @Test
    public void tostring(){
        assert t.toString()!="";
    }

}