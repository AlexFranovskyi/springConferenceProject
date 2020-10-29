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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.conference.springapp.Dto.ConferenceDto;
import ua.conference.springapp.Dto.ReportDto;
import ua.conference.springapp.entity.Report;
import ua.conference.springapp.entity.User;
import ua.conference.springapp.service.ConferenceService;
import ua.conference.springapp.service.ReportService;
import ua.conference.springapp.support.Constants;

@Controller
@RequestMapping("/speaker")
@PreAuthorize("hasAuthority('SPEAKER')")
public class SpeakerController {
	
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
		model.addAttribute("url", "/speaker");
		model.addAttribute("sort", page.getSort());
		return "speaker_conferences";
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
	public String postReport(@AuthenticationPrincipal User speaker,
			Model model, 
			@PathVariable(name = "id") long conferenceId,
			@RequestParam String name
			) {
		reportService.createReportWithNameAndSpeaker(name, speaker, conferenceId);
		model.addAttribute("message", "New report is created successfully");
		model.addAttribute("conferenceId", conferenceId);
		return "redirect:/speaker/{id}/reports";
	}
	
	@PostMapping("/{id}/propose_speaker")
	public String takeReport(@AuthenticationPrincipal User speaker,
			Model model,
			@RequestParam long reportId,
			@PathVariable(name = "id") long conferenceId
			) {
		reportService.updateReportWithSpeaker(reportId, speaker);
		model.addAttribute("message", "The proposal is added successfully");
		model.addAttribute("conferenceId", conferenceId);
		return "redirect:/speaker/{id}/reports";
	}
	
	@GetMapping("/own_reports")
	public String showSpeakerReports(Model model,
			@AuthenticationPrincipal User speaker,
			@PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC,
			size = Constants.PAGINATING_SIZE) Pageable pageable
			) {
		Page<ReportDto> page = reportService.findAllReportsBySpeaker(speaker, pageable);
		model.addAttribute("page", page);
		model.addAttribute("sort", page.getSort());
		return "speaker_reports";
	}

}
