package Entities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestNota {
    private Nota s = new Nota(1,1, "prof",7,"feedback");

    /**
     * tests the setter for Nota's ID
     */
    @Test
    public void setID() {
        s.setID("22");
        assert  s.getID()=="22";
    }

    /**
     * tests the getter for Nota's grade
     */
    @Test
    public void setGrade() {
        s.setGrade(5);
        assert s.get_grade()==5;
    }

    /**
     * tests the setter for Nota's week
     */
    @Test
    public void setSaptPredata() {
        s.setSaptPredata(5);
        assert s.getSaptPredata()==5;
    }

    /**
     * tests the setter for the Feedback
     */
    @Test
    public void setFeedback(){
        s.setFeedback("Pula");
        assert s.getFeedback()=="Pula";
    }

    /**
     * tests the setter for the Professor
     */
    @Test
    public void setProfesor(){
        s.setProfesor("Mada");
        assert s.getProfesor() == "Mada";
    }
}