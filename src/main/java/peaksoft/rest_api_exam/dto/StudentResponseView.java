package peaksoft.rest_api_exam.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class StudentResponseView {

    List<StudentResponse> responses;
}