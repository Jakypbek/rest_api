package peaksoft.rest_api_exam.repository.login;


import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.rest_api_exam.model.login.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}