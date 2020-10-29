package ua.conference.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.conference.springapp.service.UserService;

@Controller
public class SupportController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user_list")
	public String userList(Model model) {
		model.addAttribute("users", userService.findAll());
		return "user_list";
	}
	
	@GetMapping("/locale")
	public String switchLocale() {
		return "redirect:/";
	}

}
