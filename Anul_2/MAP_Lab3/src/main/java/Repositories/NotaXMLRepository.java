package Repositories;

import Entities.Nota;
import Validators.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class NotaXMLRepository extends InFileRepository<String, Nota> {

    public NotaXMLRepository(Validator<Nota> v){
        super(v, "src/main/Nota.xml");
    }

    @Override
    public Element createElementFromEntity(Document document, Nota entity) {
        Element notaXML = document.createElement("nota");
        notaXML.setAttribute("idNota", entity.getID());
        notaXML.appendChild(createNodeElement(document, notaXML, "idStudent", String.valueOf(entity.getStudentId())));
        notaXML.appendChild(createNodeElement(document, notaXML, "idTema", String.valueOf(entity.getTemaId())));
        notaXML.appendChild(createNodeElement(document, notaXML, "prof", entity.getProfesor()));
        notaXML.appendChild(createNodeElement(document, notaXML, "grade", String.valueOf(entity.get_grade())));
        notaXML.appendChild(createNodeElement(document, notaXML, "feedback", entity.getFeedback()));
        int pred;
        if(entity.isPredatLaTimp()) pred = 1;
        else pred = 0;
        notaXML.appendChild(createNodeElement(document, notaXML, "predataLaTimp", String.valueOf(pred)));
        return notaXML;
    }

    @Override
    public Nota createEntityFromElement(Element element) {
        String idNota = element.getAttributes().item(0).getNodeValue();
        String idStudent = element.getElementsByTagName("idStudent").item(0).getTextContent();
        String idTema = element.getElementsByTagName("idTema").item(0).getTextContent();
        String prof = element.getElementsByTagName("prof").item(0).getTextContent();
        String grade = element.getElementsByTagName("grade").item(0).getTextContent();
        String feedback = element.getElementsByTagName("feedback").item(0).getTextContent();
        String predataLaTimp = element.getElementsByTagName("predataLaTimp").item(0).getTextContent();
        int predat = Integer.parseInt(predataLaTimp);
        boolean pred = predat == 1;
        Nota n = new Nota(Integer.parseInt(idStudent), Integer.parseInt(idTema), prof, Integer.parseInt(grade), feedback);
        n.setPredatLaTimp(pred);
        return n;
    }
}
