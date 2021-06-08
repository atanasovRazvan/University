package Model;

import org.junit.Test;

class StudentTest {
    private Student s = new Student(1,226, "Pop Ion","pop.ion@gmail.com","prof");

    /**
     * tests the getter for student's ID
     */
    @Test
    void getID() {
        assert s.getID() == 1;
    }

    /**
     * tests the setter for student's ID
     */
    @Test
    void setID() {
        s.setID(2);
        assert  s.getID()==2;
    }

    /**
     * tests the getter for student's group
     */
    @Test
    void getGrupa() {
        assert s.getGrupa()==226;
    }

    /**
     * tests the setter for student's group
     */
    @Test
    void setGrupa() {
        s.setGrupa(221);
        assert s.getGrupa()==221;
    }

    /**
     * tests the getter for student's name
     */
    @Test
    void getNume() {
        assert s.getNume()=="Pop Ion";
    }

    /**
     * tests the setter for student's name
     */
    @Test
    void setNume() {
        s.setNume("Ioana");
        assert s.getNume()=="Ioana";
    }

    /**
     * tests the getter for student's email
     */
    @Test
    void getEmail() {
        assert s.getEmail()=="pop.ion@gmail.com";
    }

    /**
     * tests the setter for student's email
     */
    @Test
    void setEmail() {
        s.setEmail("email");
        assert s.getEmail()=="email";
    }

    /**
     * tests the getter for student's professor
     */
    @Test
    void getProfesor() {
        assert s.getProfesor()=="prof";
    }

    /**
     * tests the setter for student's professor
     */
    @Test
    void setProfesor() {
        s.setProfesor("profa");
        assert s.getProfesor()=="profa";
    }
}