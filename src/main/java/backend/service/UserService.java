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

	public User save(User user) {
		return userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public Optional<User> update(User newUser) {
		Optional<User> oldUser = findById(newUser.getId());

		if (oldUser.isPresent()) {
			User savedUser = save(newUser);
			return Optional.of(savedUser);
		} else {
			return oldUser;
		}
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public List<User> findByNameContaining(String name) {
		return userRepository.findByNameContainingIgnoreCase(name);
	}

	public List<User> findByEmail(String email) {
		return userRepository.findByEmailIgnoreCase(email);
	}

	public List<Order> findUserOrdersById(Long id) {
		return userRepository.findUserOrdersById(id);
	}
}
