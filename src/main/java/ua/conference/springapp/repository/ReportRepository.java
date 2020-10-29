package ua.conference.springapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ua.conference.springapp.entity.Report;
import ua.conference.springapp.entity.User;

public interface ReportRepository extends CrudRepository<Report, Long> {
	Page<Report> findAllByConferenceId(long conferenceId, Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("UPDATE Report r set r.speaker = null WHERE r.id = :reportId")
	int clearSpeakerName(@Param("reportId") long reportId);
	
	Page<Report> findAllBySpeaker(User speaker, Pageable pageable);
}
