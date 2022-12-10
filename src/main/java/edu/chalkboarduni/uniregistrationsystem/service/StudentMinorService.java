package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.StudentMinor;

public interface StudentMinorService {
	
	void save(StudentMinor studentMinor);

	List<StudentMinor> findStudentMinor(StudentMinor studentMinor);
	
	void delete(StudentMinor studentMinor);

	void update(StudentMinor studentMinor);
}
