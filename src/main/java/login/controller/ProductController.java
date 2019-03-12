package login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import login.repository.ProductRepository;

@Controller
public class ProductController {
	@Autowired
	ProductRepository repository;
	
	@GetMapping("/manageproducts")
    public String manage(Model model) {
        return "redirect:/manageProducts";
    }
	
	@RequestMapping(value = "/deleteproduct", method = RequestMethod.POST)
	public String deleteProduct(@RequestParam(name="id", required=true) Long id, Model model) {
		
		repository.deleteById(id);
		
		model.addAttribute("list", repository.findAll());
        
        return "manageProducts";
	}
}
