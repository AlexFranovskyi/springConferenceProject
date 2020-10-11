package ua.conference.springapp.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import ua.conference.springapp.service.MainPageService;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
	
	@Autowired
	private MainPageService mainPageService;
	
	@GetMapping("/admin")
	public String main(@RequestParam(required = false, defaultValue = "") String sorter, Model model) {
		model.addAttribute("conferences", mainPageService.findAllSorted(sorter));
		model.addAttribute("sorter", sorter);
		return "admin";
	}
	
	@PostMapping("/admin")
	@ResponseStatus(HttpStatus.CREATED)
	public String add( 
			@RequestParam String name,
			@RequestParam("localDateTime")
				@DateTimeFormat(pattern = Constants.LOCAL_TIME_DATE_PATTERN) LocalDateTime localDateTime,
			@RequestParam String location, Model model) {
		mainPageService.create(name, localDateTime, location);
		return "admin";
	}

}
