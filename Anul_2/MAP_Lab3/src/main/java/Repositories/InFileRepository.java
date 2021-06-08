package Repositories;

import Entities.*;
import Validators.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public abstract class InFileRepository <ID, E extends Entity<ID>> extends  AbstractCrudRepository<ID,E> {

    private String fileName;

    public InFileRepository(Validator<E> validator, String fileName) {
        super(validator);
        this.fileName=fileName;
        loadData();
    }

    private void loadData() {
        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(this.fileName);

            Element root = document.getDocumentElement();
            NodeList children = root.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node entityElement = children.item(i);
                if (entityElement instanceof Element) {
                    E entity = createEntityFromElement((Element) entityElement);
                    super.save(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(){
        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            Element root  = document.createElement("inbox");
            document.appendChild(root);
            super.findAll().forEach(e->{
                Element elem = createElementFromEntity(document,e);
                root.appendChild(elem);
            });

            //write Document to file
            Transformer transformer = TransformerFactory.
                    newInstance().newTransformer();
            transformer.transform(new DOMSource(document),
                    new StreamResult(fileName));

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public abstract Element createElementFromEntity(Document document, E entity);

    public abstract E createEntityFromElement(Element element);

    @Override
    public E save(E entity) throws ValidationException {
        E stuff = super.save(entity);
        if (stuff == null){
            writeToFile();
        }
        return stuff;
    }

    @Override
    public E delete(ID id) {
        E temp =super.delete(id);
        if(temp != null)
            writeToFile();
        return temp;
    }

    @Override
    public E update(E entity) {
        E temp = super.update(entity);
        if(temp != null){
            writeToFile();
        }
        return temp;
    }

    static Node createNodeElement(Document doc, Element element, String name, String value){

        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;

    }
}