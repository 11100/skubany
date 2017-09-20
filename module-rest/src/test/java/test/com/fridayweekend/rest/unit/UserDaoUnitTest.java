package test.com.fridayweekend.rest.unit;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.fridayweekend.rest.usermanagement.UserDao;
import com.fridayweekend.rest.usermanagement.UserDaoImpl;
import com.fridayweekend.rest.valueobjects.User;

public class UserDaoUnitTest {

	private UserDao userDao;
	
	@Before 
	public void before(){
		userDao = new UserDaoImpl();
	}

	private void printUserInfo(String action, User user){
		System.out.println(action + ": " + user.toString());
	}
	
    @Test
    public void saveUserTest() {
        
        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));

        printUserInfo("adding new user", user);
        userDao.addUser(user);
        
        List<User> userList = userDao.getUsers();
        Assert.assertEquals(userList.size(), 1);
    }

    @Test
    public void updateUserTest() {
        
    	saveUserTest();
    	
        User user = new User();
        user.setName("Fake Fake");
        user.setEmail("fake1@email.com");
        user.setRoles(Arrays.asList("admin", "master"));

        userDao.updateUser("fake@email.com", user);
        System.out.println("Updating user name to = " + user.getName());
        User updatedUser = userDao.findUser("fake1@email.com");
        Assert.assertEquals(updatedUser.getName(), "Fake Fake");
        
        List<User> userList = userDao.getUsers();
        Assert.assertEquals(userList.size(), 1);
    }
    
    @Test
    public void deleteUserTest() {
    	
    	updateUserTest();
        
        User user = new User();
        user.setName("Fake Fake");
        user.setEmail("fake1@email.com");
        user.setRoles(Arrays.asList("admin", "master"));

        User deleteUser = userDao.deletedUser(user.getEmail());
        Assert.assertEquals(deleteUser.getName(), "Fake Fake");
        System.out.println("deleted user = " + deleteUser.getEmail());
        
        List<User> userList = userDao.getUsers();
        Assert.assertEquals(userList.size(), 0);
    }
}