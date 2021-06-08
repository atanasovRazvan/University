package scs.ubb.map.repository.files.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

@AllArgsConstructor
public abstract class JSONRepository<E> {
    private String jsonFilesPath;

    public void write(E entity) {
        try (FileWriter file = new FileWriter(jsonFilesPath + getFileName(entity) + ".json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(file, getObject(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract JSONObject getObject(E entity);

    protected abstract String getFileName(E entity);
}
