package login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import login.model.UserOrder;
import login.repository.ProductRepository;
import login.repository.UserOrderRepository;
import login.repository.UserRepository;

@Controller
public class OrderController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserOrderRepository orderRepository;
	
	@GetMapping("/orders")
	public String orders(@RequestParam(name="userId", required=true) Long userId, Model model) {
		model.addAttribute("loggedInUser", userRepository.findByUserId(userId));
		model.addAttribute("list", orderRepository.findByUser(userRepository.findByUserId(userId)));
        return "orders";
    }
	
	
	
	@RequestMapping(value = "/orderProduct", method = RequestMethod.POST)
	public String updateUser(@RequestParam(name="userId", required=true) Long userId ,@RequestParam(name="productId", required=true) Long productId, 
							@RequestParam(name="amount", required=true) int amount, Model model) {
		UserOrder order = new UserOrder(userRepository.findByUserId(userId), productRepository.findByProductId(productId), amount);
		
		orderRepository.save(order);
        
        return "redirect:/products";
	}
}
