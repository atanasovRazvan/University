package scs.ubb.map.services.service;

import scs.ubb.map.repository.files.json.GradeJSONRepository;

public class GradeDTOService extends GradeJSONRepository {
    public GradeDTOService(String jsonFilesPath) {
        super(jsonFilesPath);
    }
}