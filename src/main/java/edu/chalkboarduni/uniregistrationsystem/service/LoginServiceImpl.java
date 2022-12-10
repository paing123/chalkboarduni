package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.LoginDao;
import edu.chalkboarduni.uniregistrationsystem.model.Login;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;

	@Override
	public void save(Login login) {
		loginDao.save(login);
	}

	@Override
	public List<Login> findLogin(Login login) {
		return loginDao.findLogin(login);
	}
	
	@Override
	public void delete(String id) {
		loginDao.delete(id);
	}

	@Override
	public void update(Login login) {
		loginDao.update(login);
	}
}
