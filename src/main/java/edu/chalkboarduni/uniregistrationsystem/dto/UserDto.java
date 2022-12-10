package edu.chalkboarduni.uniregistrationsystem.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	@NotNull(message = "User Id is required")
	@Min(value = 1, message = "UserId shouldn't be less than 1")
	@Max(value = 999999999, message = "UserId shouldn't be greater than 999999999")
	private Integer userId;
	
	@NotNull(message = "First Name is required")
	private String firstName;
	
	@NotNull(message = "Last Name is required")
	private String lastName;
	
	@NotNull(message = "DOB is required")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private String dob;
	
	@NotNull(message = "Street Num is required")
	private String streetNum;
	
	@NotNull(message = "Street is required")
	private String street;
	
	@NotNull(message = "City is required")
	private String city;
	
	@NotNull(message = "State is required")
	private String state;
	
	@NotNull(message = "Zip Code is required")
	private String zipCode;
	
	@NotNull(message = "Country is required")
	private String country;
	
	@NotNull(message = "Phone Num is required")
	private String phoneNum;
	
	@NotNull(message = "User Type is required")
	private String userType;
	
	@NotNull(message = "Email is required")
	@Email(message = "Your email is not valid format!")
	private String email;
	
	@NotNull(message = "Password is required")
	private String password;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<UserDto> userList;
}
