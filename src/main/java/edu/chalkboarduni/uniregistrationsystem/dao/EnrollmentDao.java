package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Enrollment;

public interface EnrollmentDao {
	 
	 void save(Enrollment enrollment);
	
	 List<Enrollment> findEnrollment(Enrollment enrollment);
	 
	 void delete(Enrollment enrollment);
	 
	 void update(Enrollment enrollment);
}
