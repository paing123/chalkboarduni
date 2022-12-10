package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.StudentHistory;

public interface StudentHistoryDao {
	 
	 void save(StudentHistory studentHistory);
	
	 List<StudentHistory> findStudentHistory(StudentHistory studentHistory);
	 
	 void delete(Integer studentId, Integer courseSectionId);
	 
	 void update(StudentHistory studentHistory);
}
