package peaksoft.rest_api_exam.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.rest_api_exam.model.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CompanyResponse {

    private Long id;
    private String companyName;
    private String locatedCountry;
    private LocalDate created;
    private boolean enabled;
    private List<String> courses = new ArrayList<>();

    public void setCourse(String courseName) {
        this.courses.add(courseName);
    }
}
