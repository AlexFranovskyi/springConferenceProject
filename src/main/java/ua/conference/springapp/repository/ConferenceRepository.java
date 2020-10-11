package ua.conference.springapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ua.conference.springapp.entity.Conference;

public interface ConferenceRepository extends CrudRepository<Conference, Long> {
	
	List<Conference> findAllByOrderByLocalDateTimeDesc();
	List<Conference> findAllByOrderByReportCounterDesc();
	List<Conference> findAllByOrderByRegisterCounterDesc();
	Conference findById(long id);

}
