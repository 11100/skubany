package test.com.fridayweekend.rest.unit;

import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.fridayweekend.rest.usermanagement.UserDao;
import com.fridayweekend.rest.usermanagement.UserDaoImpl;
import com.fridayweekend.rest.valueobjects.User;
import com.fridayweekend.rest.web.UserResource;

public class UserResourceUnitTest {

	private UserDao userDao;
	
	@Before
	public void before(){
		userDao = new UserDaoImpl();
	}
	
    UserResource userResource;
    
    @Test
    public void getUsersTest() {

        userResource = new UserResource();
        userResource.setUserDao(userDao);

        User user = new User();
        user.setName("fake user");
        user.setEmail("fake@user.com");
        userDao.addUser(user);

        Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
    }
}
