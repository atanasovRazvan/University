package Repositories.xml;

import Domain.Student;
import Domain.Validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * student repository - XML file data persistence
 */
@Component
public class StudentXMLRepository extends AbstractXMLRepository<String, Student> {
    @Autowired
    public StudentXMLRepository(Validator<Student> validator, @Value("${data.catalog.xml.students}") String fileName) {
        super(validator, fileName, "student", true);
    }

    /**
     * parses a student from an XML node
     *
     * @param node - the node to be parsed
     * @return student - Student
     */
    @Override
    Student readEntity(Node node) {
        Element element = (Element) node;
        String id = element.getAttribute("id");
        String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
        String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
        int group = Integer.parseInt(element.getElementsByTagName("group").item(0).getTextContent());
        String email = element.getElementsByTagName("email").item(0).getTextContent();
        String coordinator = element.getElementsByTagName("coordinator").item(0).getTextContent();
        return new Student(id, firstName, lastName, group, email, coordinator);
    }

    /**
     * creates a Node element form a student
     *
     * @param document - the XML document object
     * @param student  - Student
     * @return studentXML - the created Node
     */
    @Override
    Element createElementFromEntity(Document document, Student student) {
        Element studentXML = document.createElement("student");
        studentXML.setAttribute("id", student.getId());
        studentXML.appendChild(createNodeElement(document, studentXML, "firstName", student.getFirstName()));
        studentXML.appendChild(createNodeElement(document, studentXML, "lastName", student.getLastName()));
        studentXML.appendChild(createNodeElement(document, studentXML, "group", String.valueOf(student.getGroup())));
        studentXML.appendChild(createNodeElement(document, studentXML, "email", student.getEmail()));
        studentXML.appendChild(createNodeElement(document, studentXML, "coordinator", student.getCoordinator()));
        return studentXML;
    }
}
