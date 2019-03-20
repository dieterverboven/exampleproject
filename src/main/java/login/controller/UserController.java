package login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import login.model.User;
import login.repository.RoleRepository;
import login.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository repository;
	@Autowired
	RoleRepository roleRepository;
		
	@GetMapping("/manageusers")
    public String manage(Model model) {
        return "redirect:/manageUsers";
    }
	
	@RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
	public String deleteProduct(@RequestParam(name="id", required=true) Long id, Model model) {
		
		repository.deleteById(id);
		
		model.addAttribute("list", repository.findAll());
        
        return "redirect:/manageUsers";
	}
	
	@RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
	public String addProduct(@RequestParam(name="username", required=false) String username, @RequestParam(name="password", required=false) String password, Model model) {
		
		User user = new User(username, password, roleRepository.findByRole("ADMIN"));
		
		repository.save(user);
		
        return "redirect:/manageUsers";
	}
	
	@GetMapping(value = "/settings")
	public String editUser(@RequestParam(name="id", required=true) Long id, Model model) {
		
		model.addAttribute("loggedInUser", repository.findByUserId(id));
        return "settings";
	}
}
