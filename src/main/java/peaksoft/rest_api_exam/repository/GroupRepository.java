package peaksoft.rest_api_exam.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.rest_api_exam.model.Group;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select g from Group g where g.groupName like %?1%")
    Group getGroupByGroupName(String group);

    @Query("select g from Group g where upper(g.groupName) like concat('%',:text,'%')")
    List<Group> searchAndPagination(@Param("text") String text, Pageable pageable);
}