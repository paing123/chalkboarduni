package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Faculty;

public interface FacultyService {
	
	void save(Faculty faculty);

	List<Faculty> findFaculty(Faculty faculty);
	
	void delete(String id);

	void update(Faculty faculty);
	
	List<UserDto> getFacultyUserList();
}
