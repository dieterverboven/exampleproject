package login.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import login.model.User;
import login.model.UserOrder;

public interface UserOrderRepository  extends CrudRepository<UserOrder, Long> {

	List<UserOrder> findByUser(User user);
}
