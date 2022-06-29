package peaksoft.rest_api_exam.mapper;

import org.springframework.stereotype.Component;
import peaksoft.rest_api_exam.dto.CompanyResponse;
import peaksoft.rest_api_exam.model.Company;
import peaksoft.rest_api_exam.model.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyViewMapper {

    public CompanyResponse viewCompany(Company company) {
        if (company == null) {
            return null;
        }

        CompanyResponse response = new CompanyResponse();
        response.setId(company.getId());
        response.setCompanyName(company.getCompanyName());
        response.setLocatedCountry(company.getLocatedCountry());


        for (Course course : company.getCourses()) {
            response.setCourse(course.getCourseName());
        }

        response.setEnabled(company.isEnabled());
        response.setCreated(company.getCreated());
        return response;
    }

    public List<CompanyResponse> view(List<Company> companies){
        List<CompanyResponse> responses = new ArrayList<>();
        for (Company company : companies){
            responses.add(viewCompany(company));
        }
        return responses;
    }
}
