package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Login;

public interface LoginDao {
	 
	 void save(Login login);
	
	 List<Login> findLogin(Login login);
	 
	 void delete(String id);
	 
	 void update(Login login);
}
