package login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import login.model.User;
import login.repository.ProductRepository;
import login.repository.UserRepository;

@Controller
public class LoginController {
	@Autowired
	UserRepository repository;
	@Autowired
	ProductRepository productRes;
	User loggedInUser = null;
	String msg = "user";
	// inject via application.properties
		@Value("${welcome.message:test}")
		private String message = "Hello World";

		
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping("/")
	public String welcome(Model model) {
		model.addAttribute("message", message);
		model.addAttribute("msg", msg);
		model.addAttribute("loggedInUser", loggedInUser);
		return "welcome";
	}
	
	@GetMapping("/login")
    public String login(@RequestParam(name="msg", required=false) String msg, @RequestParam(name="password", required=false) String password, Model model) {
        model.addAttribute("msg", msg);
        
        return "login";
    }
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String signin(@RequestParam(name="username", required=false) String username, @RequestParam(name="password", required=false) String password, Model model) {
		
		User user = repository.findByUsername(username);
        
        if(user != null && user.getPassword().equals(password))
        {
        	loggedInUser = user;
        	msg = user.getUsername();
        	model.addAttribute("msg", msg);
        	model.addAttribute("loggedInUser", loggedInUser);
        	return "welcome";
        	
        }
        return "login";
	}
	
	@GetMapping("/register")
    public String register(@RequestParam(name="username", required=false) String name, @RequestParam(name="password", required=false) String password, Model model) {
        
        return "register";
    }
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String signup(@RequestParam(name="username", required=false) String username, @RequestParam(name="password", required=false) String password, Model model) {
		model.addAttribute("username", username);
        model.addAttribute("password", password);
        User user = new User(username, password, 0);
        
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
	
	@GetMapping("/manageProducts")
    public String manageProducts(Model model) {
        
		if(loggedInUser == null || loggedInUser.getRole() != 1)
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
	
	@GetMapping("/unauthorized")
    public String unautherized(Model model) {
        
		return "unauthorized";
    }
}
