package ua.conference.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.conference.springapp.Dto.ReportDto;
import ua.conference.springapp.entity.Conference;
import ua.conference.springapp.entity.Report;
import ua.conference.springapp.entity.User;
import ua.conference.springapp.repository.ConferenceRepository;
import ua.conference.springapp.repository.ReportRepository;
import ua.conference.springapp.support.EntityDtoConverter;

@Service
public class ReportService {
	
	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private ConferenceRepository conferenceRepository;
	
	public Page<ReportDto> findAllReportsDtoByConferenceId(long id, Pageable pageable){
		return reportRepository.findAllByConferenceId(id, pageable)
				.map(EntityDtoConverter::convertReportToDto);
	}
	
	@Transactional
	public Report createReportWithName(String name, long conferenceId) {
		Conference conference = conferenceRepository.findById(conferenceId).get();
		return reportRepository.save(Report.builder().name(name).conference(conference).build());
	}
	
	@Transactional
	public Report createReportWithNameAndSpeaker(String name, User speaker, long conferenceId) {
		Conference conference = conferenceRepository.findById(conferenceId).get();
		return reportRepository.save(Report.builder().name(name).speaker(speaker).conference(conference).build());
	}
	
	@Transactional
	public Report updateReportWithSpeaker(long reportId, User speaker) {
		Report report =  reportRepository.findById(reportId).get();
		report.setSpeaker(speaker);
		return reportRepository.save(report);
	}
	
	@Transactional
	public Report approveReport(long reportId) {
		Report report =  reportRepository.findById(reportId).get();
		report.setApproved(true);
		return reportRepository.save(report);
	}
	
	@Transactional
	public Report disapproveReport(long reportId) {
		Report report =  reportRepository.findById(reportId).get();
		report.setApproved(false);
		return reportRepository.save(report);
	}
	
	public boolean clearSpeakerFromReport(long id) {
		if (reportRepository.clearSpeakerName(id) != 0) {
			return true;
		}
		return false;	
	}
	
	public Page<ReportDto> findAllReportsBySpeaker(User speaker, Pageable pageable){
		return reportRepository.findAllBySpeaker(speaker, pageable).map(EntityDtoConverter::convertReportToDto);
	}
}
