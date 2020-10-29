package ua.conference.springapp.support;

import ua.conference.springapp.Dto.ConferenceDto;
import ua.conference.springapp.Dto.ReportDto;
import ua.conference.springapp.Dto.UserDto;
import ua.conference.springapp.entity.Conference;
import ua.conference.springapp.entity.Report;
import ua.conference.springapp.entity.User;

public class EntityDtoConverter {
	
	private EntityDtoConverter() {
		
	}
	
	public static UserDto convertUserToDto(User user) {
		return UserDto.builder().id(user.getId())
				.username(user.getUsername())
				.email(user.getEmail())
				.active(user.isActive())
				.roles(user.getRoles())
				.build();
	}
	
	public static ReportDto convertReportToDto(Report report) {
		return ReportDto.builder()
				.id(report.getId())
				.name(report.getName())
				.speaker(report.getSpeaker())
				.conferenceName(report.getConference())
				.approved(report.isApproved())
				.build();
	}
	
	public static ConferenceDto convertConferenceToDto(Conference conference) {
		return ConferenceDto.builder()
				.id(conference.getId())
				.conferenceName(conference.getName())
				.localDateTime(conference.getLocalDateTime())
				.location(conference.getLocation())
				.visitorCounter(conference.getVisitorCounter())
				.reportCounter(conference.getReportCounter())
				.arrivedVisitorsAmount(conference.getArrivedVisitorsAmount())
				.build();
	}

}
