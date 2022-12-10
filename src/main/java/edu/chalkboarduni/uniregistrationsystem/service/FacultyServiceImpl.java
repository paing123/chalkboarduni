package edu.chalkboarduni.uniregistrationsystem.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.FacultyDao;
import edu.chalkboarduni.uniregistrationsystem.dao.UserDao;
import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Faculty;
import edu.chalkboarduni.uniregistrationsystem.model.User;

@Service
public class FacultyServiceImpl implements FacultyService {
	
	@Autowired
	private FacultyDao facultyDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public void save(Faculty faculty) {
		facultyDao.save(faculty);
	}

	@Override
	public List<Faculty> findFaculty(Faculty faculty) {
		return facultyDao.findFaculty(faculty);
	}
	
	@Override
	public void delete(String id) {
		facultyDao.delete(id);
	}

	@Override
	public void update(Faculty faculty) {
		facultyDao.update(faculty);
	}

	@Override
	public List<UserDto> getFacultyUserList() {
		UserDto user = new UserDto();
		user.setUserType("ROLE_FACULTY");
		return userDao.findUser(user);
	}
	
}
