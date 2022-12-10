package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.BuildingMapper;
import edu.chalkboarduni.uniregistrationsystem.model.Building;

@Repository
public class BuildingDaoImpl implements BuildingDao {
	
	@Autowired
	private BuildingMapper buildingMapper;
	
	public void save(Building building) {
		buildingMapper.save(building);
	}
	
	public List<Building> findBuilding(Building building) {
		List<Building> buildings = buildingMapper.findBuilding(building);
		return buildings;
	}
	
	public void delete(String id) {
		buildingMapper.delete(id);
	}
	
	public void update(Building building) {
		buildingMapper.update(building);
	}
}
