package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.StudentMinor;

public interface StudentMinorDao {
	 
	 void save(StudentMinor studentMinor);
	
	 List<StudentMinor> findStudentMinor(StudentMinor studentMinor);
	 
	 void delete(StudentMinor studentMinor);
	 
	 void update(StudentMinor studentMinor);
}
