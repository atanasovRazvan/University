package scs.ubb.map.repository.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import scs.ubb.map.domain.Entity;
import scs.ubb.map.repository.InMemoryRepository;
import scs.ubb.map.validators.Validator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public abstract class InXMLRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> {
    private String fileName;
    private Document document;
    private Element rootElement;

    public InXMLRepository(Validator<E> validator, String fileName) throws ParserConfigurationException {
        super(validator);
        this.fileName = fileName;

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        document = dBuilder.newDocument();
        rootElement = document.createElement(getRootName());
    }

    @Override
    public E save(E entity) {
        E exitEntity = super.save(entity);
        rootElement.appendChild(getElement(entity));

        try {
            writeFile();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return exitEntity;
    }

    private void writeFile() throws TransformerException {
        StreamResult file = new StreamResult(new File(this.fileName));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        transformer.transform(source, file);
    }

    public abstract String getRootName();

    public abstract Element getElement(E entity);
}
