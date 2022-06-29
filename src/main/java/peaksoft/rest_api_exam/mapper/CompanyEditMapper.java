package peaksoft.rest_api_exam.mapper;

import org.springframework.stereotype.Component;
import peaksoft.rest_api_exam.dto.CompanyRequest;
import peaksoft.rest_api_exam.model.Company;
import peaksoft.rest_api_exam.model.Course;
import peaksoft.rest_api_exam.repository.CourseRepository;

import java.time.LocalDate;
import java.util.Locale;

@Component
public class CompanyEditMapper {

    private final CourseRepository courseRepository;

    public CompanyEditMapper(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Company create(CompanyRequest request){

        if (request == null){
            return null;
        }

        Company company = new Company();
        company.setCompanyName(request.getCompanyName());
        company.setLocatedCountry(request.getLocatedCountry());


        if (request.getCourse() != null) {

//            Long a = null;
//
//            for (Course c : courseRepository.findAll()) {
//                if (c.getCourseName().toLowerCase(Locale.ROOT).contentEquals(request.getCourse().toLowerCase(Locale.ROOT))) {
//                    a = c.getId();
//                }
//            }
//
//            Course course = courseRepository.findById(a).get();

            Course course = courseRepository.getCourseByCourseName(request.getCourse());

            course.setCompany(company);
            company.setCourse(course);

        }


        company.setCreated(LocalDate.now());
        company.setEnabled(true);

        return company;
    }


    public void update(Company company, CompanyRequest request){

        company.setCompanyName(request.getCompanyName());
        company.setLocatedCountry(request.getLocatedCountry());
        if (request.getCourse() != null) {

//            Long a = null;
//
//            for (Course c : courseRepository.findAll()) {
//                if (c.getCourseName().toLowerCase(Locale.ROOT).contentEquals(request.getCourse().toLowerCase(Locale.ROOT))) {
//                    a = c.getId();
//                }
//            }
//
//            Course course = courseRepository.findById(a).get();

            Course course = courseRepository.getCourseByCourseName(request.getCourse());

            course.setCompany(company);
            company.setCourse(course);
        }
    }
}
