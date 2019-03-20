package login;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import login.model.Product;
import login.model.Role;
import login.model.User;
import login.repository.ProductRepository;
import login.repository.RoleRepository;
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
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
    
	@Bean
	public CommandLineRunner demo(UserRepository userRepository, ProductRepository productRepository, UserOrderRepository orderRepository, RoleRepository roleRepository) {
		return (args) -> {
			// save a couple of roles
			roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));
			Role adminRole = roleRepository.findByRole("ADMIN");
			Role userRole = roleRepository.findByRole("USER");
						
			// save a couple of users
			userRepository.save(new User("Jack", "Bauer", adminRole));
			userRepository.save(new User("Chloe", "Brian", adminRole));
			userRepository.save(new User("Kim", "Bauer", adminRole));
			userRepository.save(new User("David", "Palmer", userRole));
			userRepository.save(new User("Michelle", "Dessler", userRole));
			
			// fetch all users
			log.info("Users found with findAll():");
			log.info("-------------------------------");
			for (User user : userRepository.findAll()) {
				log.info(user.toString());
			}
			log.info("");
			// save a couple of products
			productRepository.save(new Product("Stella Artois", 1.99));
			productRepository.save(new Product("Jupiler", 1.99));
			productRepository.save(new Product("Duvel", 2.99));
			productRepository.save(new Product("Leffe", 2.99));
			// save a couple of orders
			/*orderRepository.save(new UserOrder(userRepository.findByUserId(1L), productRepository.findByProductId(7L), 5));
			orderRepository.save(new UserOrder(userRepository.findByUserId(1L), productRepository.findByProductId(6L), 7));*/
			
			/*roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));*/
		};
	}

}
