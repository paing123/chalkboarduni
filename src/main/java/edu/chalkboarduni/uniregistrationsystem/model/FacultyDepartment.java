package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyDepartment {
	
	@NotNull(message = "Faculty is required")
	private Integer facultyId;
	
	private String facultyFirstName;
	
	private String facultyLastName;
	
	@NotNull(message = "Department is required")
	private String departmentId;
	
	private String departmentName;
	
	@NotNull(message = "Percent Time is required")
	@Min(value = 1, message = "Percent Time shouldn't be less than 1")
	@Max(value = 999999999, message = "Percent Time shouldn't be greater than 999999999")
	private Integer percentTime;
	
	@NotNull(message = "Date of appointment is required")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private String dateOfAppointment;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<Faculty> facultyList;
	
	private List<Department> departmentList;
	
	private List<FacultyDepartment> facultyDepartmentList;
}
