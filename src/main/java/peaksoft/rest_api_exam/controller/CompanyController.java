package peaksoft.rest_api_exam.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.rest_api_exam.dto.*;
import peaksoft.rest_api_exam.service.CompanyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Company API", description ="User with role admin can add,update, delete or get all companies")
public class CompanyController {

    private final CompanyService service;

    @PostMapping
    @Operation(summary = "create student", description = "we can create student")
    public CompanyResponse create(@RequestBody CompanyRequest request) {
        return service.create(request);
    }

    @PutMapping("{id}")
    @Operation(summary = "update student", description = "we can update student by id")
    public CompanyResponse update(@PathVariable long id, @RequestBody CompanyRequest request) {
        return service.update(id, request);
    }

    @GetMapping("{id}")
    @Operation(summary = "find student", description = "we can find student by id")
    public CompanyResponse findById(@PathVariable long id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete student", description = "we can delete student by id")
    public CompanyResponse delete(@PathVariable long id) {
        return service.deleteById(id);
    }

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return service.getAllCompanies();
    }

    @GetMapping("students/{companyId}")
    public List<StudentResponse> allStudentsInThisCompany(@PathVariable long companyId) {
        return service.getAllStudentsInThisCompany(companyId);
    }


//    @GetMapping
//    @Operation(summary = "Get allCompaniesAndSearch", description = "We can get all companies and search")
//    public CompanyResponseView getAllCompanies(@RequestParam(name = "text", required = false) String text,
//                                              @RequestParam int page,
//                                              @RequestParam int size) {
//        return service.getAllCompaniesPagination(text, page, size);
//    }

}
