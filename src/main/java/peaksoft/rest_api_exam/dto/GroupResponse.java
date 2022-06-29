package peaksoft.rest_api_exam.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import peaksoft.rest_api_exam.model.Course;
import peaksoft.rest_api_exam.model.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GroupResponse {

    private Long id;
    private String groupName;
    private LocalDate dateOfStart;
    private LocalDate dateOfFinish;

    private LocalDate created;
    private boolean enabled;

    private List<String> courses = new ArrayList<>();
    private List<String> students = new ArrayList<>();

    public void setCourse(String courseName) {
        this.courses.add(courseName);
    }

    public void setStudent(String firstname) {
        this.students.add(firstname);
    }
}
