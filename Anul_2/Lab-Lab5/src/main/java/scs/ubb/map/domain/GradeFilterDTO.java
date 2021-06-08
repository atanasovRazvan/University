package scs.ubb.map.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class GradeFilterDTO {
    private String studentFirstName;
    private String studentLastName;
    private float grade;
    private LocalDate date;
    private int homeworkId;
    private int week;
}
