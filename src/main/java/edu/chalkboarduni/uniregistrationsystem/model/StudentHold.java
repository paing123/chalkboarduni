package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentHold {
	
	@NotNull(message = "HoldId is required")
	private String holdId;
	
	@NotNull(message = "StudentId is required")
	private Integer studentId;
	
	private String studentFirstName;
	
	private String studentLastName;
	
	private String holdType;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<Hold> holdList;
	
	private List<Student> studentList;
	
	private List<StudentHold> studentHoldList;
}
