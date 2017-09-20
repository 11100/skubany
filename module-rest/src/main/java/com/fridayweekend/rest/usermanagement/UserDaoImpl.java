package com.fridayweekend.rest.usermanagement;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fridayweekend.rest.valueobjects.User;

@Repository
public class UserDaoImpl implements UserDao {

	final static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private List<User> users = new ArrayList<User>();

	public void addUser(User user) {
		users.add(user);
	}

	public List<User> getUsers() {
		return users;
	}

	public User deletedUser(String email) {
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				users.remove(user);
				return user;
			}
		}
		return null;
	}

	public void updateUser(String email, User newUserData) {

		for (User user : users) {
			if (user.getEmail().equals(email)) {
				user.setName(newUserData.getName());
				user.setEmail(newUserData.getEmail());
				user.setRoles(newUserData.getRoles());
				break;
			}
		}
	}

	public User findUser(String email) {

		for (User user : users) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}
}
