package Validator;
import Model.Student;


public class StudentValidator implements Validator<Student> {

    /**
     * the validator for a student
     * @param s - an instance of class Student
     * @throws ValidationException
     */
    public void validate(Student s) throws ValidationException{
        if(s.getID()== null)
            throw new ValidationException("Id invalid.\n");
        if(s.getGrupa() == null)
            throw new ValidationException("Grupa invalida.\n");
        if(s.getNume() == "")
            throw new ValidationException("Nume invalid.\n");
        if(s.getEmail() == "" || !s.getEmail().contains("@")  || !s.getEmail().contains(".com"))
            throw new ValidationException("Email invalid.\n");
        if(s.getProfesor() == "")
            throw new ValidationException("Profesor invalid.\n");
    }
}