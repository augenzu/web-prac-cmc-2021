package backend.service;

import backend.entity.Order;
import backend.entity.User;
import backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
  	private final UserRepository userRepository;

	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public List<User> findByName(String name) {
		return userRepository.findByName(name);
	}

	public List<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public List<Order> findUserOrdersById(Long id) {
		return userRepository.findUserOrdersById(id);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public List<User> saveAll(List<User> users) {
		return userRepository.saveAll(users);
	}
}
