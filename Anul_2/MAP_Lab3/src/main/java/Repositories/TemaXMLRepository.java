package Repositories;

import Entities.Tema;
import Validators.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TemaXMLRepository extends InFileRepository<Integer, Tema> {

    public TemaXMLRepository(Validator<Tema> v){
        super(v, "src/main/Tema.xml");
    }


    @Override
    public Element createElementFromEntity(Document document, Tema entity) {
        Element temaXML = document.createElement("tema");
        temaXML.setAttribute("nrTema", entity.getID().toString());
        temaXML.appendChild(createNodeElement(document, temaXML, "startWeek", entity.getSaptPredare().toString()));
        temaXML.appendChild(createNodeElement(document, temaXML, "deadline", entity.getDeadline().toString()));
        temaXML.appendChild(createNodeElement(document, temaXML, "descriere", entity.getDescriere()));
        return temaXML;
    }

    @Override
    public Tema createEntityFromElement(Element element) {
        Integer nrTema = Integer.parseInt(element.getAttributes().item(0).getNodeValue());
        String startWeek= element.getElementsByTagName("startWeek").item(0).getTextContent();
        String deadline = element.getElementsByTagName("deadline").item(0).getTextContent();
        String descriere = element.getElementsByTagName("descriere").item(0).getTextContent();
        Tema s = new Tema(nrTema, Integer.valueOf(startWeek), Integer.valueOf(deadline), descriere);
        return s;
    }
}
