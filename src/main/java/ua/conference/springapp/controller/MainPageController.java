package ua.conference.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.conference.springapp.entity.Conference;
import ua.conference.springapp.entity.User;
import ua.conference.springapp.service.MainPageService;

@Controller
public class MainPageController {

	@Autowired
	private MainPageService mainPageService;
	
	@GetMapping(value = {"/", "/main"})
	public String main(@RequestParam(required = false, defaultValue = "") String sorter, Model model) {
		model.addAttribute("conferences", mainPageService.findAllSorted(sorter));
		model.addAttribute("sorter", sorter);
		return "main";
	}
	
	@PostMapping("/main")
	public String registerVisitor(
			@AuthenticationPrincipal User user,
			@RequestParam(name = "conferenceId") Conference conference, Model model
			) {
		mainPageService.addVisitor(conference, user);
		model.addAttribute("message", "the registration is successful");
		return "main";
	}
}