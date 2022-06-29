package peaksoft.rest_api_exam.mapper;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;
import peaksoft.rest_api_exam.dto.GroupResponse;
import peaksoft.rest_api_exam.model.Course;
import peaksoft.rest_api_exam.model.Group;
import peaksoft.rest_api_exam.model.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class GroupViewMapper {

    public GroupResponse viewGroup(Group group) {
        if (group == null) {
            return null;
        }

        GroupResponse response = new GroupResponse();
        response.setId(group.getId());
        response.setGroupName(group.getGroupName());
        response.setDateOfStart(group.getDateOfStart());
        response.setDateOfFinish(group.getDateOfFinish());

        try {
            for (Course course : group.getCourses()) {
                response.setCourse(course.getCourseName());
            }
        } catch (NullPointerException e) {
            System.out.println("courses of group is null");
        }

 try {
     for (Student student : group.getStudents()) {
         response.setStudent(student.getFirstName() + ", " + student.getLastName());
     }
 } catch (NullPointerException e) {
     System.out.println("students of group is null");
 }


        response.setEnabled(group.isEnabled());
        response.setCreated(group.getCreated());
        return response;
    }

    public List<GroupResponse> view(List<Group> groups){
        List<GroupResponse> responses = new ArrayList<>();
        for (Group group : groups){
            responses.add(viewGroup(group));
        }
        return responses;
    }
}
