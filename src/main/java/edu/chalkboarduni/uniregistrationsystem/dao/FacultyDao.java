package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Faculty;

public interface FacultyDao {
	 
	 void save(Faculty faculty);
	
	 List<Faculty> findFaculty(Faculty faculty);
	 
	 void delete(String id);
	 
	 void update(Faculty faculty);
}
