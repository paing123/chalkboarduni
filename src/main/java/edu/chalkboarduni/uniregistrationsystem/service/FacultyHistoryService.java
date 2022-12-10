package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.FacultyHistory;

public interface FacultyHistoryService {
	
	void save(FacultyHistory facultyHistory);

	List<FacultyHistory> findFacultyHistory(FacultyHistory facultyHistory);
	
	void delete(Integer facId,Integer courseSectionId);

	void update(FacultyHistory facultyHistory);
	
	List<UserDto> getFacultyHistoryUserList();
}
