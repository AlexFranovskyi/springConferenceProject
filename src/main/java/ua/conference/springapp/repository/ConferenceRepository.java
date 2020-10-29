package ua.conference.springapp.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import ua.conference.springapp.entity.Conference;

public interface ConferenceRepository extends CrudRepository<Conference, Long> {
	
	Page<Conference> findAllByLocalDateTimeBefore(LocalDateTime dateTimeNow, Pageable pageable);
	Page<Conference> findAllByLocalDateTimeAfter(LocalDateTime dateTimeNow, Pageable pageable);
	
	Page<Conference> findAll(Pageable pageable);

}
