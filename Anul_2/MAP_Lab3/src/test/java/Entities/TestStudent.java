package Entities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestStudent {
    private Student s = new Student(1,226, "Pop Ion","pop.ion@gmail.com","prof");

    /**
     * tests the getter for student's ID
     */
    @Test
    public void getID() {
        assert s.getID() == 1;
    }

    /**
     * tests the setter for student's ID
     */
    @Test
    public void setID() {
        s.setID(2);
        assert  s.getID()==2;
    }

    /**
     * tests the getter for student's group
     */
    @Test
    public void getGrupa() {
        assert s.getGrupa()==226;
    }

    /**
     * tests the setter for student's group
     */
    @Test
    public void setGrupa() {
        s.setGrupa(221);
        assert s.getGrupa()==221;
    }

    /**
     * tests the getter for student's name
     */
    @Test
    public void getNume() {
        assert s.getNume()=="Pop Ion";
    }

    /**
     * tests the setter for student's name
     */
    @Test
    public void setNume() {
        s.setNume("Ioana");
        assert s.getNume()=="Ioana";
    }

    /**
     * tests the getter for student's email
     */
    @Test
    public void getEmail() {
        assert s.getEmail()=="pop.ion@gmail.com";
    }

    /**
     * tests the setter for student's email
     */
    @Test
    public void setEmail() {
        s.setEmail("email");
        assert s.getEmail()=="email";
    }

    /**
     * tests the getter for student's professor
     */
    @Test
    public void getProfesor() {
        assert s.getProfesor()=="prof";
    }

    /**
     * tests the setter for student's professor
     */
    @Test
    public void setProfesor() {
        s.setProfesor("profa");
        assert s.getProfesor()=="profa";
    }
    /**
     * Tests for the toString
     */
    @Test
    public void tostring(){

        assert s.toString() != "";

    }
}