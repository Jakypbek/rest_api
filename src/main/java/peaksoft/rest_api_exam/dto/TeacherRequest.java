package peaksoft.rest_api_exam.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequest {

    private String firstName;
    private String email;
    private String lastName;

    private String course;
}
