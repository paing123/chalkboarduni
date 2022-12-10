package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Student;

public interface StudentDao {
	 
	 void save(Student student);
	
	 List<Student> findStudent(Student student);
	 
	 void delete(Integer id);
	 
	 void update(Student student);
}
