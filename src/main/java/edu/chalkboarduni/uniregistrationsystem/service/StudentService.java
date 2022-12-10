package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Student;

public interface StudentService {
	
	void save(Student student);

	List<Student> findStudent(Student student);
	
	void delete(Integer id);

	void update(Student student);
	
	List<UserDto> getStudentUserList();
}
