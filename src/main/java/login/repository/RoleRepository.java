package login.repository;

import org.springframework.data.repository.CrudRepository;

import login.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

	Role findByRole(String role);
}
