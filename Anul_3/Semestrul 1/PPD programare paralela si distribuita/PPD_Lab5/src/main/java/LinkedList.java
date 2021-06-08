import java.util.ArrayList;
import java.util.List;

public class LinkedList {
    private Node root;

    public void addMonom(Monom monom) {
        if (root == null || root.getMonom().getPower() > monom.getPower()) {
            root = new Node(monom, root);
        } else {
            Node current = root;
            current.writeLock();

            while (current.getNext() != null && monom.getPower() >= current.getNext().getMonom().getPower()) {
                Node node = current.getNext();
                current.writeUnlock();
                current = node;
                current.writeLock();
            }

            if (monom.getPower() == current.getMonom().getPower()) {
                current.getMonom().setValue(current.getMonom().getValue() + monom.getValue());
            } else {
                current.setNext(new Node(monom, current.getNext()));
            }

            current.writeUnlock();
        }
    }

    public List<Monom> getAll() {
        List<Monom> monoms = new ArrayList<>();

        Node current = root;
        while (current != null) {
            monoms.add(current.getMonom());
            current = current.getNext();
        }

        return monoms;
    }
}
