package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentMajor {
	
	@NotNull(message = "Student is required")
	private Integer studentId;
	
	private String studentType;
	
	private String studentFirstName;
	
	private String studentLastName;
	
	@NotNull(message = "Major is required")
	private String majorId;
	
	private String majorName;
	
	private String majorDegree;
	
	@NotNull(message = "Date is required")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private String date;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<Student> studentList;
	
	private List<Major> majorList;
	
	private List<StudentMajor> studentMajorList;
}
