package peaksoft.rest_api_exam.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.rest_api_exam.model.Course;

import java.time.LocalDate;

@Getter
@Setter
public class TeacherResponse {

    private Long id;
    private String firstName;
    private String email;
    private String lastName;

    private LocalDate created;
    private boolean enabled;

    private String course;
}
