package peaksoft.rest_api_exam.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.rest_api_exam.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s.firstName like %?1%")
    Student findByStudentsName(String studentsName);

    @Query("select s from Student s where upper(s.firstName) like concat('%',:text,'%') " +
            "or upper(s.lastName) like concat('%',:text,'%') or upper(s.email) like concat('%',:text,'%')")
    List<Student> searchAndPagination(@Param("text") String text, Pageable pageable);
}
