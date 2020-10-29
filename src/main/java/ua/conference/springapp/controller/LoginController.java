package ua.conference.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.conference.springapp.entity.Role;
import ua.conference.springapp.entity.User;
import ua.conference.springapp.service.RegistrationService;

@Controller
public class LoginController {
	
	@Autowired
	RegistrationService registrationService;
	
	@GetMapping("/login")
	public String loginForm() {
		if (isAuthenticated()) {
			return "redirect:/";
		}
		return "login";
	}
	
	@GetMapping("/registration")
	public String registration(Model model) {
		if (isAuthenticated()) {
			return "redirect:/";
		}
		model.addAttribute("roles", Role.values());
		return "registration";
	}
	
	@PostMapping("/registration")
	public String addUser(User user,
			@RequestParam String role,
			Model model) {
		if (registrationService.addUser(user, role)) {
			model.addAttribute("message", "registration is successful, now log in, please");
			return "redirect:/login";
		}
		model.addAttribute("countryMap", Role.values());
		model.addAttribute("message", "user with such login or email already exists");
		return "/registration";
	}
	
	private boolean isAuthenticated() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || AnonymousAuthenticationToken.class.
	      isAssignableFrom(authentication.getClass())) {
	        return false;
	    }
	    return authentication.isAuthenticated();
	}

}
