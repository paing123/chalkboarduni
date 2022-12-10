package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	@NotNull(message = "Student Id is required")
	@Min(value = 1, message = "Student Id shouldn't be less than 1")
	@Max(value = 999999999, message = "Student Id shouldn't be greater than 999999999")
	private Integer studentId;
	
	@NotNull(message = "Student Type is required")
	private String studentType;
	
	private String studentFirstName;
	
	private String studentLastName;
	
	@NotNull(message = "Student Program is required")
	private String studentProgram;
	
	@NotNull(message = "Academic Year is required")
	private String academicYear;
	
	@NotNull(message = "Credits Completed is required")
	private String creditsCompleted;
	
	@NotNull(message = "Student Status is required")
	private String studentStatus;
	
	@NotNull(message = "Department Id is required")
	private String departmentId;
	
	private String departmentName;
	
	@NotNull(message = "Faculty Id is required")
	private Integer facultyId;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<Department> departmentList;
	
	private List<UserDto> studentUserList;
	
	private List<Faculty> facultyList;
	
	private List<Student> studentList;
}
