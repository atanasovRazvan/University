package Repositories.xml;

import Domain.Entity;
import Domain.Validators.Validator;
import Exceptions.ValidationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import Repositories.memory.InMemoryRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Abstract repository for XML file persistence
 *
 * @param <ID> - type of the entities' id
 * @param <E>  - type of the entities
 */
public abstract class AbstractXMLRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> {
    private String fileName;
    private String tagName;

    AbstractXMLRepository(Validator<E> validator, String fileName, String tagName, boolean loadData) {
        super(validator);
        this.fileName = fileName;
        this.tagName = tagName;
        if (loadData) {
            loadFromXMLFile();
        }
    }

    /**
     * loads data from XML file to memory
     */
    void loadFromXMLFile() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(fileName));
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            NodeList nodeList = document.getElementsByTagName(tagName);

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node nNode = nodeList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    E entity = null;
                    try {
                        entity = readEntity(nNode);
                    } catch (Exception ignored) {
                    }
                    if (entity != null) {
                        super.save(entity);
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * updates the XML file
     */
    private void updateXMLFile() {
        DocumentBuilder builder;
        try {
            Path path = Paths.get(fileName);
            BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING);
            bw.close();
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.newDocument();
            Element rootElement = document.createElement(tagName + 's');
            document.appendChild(rootElement);
            super.findAll().forEach(x -> {
                Element element = createElementFromEntity(document, x);
                rootElement.appendChild(element);
            });
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult file = new StreamResult(new File(fileName));
            transformer.transform(domSource, file);
        } catch (ParserConfigurationException | TransformerException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * saves an entity to the repository
     *
     * @param entity entity must be not null
     * @return false if entity is saved and the entity if it already exists
     * @throws ValidationException      - if entity is not valid
     * @throws IllegalArgumentException - if entity is null
     */
    @Override
    public E save(E entity) throws ValidationException, IllegalArgumentException {
        E result = super.save(entity);
        if (result != null)
            return result;
        updateXMLFile();
        return null;
    }

    /**
     * deletes entity from repository
     *
     * @param id * id must be not null
     * @return the deleted entity or null if it does not exist
     * @throws IllegalArgumentException - if entity is null
     */
    @Override
    public E delete(ID id) throws IllegalArgumentException {
        E result = super.delete(id);
        if (result == null) {
            return null;
        }
        updateXMLFile();
        return result;
    }

    /**
     * updates an entity in the repository
     *
     * @param entity - entity must not be null
     * @return the old version of the entity or null if the entity does not exist in the repository
     * @throws IllegalArgumentException - if entity is null
     * @throws ValidationException      - if entity is not valid
     */
    @Override
    public E update(E entity) throws IllegalArgumentException, ValidationException {
        E result = super.update(entity);
        if (result == null) {
            return null;
        }
        updateXMLFile();
        return result;
    }

    /**
     * creates a node element
     *
     * @param doc     - xml document object
     * @param element - parent element
     * @param name    - name of node
     * @param value   - value of node
     * @return Node - the resulted node
     */
    static Node createNodeElement(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    /**
     * reads and parses entity from XML file
     *
     * @param node - the node to be parsed
     * @return E - entity
     */
    abstract E readEntity(Node node);

    /**
     * creates a XML node from an entity
     *
     * @param document - the XML document object
     * @param entity   - E
     * @return Element - the resulted Node Element
     */
    abstract Element createElementFromEntity(Document document, E entity);
}
