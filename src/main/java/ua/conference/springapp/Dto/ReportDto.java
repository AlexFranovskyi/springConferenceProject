package ua.conference.springapp.Dto;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.conference.springapp.entity.Conference;
import ua.conference.springapp.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
	private long id;
	private String name;
	private String speakerName;
	private String conferenceName;
	private boolean approved;
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private ReportDto newReportDto;
		
		public Builder() {
			newReportDto = new ReportDto();
		}
		
		public Builder id(long id) {
			newReportDto.id = id;
			return this;
		}
		
		public Builder name(String name) {
			newReportDto.name = name;
			return this;
		}
		
		public Builder speaker(User speaker) {
			newReportDto.speakerName = Optional.ofNullable(speaker)
					.map(user -> user.getUsername()).orElse("");
			return this;
		}
		
		public Builder conferenceName(Conference conference) {
			newReportDto.conferenceName = Optional.ofNullable(conference)
					.map(conf -> conf.getName()).orElse("");
			return this;			
		}
		
		public Builder approved(boolean approved) {
			newReportDto.approved = approved;
			return this;
		}
		
		public ReportDto build() {
			return newReportDto;
		}
		
		
	}
}
