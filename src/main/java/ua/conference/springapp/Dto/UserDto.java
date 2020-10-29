package ua.conference.springapp.Dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.conference.springapp.entity.Role;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	
	private long id;
	
	private String username;
	private String email;
	
	private boolean active;
	private Set<Role> roles;

}
