package peaksoft.rest_api_exam.mapper;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;
import peaksoft.rest_api_exam.dto.CourseResponse;
import peaksoft.rest_api_exam.model.Company;
import peaksoft.rest_api_exam.model.Course;
import peaksoft.rest_api_exam.model.Group;
import peaksoft.rest_api_exam.model.Teacher;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CourseViewMapper {

    public CourseResponse viewCourse(Course course) {
        if (course == null) {
            return null;
        }

        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setCourseName(course.getCourseName());
        response.setDuration(course.getDuration());
        try {
            response.setCompany(course.getCompany().getCompanyName());
        } catch (NullPointerException e) {
            System.out.println("course is null");
        }

        try {
            for (Group g : course.getGroups()) {
                response.setGroup(g.getGroupName());
            }
        } catch (NullPointerException e) {
            System.out.println("group is null");
        }

try {
    response.setTeacher(course.getTeacher().getFirstName() + ", " + course.getTeacher().getLastName());
} catch (NullPointerException e) {
    System.out.println("teacher is null");
}

        response.setEnabled(course.isEnabled());
        response.setCreated(course.getCreated());
        return response;
    }

    public List<CourseResponse> view(List<Course> courses){
        List<CourseResponse> responses = new ArrayList<>();
        for (Course course : courses){
            responses.add(viewCourse(course));
        }
        return responses;
    }
}
