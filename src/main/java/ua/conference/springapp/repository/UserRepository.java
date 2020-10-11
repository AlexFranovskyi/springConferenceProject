package ua.conference.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.conference.springapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	User findByUsernameOrEmail(String username, String email);
	User findById(long id);
}
