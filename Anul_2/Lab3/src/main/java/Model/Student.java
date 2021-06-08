package Model;


public class Student implements HasID<Integer> {
    private Integer idStudent, grupa;
    private String nume, email, profesor;

    /**
     * Constructor for class Student
     * @param idStudent - integer, the ID of the student, must be not null
     * @param grupa - integer, the group of the student, must be not null
     * @param nume - string, the name of the student, must be not null
     * @param email - string, the email of the student, must be not null and contain "@" and ".com"
     * @param profesor - string, the name of the professor of the student, must not be null
     */
    public Student(Integer idStudent, Integer grupa, String nume, String email, String profesor) {
        this.idStudent = idStudent;
        this.grupa = grupa;
        this.nume = nume;
        this.email = email;
        this.profesor = profesor;
    }

    /**
     * getter for ID
     * @return the student's ID - integer not null
     */
    @Override
    public Integer getID() {
        return idStudent;
    }

    /**
     * setter for ID
     * @param idStudent - integer, the ID of the student, must be not null
     */
    @Override
    public void setID(Integer idStudent) {
        this.idStudent = idStudent;
    }

    /**
     * getter for group
     * @return the student's group - integer not null
     */
    public Integer getGrupa() {
        return grupa;
    }

    /**
     * setter for group
     * @param grupa - integer, the group of the student, must be not null
     */
    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    /**
     * getter for name
     * @return the student's name - string not null
     */
    public String getNume() {
        return nume;
    }

    /**
     * setter for name
     * @param nume - string, the name of the student, must be not null
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * getter for email
     * @return the student's email - string not null, contains "@" and ".com"
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for email
     * @param email - string, the email of the student, must be not null and contain "@" and ".com"
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter for professor
     * @return the student's professor - string not null
     */
    public String getProfesor() {
        return profesor;
    }

    /**
     * setter for professor
     * @param profesor - string, the name of the professor of the student, must not be null
     */
    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    /**
     *
     * @return the string for a student
     */
    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", grupa=" + grupa +
                ", nume=" + nume +
                ", email=" + email +
                ", profesor=" + profesor +
                '}' + '\n';
    }
}
