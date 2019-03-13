package login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import login.model.Product;
import login.repository.ProductRepository;

@Controller
public class ProductController {
	@Autowired
	ProductRepository repository;
	
	@GetMapping("/manageproducts")
    public String manage(Model model) {
        return "redirect:/manageProducts";
    }
	
	@GetMapping("/products")
    public String products(Model model) {
		model.addAttribute("list", repository.findAll());
        return "products";
    }
	
	@RequestMapping(value = "/deleteproduct", method = RequestMethod.POST)
	public String deleteProduct(@RequestParam(name="id", required=true) Long id, Model model) {
		
		repository.deleteById(id);
		
		model.addAttribute("list", repository.findAll());
        
        return "manageProducts";
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProduct(@RequestParam(name="name", required=false) String name, @RequestParam(name="price", required=false) double price, Model model) {
		
		Product product = new Product(name, price);
		
		repository.save(product);
		
		model.addAttribute("list", repository.findAll());
        
        return "manageProducts";
	}
}
