package peaksoft.rest_api_exam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.rest_api_exam.dto.*;
import peaksoft.rest_api_exam.mapper.CompanyEditMapper;
import peaksoft.rest_api_exam.mapper.CompanyViewMapper;
import peaksoft.rest_api_exam.mapper.CourseEditMapper;
import peaksoft.rest_api_exam.mapper.CourseViewMapper;
import peaksoft.rest_api_exam.model.Company;
import peaksoft.rest_api_exam.model.Course;
import peaksoft.rest_api_exam.repository.CompanyRepository;
import peaksoft.rest_api_exam.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;
    private final CourseEditMapper editMapper;
    private final CourseViewMapper viewMapper;

    public CourseResponse create(CourseRequest courseRequest) {
        Course course = editMapper.create(courseRequest);
        repository.save(course);
        return viewMapper.viewCourse(course);
    }

    public CourseResponse update(long id, CourseRequest courseRequest) {
        Course course = repository.findById(id).get();
        editMapper.update(course, courseRequest);
        return viewMapper.viewCourse(repository.save(course));
    }

    public CourseResponse findById(long id) {
        Course course = repository.findById(id).get();
        return viewMapper.viewCourse(course);
    }

    public CourseResponse deleteById(long id) {
        Course course = repository.getById(id);
        repository.delete(course);
        return viewMapper.viewCourse(course);
    }

    public List<CourseResponse> getAllCourses() {
        return viewMapper.view(repository.findAll());
    }

    public CourseResponseView getAllCoursesPagination(String text, int page, int size) {
        CourseResponseView responseView = new CourseResponseView();
        Pageable pageable = PageRequest.of(page-1, size);
        responseView.setResponses(view(search(text, pageable)));
        return responseView;
    }

    public List<CourseResponse> view(List<Course> courses) {
        List<CourseResponse> responses = new ArrayList<>();
        for (Course course : courses) {
            responses.add(viewMapper.viewCourse(course));
        }
        return responses;
    }

    private List<Course> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return repository.searchAndPagination(text.toUpperCase(), pageable);
    }
}
