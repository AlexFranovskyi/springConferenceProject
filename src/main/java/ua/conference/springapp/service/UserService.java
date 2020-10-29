package ua.conference.springapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.conference.springapp.Dto.UserDto;
import ua.conference.springapp.entity.User;
import ua.conference.springapp.repository.UserRepository;
import ua.conference.springapp.support.EntityDtoConverter;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}
	
	public List<UserDto> findAll(){
		return userRepository.findAll().stream()
				.map(EntityDtoConverter::convertUserToDto).collect(Collectors.toList());
	}
	
	public User findUserById(long id) {
		return userRepository.findById(id);
	}

}
