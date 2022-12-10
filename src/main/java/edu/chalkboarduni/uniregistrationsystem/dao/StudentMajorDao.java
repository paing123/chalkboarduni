package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.StudentMajor;

public interface StudentMajorDao {
	 
	 void save(StudentMajor studentMajor);
	
	 List<StudentMajor> findStudentMajor(StudentMajor studentMajor);
	 
	 void delete(StudentMajor studentMajor);
	 
	 void update(StudentMajor studentMajor);
}
