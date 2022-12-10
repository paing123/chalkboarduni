package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.BuildingDao;
import edu.chalkboarduni.uniregistrationsystem.model.Building;

@Service
public class BuildingServiceImpl implements BuildingService {
	
	@Autowired
	private BuildingDao buildingDao;

	@Override
	public void save(Building building) {
		buildingDao.save(building);
	}

	@Override
	public List<Building> findBuilding(Building building) {
		return buildingDao.findBuilding(building);
	}
	
	@Override
	public void delete(String id) {
		buildingDao.delete(id);
	}

	@Override
	public void update(Building building) {
		buildingDao.update(building);
	}
}
