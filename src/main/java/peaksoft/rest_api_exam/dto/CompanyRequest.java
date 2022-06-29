package peaksoft.rest_api_exam.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.rest_api_exam.model.Course;

@Getter
@Setter
public class CompanyRequest {

    private String companyName;
    private String locatedCountry;
    private String course;
}
