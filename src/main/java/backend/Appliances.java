package backend;

import backend.entity.Good;
import backend.entity.Order;
import backend.entity.User;
import backend.service.GoodService;
import backend.service.OrderService;
import backend.service.UserService;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Appliances {
	@Autowired
	private UserService userService;

  	public static void main(String[] args) {
		SpringApplication.run(Appliances.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void testJpaMethods(){
		User user = new User();

		user.setName("Smith");
		user.setAddress("Smith's address");
		user.setEmail("smith@gmail.com");
		user.setNumber("8-800-555-35-35");
		
		userService.saveUser(user);

		userService.findAll().forEach(it->System.out.println(it.getEmail()));
	}
}