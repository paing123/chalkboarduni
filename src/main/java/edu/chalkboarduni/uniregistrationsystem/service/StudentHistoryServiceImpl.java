package edu.chalkboarduni.uniregistrationsystem.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.StudentHistoryDao;
import edu.chalkboarduni.uniregistrationsystem.dao.UserDao;
import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.StudentHistory;

@Service
public class StudentHistoryServiceImpl implements StudentHistoryService {
	
	@Autowired
	private StudentHistoryDao studentHistoryDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public void save(StudentHistory studentHistory) {
		studentHistoryDao.save(studentHistory);
	}

	@Override
	public List<StudentHistory> findStudentHistory(StudentHistory studentHistory) {
		return studentHistoryDao.findStudentHistory(studentHistory);
	}
	
	@Override
	public void delete(Integer studentId, Integer courseSectionId) {
		studentHistoryDao.delete(studentId,courseSectionId);
	}

	@Override
	public void update(StudentHistory studentHistory) {
		studentHistoryDao.update(studentHistory);
	}
	
	@Override
	public List<UserDto> getStudentHistoryUserList() {
		UserDto user = new UserDto();
		user.setUserType("STUDENT");
		return userDao.findUser(user);
	}
}
