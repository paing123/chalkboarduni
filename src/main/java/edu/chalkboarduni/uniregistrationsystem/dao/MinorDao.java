package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Minor;

public interface MinorDao {
	 
	 void save(Minor minor);
	
	 List<Minor> findMinor(Minor minor);
	 
	 void delete(String id);
	 
	 void update(Minor minor);
}
