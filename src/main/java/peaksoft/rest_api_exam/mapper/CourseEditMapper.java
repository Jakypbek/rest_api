package peaksoft.rest_api_exam.mapper;

import org.springframework.stereotype.Component;
import peaksoft.rest_api_exam.dto.CourseRequest;
import peaksoft.rest_api_exam.model.Company;
import peaksoft.rest_api_exam.model.Course;
import peaksoft.rest_api_exam.repository.CompanyRepository;

import java.time.LocalDate;

@Component
public class CourseEditMapper {

    private final CompanyRepository companyRepository;

    public CourseEditMapper(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Course create(CourseRequest request){
        if (request == null){
            return null;
        }

        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setDuration(request.getDuration());

        if (request.getCompany() != null) {
            Company company = companyRepository.findCompanyByName(request.getCompany());
            company.setCourse(course);
            course.setCompany(company);
        }
        course.setCreated(LocalDate.now());
        course.setEnabled(true);
        return course;
    }

    public void update(Course course, CourseRequest request){
        course.setCourseName(request.getCourseName());
        course.setDuration(request.getDuration());
        if (request.getCompany() != null) {
            Company company = companyRepository.findCompanyByName(request.getCompany());
            company.setCourse(course);
            course.setCompany(company);
        }
    }
}
