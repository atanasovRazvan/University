package scs.ubb.map.repository.files;

import scs.ubb.map.domain.Entity;
import scs.ubb.map.repository.InMemoryRepository;
import scs.ubb.map.validators.ValidationException;
import scs.ubb.map.validators.Validator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public abstract class InFileRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> {
    private String fileName;

    public InFileRepository(Validator<E> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    @Override
    public E save(E entity) throws ValidationException {
        E savedEntity = super.save(entity);
        if (savedEntity == null) {
            writeToFile(entity);
        }
        return savedEntity;
    }

    public E delete(ID id) {
        E savedEntity = super.delete(id);
        if (savedEntity != null) {
            rewriteFile();
        }

        return savedEntity;
    }

    @Override
    public E update(E entity) {
        E savedEntity = super.update(entity);
        if (savedEntity != null) {
            rewriteFile();
        }

        return savedEntity;
    }

    public void loadData() {
        Path path = Paths.get(fileName);

        try {
            List<String> lines = Files.readAllLines(path);
            lines.forEach(line -> {
                E entity = getEntity(line);
                super.save(entity);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(E entity) {
        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(getEntityString(entity));
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rewriteFile() {
        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.WRITE)) {
            super.findAll().forEach(entity -> {
                try {
                    bufferedWriter.write(getEntityString(entity));
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    abstract E getEntity(String line);

    abstract String getEntityString(E entity);
}
