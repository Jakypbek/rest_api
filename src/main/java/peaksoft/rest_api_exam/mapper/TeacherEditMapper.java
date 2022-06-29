package peaksoft.rest_api_exam.mapper;


import org.springframework.stereotype.Component;
import peaksoft.rest_api_exam.dto.TeacherRequest;
import peaksoft.rest_api_exam.model.Course;
import peaksoft.rest_api_exam.model.Teacher;
import peaksoft.rest_api_exam.repository.CourseRepository;

import java.time.LocalDate;

@Component
public class TeacherEditMapper {

    private final CourseRepository courseRepository;

    public TeacherEditMapper(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Teacher create(TeacherRequest request){

        if (request == null){
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());

        if (request.getCourse() != null) {
            Course course = courseRepository.getCourseByCourseName(request.getCourse());

            course.setTeacher(teacher);
            teacher.setCourse(course);

        }

        teacher.setCreated(LocalDate.now());
        teacher.setEnabled(true);

        return teacher;
    }


    public void update(Teacher teacher, TeacherRequest request){
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());
        if (request.getCourse() != null) {
            Course course = courseRepository.getCourseByCourseName(request.getCourse());

            course.setTeacher(teacher);
            teacher.setCourse(course);

        }
    }
}
