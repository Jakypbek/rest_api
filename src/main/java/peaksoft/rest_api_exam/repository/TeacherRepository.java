package peaksoft.rest_api_exam.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.rest_api_exam.model.Teacher;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("select t from Teacher t where upper(t.firstName) like concat('%',:text,'%') " +
            "or upper(t.lastName) like concat('%',:text,'%') or upper(t.email) like concat('%',:text,'%')")
    List<Teacher> searchAndPagination(@Param("text") String text, Pageable pageable);
}