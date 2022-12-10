package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.User;

@Mapper
public interface UserMapper {
	public void save(@Param("user") User user);
	
	public List<User> findUser(@Param("user") User user);
	
	public void update(@Param("user") User user);
	
	public void delete(@Param("id") String id);
}
