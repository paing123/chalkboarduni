package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
	private Integer userId;
	private String email;
	private String password;
	private List<Login> loginList;
}
