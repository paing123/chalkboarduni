package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advising {
	
	private Integer appointmentId;
	
	@NotNull(message = "Faculty is required")
	private Integer facultyId;
	
	private String facultyFirstName;
	
	private String facultyLastName;
	
	@NotNull(message = "Student is required")
	private Integer studentId;
	
	private String studentFirstName;
	
	private String studentLastName;
	
	@NotNull(message = "Date is required")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private String advisingDate;
	
	private String successMessage;
	
	private List<Faculty> facultyList;
	
	private List<Student> studentList;
	
	private List<Advising> advisingList;
}
