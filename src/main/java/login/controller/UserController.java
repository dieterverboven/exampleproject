package login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import login.model.User;
import login.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository repository;
	
	User user;
	
	@GetMapping("/manageusers")
    public String manage(Model model) {
        return "redirect:/manageUsers";
    }
	
	@RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
	public String deleteProduct(@RequestParam(name="id", required=true) Long id, Model model) {
		
		repository.deleteById(id);
		
		model.addAttribute("list", repository.findAll());
        
        return "manageUsers";
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(@RequestParam(name="id", required=true) Long id ,@RequestParam(name="username", required=true) String username, 
							@RequestParam(name="password", required=true) String password, Model model) {
		
		user = new User();
		
		repository.findById(id).ifPresent(foundUser -> {
			user = foundUser;
			user.setUsername(username);
			user.setPassword(password);
		});
		
		repository.save(user);
		
		
		model.addAttribute("loggedInUser", user);
        
        return "welcome";
	}
	
	@GetMapping(value = "/settings")
	public String editUser(@RequestParam(name="id", required=true) Long id, Model model) {
		
		model.addAttribute("loggedInUser", repository.findByUserId(id));
        return "settings";
	}
}
