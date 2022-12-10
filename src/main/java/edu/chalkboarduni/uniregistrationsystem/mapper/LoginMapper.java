package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.Login;

@Mapper
public interface LoginMapper {
	public void save(@Param("login") Login login);
	
	public List<Login> findLogin(@Param("login") Login login);
	
	public void update(@Param("login") Login login);
	
	public void delete(@Param("id") String id);
}
