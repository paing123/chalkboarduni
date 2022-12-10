package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlot {
	
	private Integer timeSlotId;
	
	@NotNull(message = "Day is required")
	private String day;
	
	@NotNull(message = "Period is required")
	private String period;
	
	private String successMessage;
	
	private List<TimeSlot> timeSlotList;
}
