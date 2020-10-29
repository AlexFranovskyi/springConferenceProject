package ua.conference.springapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Report {
	
	@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "report_name")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conference_id")
	private Conference conference;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User speaker;
	
	private boolean approved;
	
	public Report(String name, Conference conference) {
		this.name = name;
		this.conference = conference;
	}
	
	public Report(String name, Conference conference, User speaker) {
		this.name = name;
		this.conference = conference;
		this.speaker = speaker;
	}
		
}
