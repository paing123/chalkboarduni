package edu.chalkboarduni.uniregistrationsystem.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.FacultyHistoryDao;
import edu.chalkboarduni.uniregistrationsystem.dao.UserDao;
import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.FacultyHistory;

@Service
public class FacultyHistoryServiceImpl implements FacultyHistoryService {
	
	@Autowired
	private FacultyHistoryDao facultyHistoryDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public void save(FacultyHistory facultyHistory) {
		facultyHistoryDao.save(facultyHistory);
	}

	@Override
	public List<FacultyHistory> findFacultyHistory(FacultyHistory facultyHistory) {
		return facultyHistoryDao.findFacultyHistory(facultyHistory);
	}
	
	@Override
	public void delete(Integer facId,Integer courseSectionId) {
		facultyHistoryDao.delete(facId,courseSectionId);
	}

	@Override
	public void update(FacultyHistory facultyHistory) {
		facultyHistoryDao.update(facultyHistory);
	}
	
	@Override
	public List<UserDto> getFacultyHistoryUserList() {
		UserDto user = new UserDto();
		user.setUserType("FACULTY");
		return userDao.findUser(user);
	}
}
