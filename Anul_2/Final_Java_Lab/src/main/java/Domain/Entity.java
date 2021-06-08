package Domain;

/**
 * The base entity class
 *
 * @param <ID> - the type of the entity's id
 */
public class Entity<ID> {
    private ID id;

    /**
     * returns the id of the entity
     *
     * @return id - ID
     */
    public ID getId() {
        return id;
    }

    /**
     * sets the id of the entity
     *
     * @param id - ID
     */
    public void setId(ID id) {
        this.id = id;
    }

    /**
     * returns the file string representation of the entity
     *
     * @return fileString - String
     */
    public String toFileString() {
        return this.toString();
    }
}