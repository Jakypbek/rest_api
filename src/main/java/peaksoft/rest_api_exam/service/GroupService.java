package peaksoft.rest_api_exam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.rest_api_exam.dto.*;
import peaksoft.rest_api_exam.mapper.GroupEditMapper;
import peaksoft.rest_api_exam.mapper.GroupViewMapper;
import peaksoft.rest_api_exam.mapper.StudentViewMapper;
import peaksoft.rest_api_exam.model.Company;
import peaksoft.rest_api_exam.model.Group;
import peaksoft.rest_api_exam.model.Student;
import peaksoft.rest_api_exam.repository.GroupRepository;
import peaksoft.rest_api_exam.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final StudentRepository studentRepository;
    private final StudentViewMapper studentViewMapper;
    private final GroupRepository repository;
    private final GroupEditMapper editMapper;
    private final GroupViewMapper viewMapper;


    public GroupResponse create(GroupRequest groupRequest) {
        Group group = editMapper.create(groupRequest);
        repository.save(group);
        return viewMapper.viewGroup(group);
    }

    public GroupResponse update(long id, GroupRequest groupRequest) {
        Group group = repository.findById(id).get();
        editMapper.update(group, groupRequest);
        return viewMapper.viewGroup(repository.save(group));
    }

    public GroupResponse findById(long id) {
        Group group = repository.findById(id).get();
        return viewMapper.viewGroup(group);
    }

    public GroupResponse deleteById(long id) {
        Group group = repository.getById(id);
        repository.delete(group);
        return viewMapper.viewGroup(group);
    }

    public List<GroupResponse> getAllGroups() {
        return viewMapper.view(repository.findAll());
    }

    public StudentResponse findByStudentsName(String studentsName) {
        return studentViewMapper.viewStudent(studentRepository.findByStudentsName(studentsName));
    }

    public GroupResponseView getAllGroupsPagination(String text, int page, int size) {
        GroupResponseView responseView = new GroupResponseView();
        Pageable pageable = PageRequest.of(page, size);
        responseView.setResponses(view(search(text, pageable)));
        return responseView;
    }

    public List<GroupResponse> view(List<Group> groups) {
        List<GroupResponse> responses = new ArrayList<>();
        for (Group group : groups) {
            responses.add(viewMapper.viewGroup(group));
        }
        return responses;
    }

    private List<Group> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return repository.searchAndPagination(text.toUpperCase(), pageable);
    }
}
