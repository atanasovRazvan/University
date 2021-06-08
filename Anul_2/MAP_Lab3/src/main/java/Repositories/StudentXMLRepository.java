package Repositories;

import Entities.Student;
import Validators.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class StudentXMLRepository extends InFileRepository<Integer, Student> {

    public StudentXMLRepository(Validator<Student> v){
        super(v, "src/main/Student.xml");
    }

    @Override
    public Element createElementFromEntity(Document document, Student entity) {
        Element studentXML = document.createElement("student");
        studentXML.setAttribute("id", entity.getID().toString());
        studentXML.appendChild(createNodeElement(document, studentXML, "grupa", entity.getGrupa().toString()));
        studentXML.appendChild(createNodeElement(document, studentXML, "nume", entity.getNume()));
        studentXML.appendChild(createNodeElement(document, studentXML, "email", entity.getEmail()));
        studentXML.appendChild(createNodeElement(document, studentXML, "profesor", entity.getProfesor()));
        return studentXML;
    }

    @Override
    public Student createEntityFromElement(Element element) {
        Integer id = Integer.parseInt(element.getAttributes().item(0).getNodeValue());
        String nume = element.getElementsByTagName("nume").item(0).getTextContent();
        String grupa = element.getElementsByTagName("grupa").item(0).getTextContent();
        String email = element.getElementsByTagName("email").item(0).getTextContent();
        String prof = element.getElementsByTagName("profesor").item(0).getTextContent();
        return new Student(id, Integer.valueOf(grupa), nume, email, prof);
    }
}
