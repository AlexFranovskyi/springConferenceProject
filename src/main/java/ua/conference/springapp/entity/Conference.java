package ua.conference.springapp.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Formula;

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
public class Conference {
	@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;	
	
	@Column(name="conference_name")
	private String name;
	
	private LocalDateTime localDateTime;
	private String location;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "conference_has_visitor", 
			inverseJoinColumns = {@JoinColumn(name = "user_id")})
	private Set<User> visitors;
		
	@OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Report> reports;
	
	@Formula("(select count(chv.user_id) from conference_has_visitor chv where chv.conference_id = id)")
	private int visitorCounter;
	
	@Formula("(select count(r.id) from report r where r.conference_id = id and r.approved = 1)")
	private int reportCounter;
	
	private int arrivedVisitorsAmount;
	
}
