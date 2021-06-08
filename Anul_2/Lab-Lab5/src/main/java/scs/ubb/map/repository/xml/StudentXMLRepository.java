package scs.ubb.map.repository.xml;

import org.w3c.dom.Element;
import scs.ubb.map.domain.Entity;
import scs.ubb.map.validators.Validator;

import javax.xml.parsers.ParserConfigurationException;

public class StudentXMLRepository extends InXMLRepository {

    public StudentXMLRepository(Validator validator, String fileName) throws ParserConfigurationException {
        super(validator, fileName);
    }

    @Override
    public String getRootName() {
        return null;
    }

    @Override
    public Element getElement(Entity entity) {

    }
}
