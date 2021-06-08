import java.util.ArrayList;
import java.util.List;

public class MyLinkedList {

    private Node firstNode;

    public void add(Monom monom){

        if(firstNode == null || firstNode.getMonom().getExponent() > monom.getExponent()){
            firstNode = new Node(monom, firstNode);
        }
        else{
            Node currentNode = firstNode;
            currentNode.lock();

            while(currentNode.getNextNode() != null && monom.getExponent() >= currentNode.getNextNode().getMonom().getExponent()){
                Node node = currentNode.getNextNode();
                currentNode.unlock();
                currentNode = node;
                currentNode.lock();
            }

            if(monom.getExponent().equals(currentNode.getMonom().getExponent())){
                currentNode.getMonom().setCoefficient(currentNode.getMonom().getCoefficient() + monom.getCoefficient());
            }
            else{
                currentNode.setNextNode(new Node(monom, currentNode.getNextNode()));
            }

            currentNode.unlock();
        }
    }

    public List<Monom> allMonoms(){
        List<Monom> monoms = new ArrayList<>();
        Node currentNode = firstNode;
        while(currentNode != null){
            monoms.add(currentNode.getMonom());
            currentNode = currentNode.getNextNode();
        }
        return monoms;
    }

}
