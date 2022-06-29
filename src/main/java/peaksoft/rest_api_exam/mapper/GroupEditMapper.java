package peaksoft.rest_api_exam.mapper;


import org.springframework.stereotype.Component;
import peaksoft.rest_api_exam.dto.GroupRequest;
import peaksoft.rest_api_exam.model.Course;
import peaksoft.rest_api_exam.model.Group;
import peaksoft.rest_api_exam.repository.CourseRepository;

import java.time.LocalDate;

@Component
public class GroupEditMapper {

    private final CourseRepository courseRepository;

    public GroupEditMapper(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Group create(GroupRequest request){

        if (request == null){
            return null;
        }
        Group group = new Group();
        group.setGroupName(request.getGroupName());
        group.setDateOfStart(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());

        if (request.getCourse() != null) {

            Course course = courseRepository.getCourseByCourseName(request.getCourse());

            course.setGroup(group);
            group.setCourse(course);

        }

        group.setCreated(LocalDate.now());
        group.setEnabled(true);

        return group;
    }


    public void update(Group group, GroupRequest request){
        group.setGroupName(request.getGroupName());
        group.setDateOfStart(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());
        if (request.getCourse() != null) {

            Course course = courseRepository.getCourseByCourseName(request.getCourse());

            course.setGroup(group);
            group.setCourse(course);
        }
    }
}
