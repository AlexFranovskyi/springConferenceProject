package ua.conference.springapp.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.conference.springapp.Dto.ConferenceDto;
import ua.conference.springapp.entity.Conference;
import ua.conference.springapp.entity.User;
import ua.conference.springapp.repository.ConferenceRepository;
import ua.conference.springapp.support.EntityDtoConverter;

@Service
public class ConferenceService {
	
	@Autowired
	private ConferenceRepository conferenceRepository;
	
	public Page<ConferenceDto> findAllConferencesDto(Pageable pageable){
		return conferenceRepository.findAll(pageable).map(EntityDtoConverter::convertConferenceToDto);
	}
	
	public ConferenceDto findConferenceDtoById(long id) {
		Optional<Conference> conference = conferenceRepository.findById(id);
		return EntityDtoConverter.convertConferenceToDto(conference.get());
	}
	
	public Page<ConferenceDto> findConferencesDtoSelectedByTime(String showFutureEvents, Pageable pageable){
		if ("yes".equals(showFutureEvents)) {
			return conferenceRepository.findAllByLocalDateTimeAfter(LocalDateTime.now(), pageable)
					.map(EntityDtoConverter::convertConferenceToDto);
		}
		return conferenceRepository.findAllByLocalDateTimeBefore(LocalDateTime.now(), pageable)
				.map(EntityDtoConverter::convertConferenceToDto);
	}
	
	public Conference createConference(String name, LocalDateTime localDateTime, String location) {
		Conference newConference = Conference
				.builder()
				.name(name)
				.localDateTime(localDateTime)
				.location(location)
				.build();
		return conferenceRepository.save(newConference);
	}
	
	@Transactional
	public Conference updateConference(long id, LocalDateTime localDateTime,
			String location, int arrivedVisitorsAmount
			) {
		Conference newConference = conferenceRepository.findById(id).get();
		newConference.setLocalDateTime(localDateTime);
		newConference.setLocation(location);
		newConference.setArrivedVisitorsAmount(arrivedVisitorsAmount);
		return conferenceRepository.save(newConference);
	}
	
	@Transactional
	public Conference addVisitor(long conferenceId, User user) {
		Conference conference = conferenceRepository.findById(conferenceId).get();
		conference.getVisitors().add(user);
		return conferenceRepository.save(conference);
	}
	
	public void deleteConference(long id) {
		conferenceRepository.deleteById(id);
	}

}
