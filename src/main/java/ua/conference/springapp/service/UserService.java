package ua.conference.springapp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.conference.springapp.entity.Role;
import ua.conference.springapp.entity.User;
import ua.conference.springapp.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findUserById(long id) {
		return userRepository.findById(id);
	}
	
	public boolean updateUser(User user, String username, Map<String, String> form) {
		user.setUsername(username);
		Set<String> roles = Arrays.stream(Role.values())
				.map(Role::name)
				.collect(Collectors.toSet());
		
		user.getRoles().clear();
		
		for (String key : form.keySet()) {
			if (roles.contains(key)) {
				user.getRoles().add(Role.valueOf(key));
			}
		}
		
		userRepository.save(user);
		return true;
	}

}
