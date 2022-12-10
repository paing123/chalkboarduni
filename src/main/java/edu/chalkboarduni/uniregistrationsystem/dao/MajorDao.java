package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Major;

public interface MajorDao {
	 
	 void save(Major major);
	
	 List<Major> findMajor(Major major);
	 
	 void delete(String id);
	 
	 void update(Major major);
}
