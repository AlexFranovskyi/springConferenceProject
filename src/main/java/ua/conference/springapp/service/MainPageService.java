package ua.conference.springapp.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.conference.springapp.entity.Conference;
import ua.conference.springapp.entity.User;
import ua.conference.springapp.repository.ConferenceRepository;

@Service
public class MainPageService {
	
	@Autowired
	private ConferenceRepository conferenceRepository;
	
	public Iterable<Conference> findAll(){
		return conferenceRepository.findAll();
	}
	
	public void create(String name, LocalDateTime localDateTime, String location) {
		conferenceRepository.save(new Conference(name, localDateTime, location));
	}
	
	public void addVisitor(Conference conference, User user) {
		conference.getVisitor().add(user);
		conferenceRepository.save(conference);
	}
	
	public Conference findConferenceById(long id) {
		return conferenceRepository.findById(id);
	}
	
	public Iterable<Conference> findAllSorted(String sorter){
		Iterable<Conference> conferences;
		if (sorter == null || sorter.isEmpty()) {
			return findAll();
		}
		
		if (sorter.equals("date")) {
			conferences = conferenceRepository.findAllByOrderByLocalDateTimeDesc();			
		} else if (sorter.equals("reports")){
			conferences = conferenceRepository.findAllByOrderByRegisterCounterDesc(); 
		} else if (sorter.equals("participants")) {
			conferences = conferenceRepository.findAllByOrderByReportCounterDesc();	
		} else {
			return findAll();
		}
		return conferences;
	}

}
