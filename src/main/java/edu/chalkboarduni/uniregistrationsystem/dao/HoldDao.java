package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Hold;

public interface HoldDao {
	 
	 void save(Hold hold);
	
	 List<Hold> findHold(Hold hold);
	 
	 void delete(String id);
	 
	 void update(Hold hold);
}
