package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.StudentHistory;

public interface StudentHistoryService {
	
	void save(StudentHistory studentHistory);

	List<StudentHistory> findStudentHistory(StudentHistory studentHistory);
	
	void delete(Integer studentId, Integer courseSectionId);

	void update(StudentHistory studentHistory);
	
	List<UserDto> getStudentHistoryUserList();
}
