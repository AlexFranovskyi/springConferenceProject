package ua.conference.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.conference.springapp.service.UserService;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String userList(Model model) {
		model.addAttribute("users", userService.findAll());
		return "user_list";
	}

}
