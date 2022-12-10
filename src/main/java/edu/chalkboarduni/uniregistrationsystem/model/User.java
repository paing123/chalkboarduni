package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Integer userId;
	private String firstName;
	private String lastName;
	private String dob;
	private String streetNum;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private String phoneNum;
	private String userType;
	private List<User> userList;
}
