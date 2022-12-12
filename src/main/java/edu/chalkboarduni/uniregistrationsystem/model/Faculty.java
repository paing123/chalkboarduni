package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.cache.annotation.EnableCaching;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {
	
	@NotNull(message = "FacultyId is required")
	@Min(value = 1, message = "FacultyId shouldn't be less than 1")
	@Max(value = 999999999, message = "FacultyId shouldn't be greater than 999999999")
	private Integer facultyId;
	
	@NotNull(message = "Faculty Type is required")
	private String facultyType;
	
	private String facultyFirstName;
	
	private String facultyLastName;
	
	@NotNull(message = "Rank is required")
	private String rank;
	
	@NotNull(message = "OfficeId is required")
	private String officeId;
	
	private String roomType;
	
	@NotNull(message = "Office Hours is required")
	private String officeHours;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<UserDto> facultyUserList;
	
	private List<Room> roomList;
	
	private List<Faculty> facultyList;
}
