package peaksoft.rest_api_exam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.rest_api_exam.dto.StudentRequest;
import peaksoft.rest_api_exam.dto.StudentResponse;
import peaksoft.rest_api_exam.dto.StudentResponseView;
import peaksoft.rest_api_exam.mapper.StudentEditMapper;
import peaksoft.rest_api_exam.mapper.StudentViewMapper;
import peaksoft.rest_api_exam.model.Student;
import peaksoft.rest_api_exam.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {



    private final StudentRepository repository;
    private final StudentEditMapper editMapper;
    private final StudentViewMapper viewMapper;


    public StudentResponse create(StudentRequest studentRequest) {
        Student student = editMapper.create(studentRequest);
        repository.save(student);
        return viewMapper.viewStudent(student);
    }

    public StudentResponse update(long id, StudentRequest studentRequest) {
        Student student = repository.findById(id).get();
        editMapper.update(student, studentRequest);
        return viewMapper.viewStudent(repository.save(student));
    }

    public StudentResponse findById(long id) {
        Student student = repository.findById(id).get();
        return viewMapper.viewStudent(student);
    }

    public StudentResponse deleteById(long id) {
        Student student = repository.getById(id);
        repository.delete(student);
        return viewMapper.viewStudent(student);
    }

    public List<StudentResponse> getAllStudents() {
        return viewMapper.view(repository.findAll());
    }

    public StudentResponseView getAllStudentsPagination(String text, int page, int size) {
        StudentResponseView responseView = new StudentResponseView();
        Pageable pageable = PageRequest.of(page, size);
        responseView.setResponses(view(search(text, pageable)));
        return responseView;
    }

    public List<StudentResponse> view(List<Student> students) {
        List<StudentResponse> responses = new ArrayList<>();
        for (Student student : students) {
            responses.add(viewMapper.viewStudent(student));
        }
        return responses;
    }

    private List<Student> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return repository.searchAndPagination(text.toUpperCase(), pageable);
    }


}