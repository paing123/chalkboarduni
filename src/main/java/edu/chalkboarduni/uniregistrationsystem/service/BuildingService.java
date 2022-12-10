package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Building;

public interface BuildingService {
	
	void save(Building building);

	List<Building> findBuilding(Building building);
	
	void delete(String id);

	void update(Building building);
}
