package login.repository;

import org.springframework.data.repository.CrudRepository;

import login.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
	User findByUserId(Long id);

}
