package ua.conference.springapp.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.conference.springapp.entity.Role;
import ua.conference.springapp.entity.User;
import ua.conference.springapp.repository.UserRepository;

@Service
public class RegistrationService {
	
	@Autowired
	UserRepository userRepository;
	
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public boolean addUser(User user, String role) {
		User userFromDb = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
		if (userFromDb != null) {
			return false;
		}
		user.setActive(true);
		user.setRoles(Collections.singleton(Role.valueOf(role.toUpperCase())));
		
		userRepository.save(user);
		return true;
	}

}
