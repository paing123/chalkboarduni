package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	void save(UserDto user);

	List<UserDto> findUser(UserDto user);
	
	UserDto findUserByEmail(String email);
	
	void delete(String id);

	void update(UserDto user);
	
	void updateStudentOrFacultyUser(UserDto user);
}
