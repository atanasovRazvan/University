package scs.ubb.map.repository.files.json;

import org.json.simple.JSONObject;
import scs.ubb.map.domain.GradeDTO;

public class GradeJSONRepository extends JSONRepository<GradeDTO> {
    public GradeJSONRepository(String jsonFilesPath) {
        super(jsonFilesPath);
    }

    @Override
    protected JSONObject getObject(GradeDTO entity) {
        JSONObject object = new JSONObject();
        object.put("Feedback", entity.getFeedback());
        object.put("Deadline", entity.getDeadline());
        object.put("Presentation", entity.getWeek());
        object.put("Grade", entity.getGrade());
        object.put("Homework", entity.getHomework());

        return object;
    }

    @Override
    protected String getFileName(GradeDTO gradeDTO) {
        return gradeDTO.getStudentName();
    }
}
