package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;

public interface UserDao {
	 
	 void save(UserDto user);
	
	 List<UserDto> findUser(UserDto user);
	 
	 void delete(String id);
	 
	 void update(UserDto user);
	 
	 void updateStudentOrFacultyUser(UserDto user);
}
