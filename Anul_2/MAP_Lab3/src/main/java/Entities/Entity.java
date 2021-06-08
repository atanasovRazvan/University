package Entities;

/**
 *
 * @param <ID>
 */
public interface Entity<ID> {
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
