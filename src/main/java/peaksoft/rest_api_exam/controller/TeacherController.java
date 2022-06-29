package peaksoft.rest_api_exam.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.rest_api_exam.dto.*;
import peaksoft.rest_api_exam.service.TeacherService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
@PreAuthorize("hasAuthority('HR')")
@Tag(name = "Teacher API", description ="User with role admin can add,update, delete or get all teachers")
public class TeacherController {

    private final TeacherService service;

    @PostMapping
    @Operation(summary = "create student", description = "we can create student")
    public TeacherResponse create(@RequestBody TeacherRequest request) {
        return service.create(request);
    }

    @PutMapping("{id}")
    @Operation(summary = "update student", description = "we can update student by id")
    public TeacherResponse update(@PathVariable long id, @RequestBody TeacherRequest request) {
        return service.update(id, request);
    }

    @GetMapping("{id}")
    @Operation(summary = "find student", description = "we can find student by id")
    public TeacherResponse findById(@PathVariable long id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete student", description = "we can delete student by id")
    public TeacherResponse delete(@PathVariable long id) {
        return service.deleteById(id);
    }

    @GetMapping
    public List<TeacherResponse> getAllTeachers() {
        return service.getAllTeachers();
    }

    @GetMapping("students/{teacherId}")
    public List<StudentResponse> allStudentsInThisCompany(@PathVariable long teacherId) {
        return service.getAllStudentsOfThisTeacher(teacherId);
    }


//    @GetMapping
//    @Operation(summary = "Get allTeachersAndSearch", description = "We can get all teachers and search")
//    public TeacherResponseView getAllTeachers(@RequestParam(name = "text", required = false) String text,
//                                              @RequestParam int page,
//                                              @RequestParam int size) {
//        return service.getAllTeachersPagination(text, page, size);
//    }
}
