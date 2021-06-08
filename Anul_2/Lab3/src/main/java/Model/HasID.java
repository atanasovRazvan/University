package Model;


/**
 *
 * @param <ID>
 */
public interface HasID<ID> {
    /**
     * getter for ID
     * @return the ID
     */
    ID getID();

    /**
     * setter for ID
     * @param id - type <ID>
     */
    void setID(ID id);
}
