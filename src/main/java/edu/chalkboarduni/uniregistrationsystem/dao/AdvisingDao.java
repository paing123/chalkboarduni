package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Advising;

public interface AdvisingDao {
	 
	 void save(Advising advising);
	
	 List<Advising> findAdvising(Advising advising);
	 
	 void delete(String id);
	 
	 void update(Advising advising);
}
