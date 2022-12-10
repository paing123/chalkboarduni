package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.mapper.LoginMapper;
import edu.chalkboarduni.uniregistrationsystem.mapper.UserMapper;
import edu.chalkboarduni.uniregistrationsystem.model.Login;
import edu.chalkboarduni.uniregistrationsystem.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private LoginMapper loginMapper;
	
	public void save(UserDto userDto) {
		
		User user = copyUserProperties(userDto);
		Login login = copyLoginProperties(userDto);
		
		userMapper.save(user);
		loginMapper.save(login);
	}
	
	public List<UserDto> findUser(UserDto userDto) {
		
		User user = copyUserProperties(userDto);
		List<User> users = userMapper.findUser(user);
		
		List<UserDto> userList = new ArrayList<UserDto>();
		for (int index = 0; index < users.size(); index++) {
			UserDto newUserDto = new UserDto();
			User existUser = users.get(index);
			Login existLogin = new Login();
			existLogin.setUserId(existUser.getUserId());
			existLogin = loginMapper.findLogin(existLogin).get(0);
			BeanUtils.copyProperties(existUser, newUserDto);
			BeanUtils.copyProperties(existLogin, newUserDto);
			userList.add(newUserDto);
		}
		
		return userList;
	}
	
	public void delete(String id) {
		userMapper.delete(id);
		loginMapper.delete(id);
	}
	
	public void update(UserDto userDto) {
		User user = copyUserProperties(userDto);
		Login login = copyLoginProperties(userDto);
				
		userMapper.update(user);
		loginMapper.update(login);
	}
	
	public void updateStudentOrFacultyUser(UserDto userDto) {
		User user = copyUserProperties(userDto);
		userMapper.update(user);
	}
	
	private User copyUserProperties(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return user;
	}
	
	private Login copyLoginProperties(UserDto userDto) {
		Login login = new Login();
		BeanUtils.copyProperties(userDto, login);
		return login;
	}
}
