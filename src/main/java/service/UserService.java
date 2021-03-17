package service;

import entity.User;
import repository.UserRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<User> findAllByName(String name){
		return userRepository.findAllByName(name);
	}
}
