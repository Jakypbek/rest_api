package peaksoft.rest_api_exam.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.rest_api_exam.model.Company;


@Getter
@Setter
public class CourseRequest {

    private String courseName;
    private int duration;
    private String company;
}
