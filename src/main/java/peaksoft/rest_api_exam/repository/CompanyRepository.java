package peaksoft.rest_api_exam.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.rest_api_exam.model.Company;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select c from Company c where c.companyName like %?1%")
    Company findCompanyByName(String company);

    @Query("select c from Company c where upper(c.companyName) like concat('%',:text,'%') " +
            "or upper(c.locatedCountry) like concat('%',:text,'%')")
    List<Company> searchAndPagination(@Param("text") String text, Pageable pageable);
}