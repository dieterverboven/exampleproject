package login;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import login.model.Product;
import login.model.User;
import login.model.UserOrder;
import login.repository.ProductRepository;
import login.repository.UserOrderRepository;
import login.repository.UserRepository;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
    
    @Bean
	public CommandLineRunner demo(UserRepository userRepository, ProductRepository productRepository, UserOrderRepository orderRepository) {
		return (args) -> {
			// save a couple of users
			userRepository.save(new User("Jack", "Bauer", 1));
			userRepository.save(new User("Chloe", "O'Brian", 0));
			userRepository.save(new User("Kim", "Bauer", 1));
			userRepository.save(new User("David", "Palmer", 0));
			userRepository.save(new User("Michelle", "Dessler", 0));
			// save a couple of products
			productRepository.save(new Product("Stella Artois", 1.99));
			productRepository.save(new Product("Jupiler", 1.99));
			productRepository.save(new Product("Duvel", 2.99));
			productRepository.save(new Product("Leffe", 2.99));
			// save a couple of orders
			orderRepository.save(new UserOrder(userRepository.findByUserId(1L), productRepository.findByProductId(7L), 5));
			orderRepository.save(new UserOrder(userRepository.findByUserId(1L), productRepository.findByProductId(6L), 7));
		};
	}

}
