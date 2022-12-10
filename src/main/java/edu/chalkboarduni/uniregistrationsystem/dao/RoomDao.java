package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Room;

public interface RoomDao {
	 
	 void save(Room room);
	
	 List<Room> findRoom(Room room);
	 
	 void delete(String id);
	 
	 void update(Room room);
}
