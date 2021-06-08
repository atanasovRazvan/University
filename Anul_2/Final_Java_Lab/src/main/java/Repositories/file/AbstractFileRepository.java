package Repositories.file;

import Domain.Entity;
import Domain.Validators.Validator;
import Exceptions.ValidationException;
import Repositories.memory.InMemoryRepository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

/**
 * Repository class for entity storage with file data persistence
 *
 * @param <ID> - type of the id of the entity
 * @param <E>  - type of the entity
 */
public abstract class AbstractFileRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> {
    private String fileName;

    AbstractFileRepository(Validator<E> validator, String fileName, boolean loadData) {
        super(validator);
        this.fileName = fileName;
        if (loadData) {
            loadDataFromFile();
        }
    }

    /**
     * loads data in the repository from the file
     */
    void loadDataFromFile() {
        Path p = Paths.get(fileName);
        try {
            Stream<String> lines = Files.lines(p);
            lines.forEach(line -> {
                E entity = parseEntity(line);
                super.save(entity);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * parses the entity stored in the file
     *
     * @param lineToParse - the entity to be parsed - String
     * @return entity - E extends Entity
     */
    abstract E parseEntity(String lineToParse);

    @Override
    public E save(E entity) throws ValidationException, IllegalArgumentException {
        E result = super.save(entity);
        if (result != null)
            return result;
        updateFile();
        return null;
    }

    /**
     * updates the file with the current data stored in memory
     */
    private void updateFile() {
        try (BufferedWriter bf = Files.newBufferedWriter(Paths.get(fileName), StandardOpenOption.TRUNCATE_EXISTING)) {
            findAll().forEach(ent -> {
                try {
                    bf.write(ent.toFileString());
                    bf.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E delete(ID id) throws IllegalArgumentException {
        E result = super.delete(id);
        if (result == null) {
            return null;
        }
        updateFile();
        return result;
    }

    @Override
    public E update(E entity) throws IllegalArgumentException, ValidationException {
        E result = super.update(entity);
        if (result == null) {
            return null;
        }
        updateFile();
        return result;
    }
}