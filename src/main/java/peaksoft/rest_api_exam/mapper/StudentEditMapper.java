package peaksoft.rest_api_exam.mapper;

import org.springframework.stereotype.Component;
import peaksoft.rest_api_exam.dto.StudentRequest;
import peaksoft.rest_api_exam.model.Group;
import peaksoft.rest_api_exam.model.Student;
import peaksoft.rest_api_exam.model.enam.StudyFormat;
import peaksoft.rest_api_exam.repository.GroupRepository;

import java.time.LocalDate;
import java.util.Locale;

@Component
public class StudentEditMapper {

    private final GroupRepository groupRepository;

    public StudentEditMapper(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Student create(StudentRequest request){

        if (request == null){
            return null;
        }
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());


        if (request.getStudyFormat() != null) {
            if (request.getStudyFormat().toUpperCase(Locale.ROOT).contentEquals("ONLINE")) {
                student.setStudyFormat(StudyFormat.ONLINE);
            } else {
                student.setStudyFormat(StudyFormat.OFFLINE);
            }
        }

        if (request.getGroup() != null) {
            Group group = groupRepository.getGroupByGroupName(request.getGroup());

            group.setStudent(student);
            student.setGroup(group);

        }

        student.setCreated(LocalDate.now());
        student.setEnabled(true);

        return student;
    }


    public void update(Student student, StudentRequest request){
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());

        if (request.getStudyFormat() != null) {
            if (request.getStudyFormat().toUpperCase(Locale.ROOT).contentEquals("ONLINE")) {
                student.setStudyFormat(StudyFormat.ONLINE);
            } else {
                student.setStudyFormat(StudyFormat.OFFLINE);
            }
        }

        if (request.getGroup() != null) {
            Group group = groupRepository.getGroupByGroupName(request.getGroup());

            group.setStudent(student);
            student.setGroup(group);

        }
    }
}
