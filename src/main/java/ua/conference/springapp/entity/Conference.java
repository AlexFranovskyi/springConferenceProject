package ua.conference.springapp.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Conference {
	@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;	
	
	private String name;
	private LocalDateTime localDateTime;
	private String location;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "conference_has_visitor", 
			inverseJoinColumns = {@JoinColumn(name = "user_id")})
	private Set<User> visitor;
	
	//stubs
	private int registerCounter = 0;
	private int reportCounter = 0;

	
	@OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Report> reports;
	
	public Conference() {
	}

	public Conference(String name, LocalDateTime localDateTime, String location) {
		this.name = name;
		this.localDateTime = localDateTime;
		this.location = location;
	}
	
	

}
