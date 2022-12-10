package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.FacultyHistory;

public interface FacultyHistoryDao {
	 
	 void save(FacultyHistory facultyHistory);
	
	 List<FacultyHistory> findFacultyHistory(FacultyHistory facultyHistory);
	 
	 void delete(Integer facId,Integer courseSectionId);
	 
	 void update(FacultyHistory facultyHistory);
}
