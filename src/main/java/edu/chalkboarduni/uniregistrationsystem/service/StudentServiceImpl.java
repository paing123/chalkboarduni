package edu.chalkboarduni.uniregistrationsystem.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.StudentDao;
import edu.chalkboarduni.uniregistrationsystem.dao.UserDao;
import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public void save(Student student) {
		studentDao.save(student);
	}

	@Override
	public List<Student> findStudent(Student student) {
		return studentDao.findStudent(student);
	}
	
	@Override
	public void delete(Integer id) {
		studentDao.delete(id);
	}

	@Override
	public void update(Student student) {
		studentDao.update(student);
	}
	
	@Override
	public List<UserDto> getStudentUserList() {
		UserDto user = new UserDto();
		user.setUserType("ROLE_STUDENT");
		return userDao.findUser(user);
	}
}
