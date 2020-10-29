package ua.conference.springapp.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.conference.springapp.Dto.ConferenceDto;
import ua.conference.springapp.entity.User;
import ua.conference.springapp.service.ConferenceService;
import ua.conference.springapp.support.Constants;

@Controller
@RequestMapping("/user_page")
@PreAuthorize("hasAuthority('USER')")
public class UserPageController {

	@Autowired
	private ConferenceService conferenceService;
	
	@GetMapping
	public String main(Model model,
			@RequestParam(required  = false, defaultValue = "yes") String showFutureEvents,
			@PageableDefault(sort = {"localDateTime"}, direction = Sort.Direction.DESC,
				size = Constants.PAGINATING_SIZE) Pageable pageable
			) {
		Page<ConferenceDto> page = conferenceService.findConferencesDtoSelectedByTime(showFutureEvents, pageable);
		model.addAttribute("showFutureEvents", showFutureEvents);
		model.addAttribute("page", page);
		model.addAttribute("url", "/user_page");
		model.addAttribute("sort", page.getSort());
		return "user_page";
	}
	
	@PostMapping
	public String registerVisitor(
			@AuthenticationPrincipal User user,
			@RequestParam long conferenceId, Model model
			) {
		try {
			conferenceService.addVisitor(conferenceId, user);			
			model.addAttribute("message", "You have been registered to visit this event");
		} catch (NoSuchElementException e) {
			model.addAttribute("message", "the registration is failed, please, try again");
		}
		return "user_page";
	}
}