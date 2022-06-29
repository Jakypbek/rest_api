package peaksoft.rest_api_exam.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.rest_api_exam.dto.CompanyResponseView;
import peaksoft.rest_api_exam.dto.CourseRequest;
import peaksoft.rest_api_exam.dto.CourseResponse;
import peaksoft.rest_api_exam.dto.CourseResponseView;
import peaksoft.rest_api_exam.service.CourseService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Course API", description ="User with role admin can add,update, delete or get all courses")
public class CourseController {


    private final CourseService service;

    @PostMapping
    @Operation(summary = "create student", description = "we can create student")
    public CourseResponse create(@RequestBody CourseRequest request) {
        return service.create(request);
    }

    @PutMapping("{id}")
    @Operation(summary = "update student", description = "we can update student by id")
    public CourseResponse update(@PathVariable long id, @RequestBody CourseRequest request) {
        return service.update(id, request);
    }

    @GetMapping("{id}")
    @Operation(summary = "find student", description = "we can find student by id")
    public CourseResponse findById(@PathVariable long id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete student", description = "we can delete student by id")
    public CourseResponse delete(@PathVariable long id) {
        return service.deleteById(id);
    }

//    @GetMapping
//    public List<CourseResponse> getAllCourses() {
//        return service.getAllCourses();
//    }


    @GetMapping
    @Operation(summary = "Get allCoursesAndSearch", description = "We can get all courses and search")
    public CourseResponseView getAllCourses(@RequestParam(name = "text", required = false) String text,
                                              @RequestParam int page,
                                              @RequestParam int size) {
        return service.getAllCoursesPagination(text, page, size);
    }

}
