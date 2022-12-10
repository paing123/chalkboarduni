package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Building;

public interface BuildingDao {
	 
	 void save(Building building);
	
	 List<Building> findBuilding(Building building);
	 
	 void delete(String id);
	 
	 void update(Building building);
}
