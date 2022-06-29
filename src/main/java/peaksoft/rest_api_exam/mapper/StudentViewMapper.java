package peaksoft.rest_api_exam.mapper;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;
import peaksoft.rest_api_exam.dto.StudentResponse;
import peaksoft.rest_api_exam.model.Group;
import peaksoft.rest_api_exam.model.Student;
import peaksoft.rest_api_exam.model.enam.StudyFormat;

import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentViewMapper {

    public StudentResponse viewStudent(Student student) {
        if (student == null) {
            return null;
        }

        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setEmail(student.getEmail());

        if (student.getStudyFormat() == StudyFormat.ONLINE) {
            response.setStudyFormat("Online");
        } else {
            response.setStudyFormat("Offline");
        }


        try {
            response.setGroup(student.getGroup().getGroupName());
        } catch (NullPointerException e) {
            System.out.println("group of student is null");
        }


        response.setEnabled(student.isEnabled());
        response.setCreated(student.getCreated());
        return response;
    }

    public List<StudentResponse> view(List<Student> students){
        List<StudentResponse> responses = new ArrayList<>();
        for (Student student : students){
            responses.add(viewStudent(student));
        }
        return responses;
    }

}
