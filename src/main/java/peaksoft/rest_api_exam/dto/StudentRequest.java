package peaksoft.rest_api_exam.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import peaksoft.rest_api_exam.model.Group;
import peaksoft.rest_api_exam.model.enam.StudyFormat;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
public class StudentRequest {

    private String firstName;
    private String email;
    private String lastName;

    private String studyFormat;
    private String group;
}
