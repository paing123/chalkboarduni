package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Enrollment;

public interface EnrollmentService {
	
	void save(Enrollment enrollment);

	List<Enrollment> findEnrollment(Enrollment enrollment);
	
	void delete(Enrollment enrollment);

	void update(Enrollment enrollment);
}
