package peaksoft.rest_api_exam.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.rest_api_exam.model.Company;

import java.util.List;

@Getter@Setter
public class CompanyResponseView {

    List<CompanyResponse> responses;
}
