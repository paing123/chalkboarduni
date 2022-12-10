package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.StudentHold;

public interface StudentHoldDao {
	 
	 void save(StudentHold studentHold);
	
	 List<StudentHold> findStudentHold(StudentHold studentHold);
	 
	 void delete(StudentHold studentHold);
}
