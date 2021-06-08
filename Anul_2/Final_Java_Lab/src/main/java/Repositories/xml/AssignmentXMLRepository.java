package Repositories.xml;

import Domain.Assignment;

import Domain.Validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Assignment repository - XML file data persistence
 */
@Component
public class AssignmentXMLRepository extends AbstractXMLRepository<Integer, Assignment> {
    @Autowired
    public AssignmentXMLRepository(Validator<Assignment> validator, @Value("${data.catalog.xml.assignments}") String fileName) {
        super(validator, fileName, "assignment", true);
    }

    /**
     * parses assignment from XML file
     *
     * @param node - the node to be parsed
     * @return assignment - Assignment
     */
    @Override
    Assignment readEntity(Node node) {
        Element element = (Element) node;
        int id = Integer.parseInt(element.getAttribute("id"));
        String description = element.getElementsByTagName("description").item(0).getTextContent();
        int startWeek = Integer.parseInt(element.getElementsByTagName("startWeek").item(0).getTextContent());
        int deadlineWeek = Integer.parseInt(element.getElementsByTagName("deadlineWeek").item(0).getTextContent());
        Assignment assignment = new Assignment(id, description, deadlineWeek);
        assignment.setStartWeek(startWeek);
        return assignment;
    }

    /**
     * @param document   - the XML document object
     * @param assignment - Assignment
     * @return assignmentXML - the XML node
     */
    @Override
    Element createElementFromEntity(Document document, Assignment assignment) {
        Element assignmentXML = document.createElement("assignment");
        assignmentXML.setAttribute("id", String.valueOf(assignment.getId()));
        assignmentXML.appendChild(createNodeElement(document, assignmentXML, "description", assignment.getDescription()));
        assignmentXML.appendChild(createNodeElement(document, assignmentXML, "startWeek", String.valueOf(assignment.getStartWeek())));
        assignmentXML.appendChild(createNodeElement(document, assignmentXML, "deadlineWeek", String.valueOf(assignment.getDeadlineWeek())));
        return assignmentXML;
    }
}
