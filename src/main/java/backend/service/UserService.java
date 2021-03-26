package backend.service;

import backend.entity.User;
import backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	@Autowired
  	private final UserRepository userRepository;

	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	public void saveUser(User user){
		userRepository.save(user);
	}

	@Transactional
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public List<User> findByName(String name) {
		return userRepository.findByName(name);
	}

	public List<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public List<User> findAllWhoHaveOrders() {
		return userRepository.findAllWhoHaveOrders();
	}
}
