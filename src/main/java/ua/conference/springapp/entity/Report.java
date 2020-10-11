package ua.conference.springapp.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Report {
	
	@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conference_id")
	private Conference conference;
	
	public Report() {
	}
	
	public Report(String name) {
		this.name = name;
	}
	
}
