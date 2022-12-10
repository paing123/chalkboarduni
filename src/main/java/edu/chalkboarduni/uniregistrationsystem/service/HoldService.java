package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Hold;

public interface HoldService {
	
	void save(Hold hold);

	List<Hold> findHold(Hold hold);
	
	void delete(String id);

	void update(Hold hold);
}
