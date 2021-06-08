package scs.ubb.map.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GradeDTO extends Entity<Integer> {
    private String studentName;
    private int homework;
    private float grade;
    private int week;
    private int deadline;
    private String feedback;
}
