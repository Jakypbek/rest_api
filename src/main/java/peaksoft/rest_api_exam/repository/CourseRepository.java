package peaksoft.rest_api_exam.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.rest_api_exam.model.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c where c.courseName like %?1%")
    Course getCourseByCourseName (String courseName);

    @Query("select c from Course c where c.company.id = ?1")
    List<Course> findAllCoursesByCompanyId(Long companyId);

    @Query("select c from Course c where upper(c.courseName) like concat('%',:text,'%') ")
    List<Course> searchAndPagination(@Param("text") String text, Pageable pageable);
}