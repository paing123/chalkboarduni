package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Student;
import edu.chalkboarduni.uniregistrationsystem.model.StudentHold;

public interface StudentHoldService {
	
	void save(StudentHold studentHold);

	List<StudentHold> findStudentHold(StudentHold studentHold);
	
	void delete(StudentHold studentHold);
}
