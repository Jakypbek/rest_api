package peaksoft.rest_api_exam.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import peaksoft.rest_api_exam.model.Company;
import peaksoft.rest_api_exam.model.Group;
import peaksoft.rest_api_exam.model.Teacher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseResponse {

    private Long id;
    private String courseName;
    private int duration;
    private LocalDate created;
    private boolean enabled;
    private String company;
    private List<String> groups = new ArrayList<>();
    private String teacher;

    public void setGroup(String groupName) {
        this.groups.add(groupName);
    }
}
