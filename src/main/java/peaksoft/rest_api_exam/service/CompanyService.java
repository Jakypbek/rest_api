package peaksoft.rest_api_exam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.rest_api_exam.dto.CompanyRequest;
import peaksoft.rest_api_exam.dto.CompanyResponse;
import peaksoft.rest_api_exam.dto.CompanyResponseView;
import peaksoft.rest_api_exam.dto.StudentResponse;
import peaksoft.rest_api_exam.mapper.CompanyEditMapper;
import peaksoft.rest_api_exam.mapper.CompanyViewMapper;
import peaksoft.rest_api_exam.mapper.StudentViewMapper;
import peaksoft.rest_api_exam.model.Company;
import peaksoft.rest_api_exam.model.Course;
import peaksoft.rest_api_exam.model.Group;
import peaksoft.rest_api_exam.model.Student;
import peaksoft.rest_api_exam.repository.CompanyRepository;
import peaksoft.rest_api_exam.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;
    private final CourseRepository courseRepository;
    private final CompanyEditMapper editMapper;
    private final CompanyViewMapper viewMapper;
    private final StudentViewMapper studentViewMapper;


    public CompanyResponse create(CompanyRequest companyRequest) {
        Company company = editMapper.create(companyRequest);
        repository.save(company);
        return viewMapper.viewCompany(company);
    }

    public CompanyResponse update(long id, CompanyRequest companyRequest) {
        Company company = repository.findById(id).get();
        editMapper.update(company, companyRequest);
        return viewMapper.viewCompany(repository.save(company));
    }

    public CompanyResponse findById(long id) {
        Company company = repository.findById(id).get();
        return viewMapper.viewCompany(company);
    }

    public CompanyResponse deleteById(long id) {
        Company company = repository.findById(id).get();
        repository.delete(company);
        return viewMapper.viewCompany(company);
    }

    public List<CompanyResponse> getAllCompanies() {
        return viewMapper.view(repository.findAll());
    }

    public List<StudentResponse> getAllStudentsInThisCompany(Long companyId) {

        List<Student> students = new ArrayList<>();
        List<Course> courses = courseRepository.findAllCoursesByCompanyId(companyId);
        List<Group> groups = new ArrayList<>();

        if (courses != null) {
            for (Course course : courses) {
                groups.addAll(course.getGroups());
            }
            for (Group g : groups) {
                students.addAll(g.getStudents());
            }

            return studentViewMapper.view(students);
        }
        return null;
    }

    public CompanyResponseView getAllCompaniesPagination(String text, int page, int size) {
        CompanyResponseView responseView = new CompanyResponseView();
        Pageable pageable = PageRequest.of(page, size);
        responseView.setResponses(view(search(text, pageable)));
        return responseView;
    }

    public List<CompanyResponse> view(List<Company> companies) {
        List<CompanyResponse> responses = new ArrayList<>();
        for (Company company : companies) {
            responses.add(viewMapper.viewCompany(company));
        }
        return responses;
    }

    private List<Company> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return repository.searchAndPagination(text.toUpperCase(), pageable);
    }
}
