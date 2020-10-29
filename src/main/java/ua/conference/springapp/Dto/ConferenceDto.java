package ua.conference.springapp.Dto;

import java.time.LocalDateTime;

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
public class ConferenceDto {
	
	private long id;
	private String conferenceName;
	
	private LocalDateTime localDateTime;
	private String location;
	
	private int visitorCounter;
	private int reportCounter;
	
	private int arrivedVisitorsAmount;

}
