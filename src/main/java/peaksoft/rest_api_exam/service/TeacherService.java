package peaksoft.rest_api_exam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.rest_api_exam.dto.*;
import peaksoft.rest_api_exam.mapper.StudentViewMapper;
import peaksoft.rest_api_exam.mapper.TeacherEditMapper;
import peaksoft.rest_api_exam.mapper.TeacherViewMapper;
import peaksoft.rest_api_exam.model.Company;
import peaksoft.rest_api_exam.model.Group;
import peaksoft.rest_api_exam.model.Student;
import peaksoft.rest_api_exam.model.Teacher;
import peaksoft.rest_api_exam.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final StudentViewMapper studentViewMapper;
    private final TeacherRepository repository;
    private final TeacherEditMapper editMapper;
    private final TeacherViewMapper viewMapper;


    public TeacherResponse create(TeacherRequest teacherRequest) {
        Teacher teacher = editMapper.create(teacherRequest);
        repository.save(teacher);
        return viewMapper.viewTeacher(teacher);
    }

    public TeacherResponse update(long id, TeacherRequest teacherRequest) {
        Teacher teacher = repository.findById(id).get();
        editMapper.update(teacher, teacherRequest);
        return viewMapper.viewTeacher(repository.save(teacher));
    }

    public TeacherResponse findById(long id) {
        Teacher teacher = repository.findById(id).get();
        return viewMapper.viewTeacher(teacher);
    }

    public TeacherResponse deleteById(long id) {
        Teacher teacher = repository.getById(id);
        repository.delete(teacher);
        return viewMapper.viewTeacher(teacher);
    }

    public List<TeacherResponse> getAllTeachers() {
        return viewMapper.view(repository.findAll());
    }

    public List<StudentResponse> getAllStudentsOfThisTeacher(long teacherId) {

        List<Student> students = new ArrayList<>();
        Teacher teacher = repository.findById(teacherId).get();
        List<Group> groups = teacher.getCourse().getGroups();

        if (groups != null) {
            for (Group g : groups) {
                students.addAll(g.getStudents());
            }

            return studentViewMapper.view(students);
        }
        return null;
    }

    public TeacherResponseView getAllTeachersPagination(String text, int page, int size) {
        TeacherResponseView responseView = new TeacherResponseView();
        Pageable pageable = PageRequest.of(page, size);
        responseView.setResponses(view(search(text, pageable)));
        return responseView;
    }

    public List<TeacherResponse> view(List<Teacher> teachers) {
        List<TeacherResponse> responses = new ArrayList<>();
        for (Teacher teacher : teachers) {
            responses.add(viewMapper.viewTeacher(teacher));
        }
        return responses;
    }

    private List<Teacher> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return repository.searchAndPagination(text.toUpperCase(), pageable);
    }
}
