package test.com.fridayweekend.rest.integration;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.fridayweekend.rest.usermanagement.UserDao;
import com.fridayweekend.rest.usermanagement.UserDaoImpl;
import com.fridayweekend.rest.valueobjects.User;
import com.fridayweekend.rest.web.UserResource;

public class UserIntegrationTest {
	
	private UserDao userDao;
	private UserResource userResource;
	
	@Before
	public void before(){

		userDao = new UserDaoImpl();
		userResource = new UserResource();
		userResource.setUserDao(userDao);
	}

	@Test
	public void createUserTest() {
		
		
		User integration = new User();
        integration.setName("integration");
        integration.setEmail("initial@integration.com");
        integration.setRoles(Arrays.asList("admin", "master"));
        
        Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
        Assert.assertEquals(200, response.getStatus());
	}

	@Test
	public void updateUserTest() {
		
		
		createUserTest();
        
		
        User updated = new User();
        updated.setName("integration");
        updated.setEmail("updated@integration.com");
        updated.setRoles(Arrays.asList("admin", "master"));
        
        Response response = userResource.updateUser("initial@integration.com", updated.getName(), updated.getEmail(), updated.getRoles());
        Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void createUser2Test() {
		
		updateUserTest();
		
		User integration = new User();
        integration.setName("integration2");
        integration.setEmail("initial@integration.com2");
        integration.setRoles(Arrays.asList("admin", "master"));
        
        Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
        Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void updateUser2NegativeTest() {
		
		createUserTest();
		
		User integration = new User();
        integration.setName("integration2");
        integration.setEmail("initial@integration.com");
        integration.setRoles(Arrays.asList("admin", "master"));
        
        Response response = userResource.updateUser("initial@integration.com2", integration.getName(), integration.getEmail(), integration.getRoles());
        Assert.assertEquals(409, response.getStatus());
	}
}
