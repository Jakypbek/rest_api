package peaksoft.rest_api_exam.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.rest_api_exam.dto.*;
import peaksoft.rest_api_exam.service.GroupService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
@PreAuthorize("hasAuthority('TEACHER')")
@Tag(name = "Group API", description ="User with role admin can add,update, delete or get all groups")
public class GroupController {


    private final GroupService service;

    @PostMapping
    @Operation(summary = "create student", description = "we can create student")
    public GroupResponse create(@RequestBody GroupRequest request) {
        return service.create(request);
    }

    @PutMapping("{id}")
    @Operation(summary = "update student", description = "we can update student by id")
    public GroupResponse update(@PathVariable long id, @RequestBody GroupRequest request) {
        return service.update(id, request);
    }

    @GetMapping("{id}")
    @Operation(summary = "find student", description = "we can find student by id")
    public GroupResponse findById(@PathVariable long id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete student", description = "we can delete student by id")
    public GroupResponse delete(@PathVariable long id) {
        return service.deleteById(id);
    }

    @GetMapping
    public List<GroupResponse> getAllGroups() {
        return service.getAllGroups();
    }

    @GetMapping("findByStudentsName/{studentsName}")
    public StudentResponse findByStudentsName(@PathVariable String studentsName) {
        return service.findByStudentsName(studentsName);
    }


//    @GetMapping
//    @Operation(summary = "Get allGroupsAndSearch", description = "We can get all groups and search")
//    public GroupResponseView getAllGroups(@RequestParam(name = "text", required = false) String text,
//                                             @RequestParam int page,
//                                             @RequestParam int size) {
//        return service.getAllGroupsPagination(text, page, size);
//    }
}
