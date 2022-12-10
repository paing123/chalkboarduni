package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Major;

public interface MajorService {
	
	void save(Major major);

	List<Major> findMajor(Major major);
	
	void delete(String id);

	void update(Major major);
}
