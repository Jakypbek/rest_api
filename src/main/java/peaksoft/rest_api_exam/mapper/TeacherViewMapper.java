package peaksoft.rest_api_exam.mapper;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;
import peaksoft.rest_api_exam.dto.TeacherResponse;
import peaksoft.rest_api_exam.model.Course;
import peaksoft.rest_api_exam.model.Teacher;

import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherViewMapper {

    public TeacherResponse viewTeacher(Teacher teacher) {
        if (teacher == null) {
            return null;
        }

        TeacherResponse response = new TeacherResponse();
        response.setId(teacher.getId());
        response.setFirstName(teacher.getFirstName());
        response.setLastName(teacher.getLastName());
        response.setEmail(teacher.getEmail());

        try {
            response.setCourse(teacher.getCourse().getCourseName());
        } catch (NullPointerException e) {
            System.out.println("courses of a teacher is null");
        }


        response.setEnabled(teacher.isEnabled());
        response.setCreated(teacher.getCreated());
        return response;
    }

    public List<TeacherResponse> view(List<Teacher> teachers){
        List<TeacherResponse> responses = new ArrayList<>();
        for (Teacher teacher : teachers){
            responses.add(viewTeacher(teacher));
        }
        return responses;
    }
}
