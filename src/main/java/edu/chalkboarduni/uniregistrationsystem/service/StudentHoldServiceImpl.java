package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.StudentHoldDao;
import edu.chalkboarduni.uniregistrationsystem.dao.UserDao;
import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Student;
import edu.chalkboarduni.uniregistrationsystem.model.StudentHold;

@Service
public class StudentHoldServiceImpl implements StudentHoldService {
	
	@Autowired
	private StudentHoldDao studentHoldDao;

	@Override
	public void save(StudentHold studentHold) {
		studentHoldDao.save(studentHold);
	}

	@Override
	public List<StudentHold> findStudentHold(StudentHold studentHold) {
		return studentHoldDao.findStudentHold(studentHold);
	}
	
	@Override
	public void delete(StudentHold studentHold) {
		studentHoldDao.delete(studentHold);
	}
}
