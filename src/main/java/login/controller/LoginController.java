package login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import login.model.User;
import login.repository.ProductRepository;
import login.repository.RoleRepository;
import login.repository.UserRepository;

@Controller
public class LoginController {
	@Autowired
	UserRepository repository;
	@Autowired
	ProductRepository productRes;
	@Autowired
	RoleRepository roleRepository;
	
	User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication();
	String msg = "user";
	User user;
	
	// inject via application.properties
		@Value("${welcome.message:test}")
		private String message = "Hello World";

		
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping("/")
	public String welcome(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
	      model.addAttribute("username", auth.getName());
		
		model.addAttribute("message", message);
		model.addAttribute("msg", msg);
		
		model.addAttribute("loggedInUser", loggedInUser);
		return "welcome";
	}
	
	@GetMapping("/login")
    public String login(@RequestParam(name="msg", required=false) String msg, @RequestParam(name="password", required=false) String password, Model model) {
        model.addAttribute("msg", msg);
        model.addAttribute("loggedInUser", loggedInUser);
        return "login";
    }
	@GetMapping("/products")
    public String products(Model model) {
		model.addAttribute("list", productRes.findAll());
		model.addAttribute("loggedInUser", loggedInUser);
        return "products";
    }
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String signin(@RequestParam(name="username", required=false) String username, @RequestParam(name="password", required=false) String password, Model model) {
		
		Optional<User> user = repository.findByUsername(username);
        
        if(user != null && user.getPassword().equals(password))
        {
        	loggedInUser = user;
        	msg = user.getUsername();
        	model.addAttribute("msg", msg);
        	model.addAttribute("loggedInUser", loggedInUser);
        	return "welcome";
        	
        }
        return "login";
	}*/
	
	@GetMapping("/register")
    public String register(@RequestParam(name="username", required=false) String name, @RequestParam(name="password", required=false) String password, Model model) {
		model.addAttribute("loggedInUser", loggedInUser);
        return "register";
    }
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String signup(@RequestParam(name="username", required=false) String username, @RequestParam(name="password", required=false) String password, Model model) {
		model.addAttribute("username", username);
        model.addAttribute("password", password);
        User user = new User(username, password, roleRepository.findByRole("USER"));
        
        log.info(user.toString());		
        repository.save(user);
        loggedInUser = user;
        msg = username;
    	model.addAttribute("msg", msg);
    	model.addAttribute("loggedInUser", loggedInUser);
        return "welcome";
	}
	@GetMapping("/confirm-registration")
    public String makeUser(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("loggedInUser", loggedInUser);
        return "register";
    }
	
	@GetMapping("/logout")
    public String logout(@RequestParam(name="username", required=false) String name, @RequestParam(name="password", required=false) String password, Model model) {
        
		loggedInUser = null;
		msg = "user";
		model.addAttribute("loggedInUser", loggedInUser);
		model.addAttribute("msg", msg);
        return "welcome";
    }
	/*
	@GetMapping("/manageProducts")
    public String manageProducts(Model model) {
        
		if(loggedInUser == null || loggedInUser.getRoles() != 1)
		{
			return "redirect:/unauthorized";
		}
		else
		{
			model.addAttribute("loggedInUser", loggedInUser);
			model.addAttribute("list", productRes.findAll());
			return "manageProducts";
		}
    }
	
	@GetMapping("/manageUsers")
    public String manageUsers(Model model) {
        
		if(loggedInUser == null || loggedInUser.getRole() != 1)
		{
			return "redirect:/unauthorized";
		}
		else
		{
			model.addAttribute("loggedInUser", loggedInUser);
			model.addAttribute("list", repository.findAll());
			return "manageUsers";
		}
    }
	
	@GetMapping("/editProduct")
    public String product(@RequestParam(name="id", required=true) Long id, Model model) {
		
		if(loggedInUser == null || loggedInUser.getRole() != 1)
		{
			return "redirect:/unauthorized";
		}
		else
		{
			model.addAttribute("product", productRes.findByProductId(id));
	        return "editProduct";
		}
				
    }*/
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(@RequestParam(name="id", required=true) Long id ,@RequestParam(name="username", required=true) String username, 
							@RequestParam(name="password", required=true) String password, Model model) {
		
		user = new User();
		
		repository.findById(id).ifPresent(foundUser -> {
			user = foundUser;
			user.setUsername(username);
			user.setPassword(password);
			loggedInUser = user;
		});
		
		repository.save(user);
		
		
		model.addAttribute("loggedInUser", user);
        
        return "welcome";
	}
	
	@GetMapping("/unauthorized")
    public String unautherized(Model model) {
		model.addAttribute("loggedInUser", loggedInUser);
        
		return "unauthorized";
    }
}
