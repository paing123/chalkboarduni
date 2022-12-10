package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Login;

public interface LoginService {
	
	void save(Login login);

	List<Login> findLogin(Login login);
	
	void delete(String id);

	void update(Login login);
}
