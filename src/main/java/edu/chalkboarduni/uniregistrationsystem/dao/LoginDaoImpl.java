package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.LoginMapper;
import edu.chalkboarduni.uniregistrationsystem.model.Login;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	private LoginMapper loginMapper;
	
	public void save(Login login) {
		loginMapper.save(login);
	}
	
	public List<Login> findLogin(Login login) {
		List<Login> logins = loginMapper.findLogin(login);
		return logins;
	}
	
	public void delete(String id) {
		loginMapper.delete(id);
	}
	
	public void update(Login login) {
		loginMapper.update(login);
	}
}
