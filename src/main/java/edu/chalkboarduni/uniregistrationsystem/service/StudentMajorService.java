package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.StudentMajor;

public interface StudentMajorService {
	
	void save(StudentMajor studentMajor);

	List<StudentMajor> findStudentMajor(StudentMajor studentMajor);
	
	void delete(StudentMajor studentMajor);

	void update(StudentMajor studentMajor);
}
