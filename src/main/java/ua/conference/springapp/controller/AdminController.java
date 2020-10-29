package ua.conference.springapp.controller;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import ua.conference.springapp.Dto.ConferenceDto;
import ua.conference.springapp.Dto.ReportDto;
import ua.conference.springapp.service.ConferenceService;
import ua.conference.springapp.service.ReportService;
import ua.conference.springapp.support.Constants;
import ua.conference.springapp.support.EntityDtoConverter;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
	
	@Autowired
	private ConferenceService conferenceService;
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping
	public String main(Model model,
			@RequestParam(required  = false, defaultValue = "yes") String showFutureEvents,
			@PageableDefault(sort = {"localDateTime"}, direction = Sort.Direction.DESC,
				size = Constants.PAGINATING_SIZE) Pageable pageable
			) {
		Page<ConferenceDto> page = conferenceService.findConferencesDtoSelectedByTime(showFutureEvents, pageable);
		model.addAttribute("showFutureEvents", showFutureEvents);
		model.addAttribute("page", page);
		model.addAttribute("url", "/admin");
		model.addAttribute("sort", page.getSort());
		return "admin_conferences";
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String add( 
			@RequestParam String name,
			@RequestParam("localDateTime")
			@DateTimeFormat(pattern = Constants.LOCAL_TIME_DATE_PATTERN) LocalDateTime localDateTime,
			@RequestParam String location, Model model) {
		conferenceService.createConference(name, localDateTime, location);
		
		model.addAttribute("message", "new conference has been created succesfully");
		return "admin_conferences";
	}
	
	@GetMapping("/{id}")
	public String getConference(Model model,
			@PathVariable long id) {
		try {
			ConferenceDto conferenceDto = conferenceService.findConferenceDtoById(id);
			model.addAttribute("conference", conferenceDto);				
		} catch (NoSuchElementException e){
			//TODO logger;
		}
		return "conference_details";
	}
	
	@PostMapping("/{id}")
	public String updateConference(Model model,
			@RequestParam
			@DateTimeFormat(pattern = Constants.LOCAL_TIME_DATE_PATTERN) LocalDateTime localDateTime,
			@RequestParam String location,
			@RequestParam(required = false, defaultValue = "0") int arrivedVisitorsAmount,
			@PathVariable long id) {
		
		try {
			ConferenceDto conferenceDto = EntityDtoConverter.convertConferenceToDto(conferenceService
					.updateConference(id, localDateTime, location, arrivedVisitorsAmount));
			
			model.addAttribute("conference", conferenceDto);
			model.addAttribute("message", "Changes are successful");
		} catch(NoSuchElementException e) {
			//TODO logger;
			model.addAttribute("message", "Changes are failed due to some reason");
		}
		return "conference_details";
	}
	
	@PostMapping("/{id}/delete")
	public String updateConference(Model model,
			@RequestParam long conferenceId
			) {
		conferenceService.deleteConference(conferenceId);
		return "redirect:/admin";
	}
	
	@GetMapping("/{id}/reports")
	public String getReports(Model model, @PathVariable long id,
			@PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC,
			size = Constants.PAGINATING_SIZE) Pageable pageable
			) {
		Page<ReportDto> page = reportService.findAllReportsDtoByConferenceId(id, pageable);
		model.addAttribute("page", page);
		model.addAttribute("conferenceId", id);
		model.addAttribute("sort", page.getSort());
		return "report_actions";
	}
	
	@PostMapping("/{id}/post_report")
	public String postReport(Model model, @PathVariable(name = "id") long conferenceId,
			@RequestParam String name
			) {
		reportService.createReportWithName(name, conferenceId);
		model.addAttribute("message", "New report is created successfully");
		model.addAttribute("conferenceId", conferenceId);
		return "redirect:/admin/{id}/reports";
	}
	
	@PostMapping("/{id}/clear_speaker")
	public String postReport(Model model, @PathVariable(name = "id") long conferenceId,
			@RequestParam long reportId
			) {
		reportService.clearSpeakerFromReport(reportId);
		model.addAttribute("conferenceId", conferenceId);
		return "redirect:/admin/{id}/reports";
	}
	
	@PostMapping("/{id}/approve")
	public String approveReport(Model model,
			@RequestParam long reportId,
			@PathVariable(name = "id") long conferenceId
			) {
		reportService.approveReport(reportId);
		model.addAttribute("message", "Report is approved");
		model.addAttribute("conferenceId", conferenceId);
		return "redirect:/admin/{id}/reports";
	}
	
	@PostMapping("/{id}/disapprove")
	public String disapproveReport(Model model,
			@RequestParam long reportId,
			@PathVariable(name = "id") long conferenceId
			) {
		reportService.disapproveReport(reportId);
		model.addAttribute("message", "Report is approved");
		model.addAttribute("conferenceId", conferenceId);
		return "redirect:/admin/{id}/reports";
	}
	
}
