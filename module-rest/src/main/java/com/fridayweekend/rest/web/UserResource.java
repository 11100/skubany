package com.fridayweekend.rest.web;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fridayweekend.rest.usermanagement.UserDao;
import com.fridayweekend.rest.valueobjects.User;

@Component
@Path("/users")
public class UserResource{

	private static final String REST_BAD_REQ = "email and at least one role are required";
	private static final String REST_CONFLICT = "email already in use";
	
	@Autowired
    private UserDao userDao;

    public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@GET
    @Path("add/")
    public Response addUser(@QueryParam("name") String name,
                            @QueryParam("email") String email,
                            @QueryParam("role") List<String> roles) {

		if(email == null || roles == null || !checkRoles(roles)){
			return Response.status(Response.Status.BAD_REQUEST).entity(REST_BAD_REQ).build();
		}
		
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRoles(roles);

        synchronized(userDao){
        	if(userDao.findUser(email) == null){
        		userDao.addUser(user);
        		return Response.ok().entity(user).build();
        	} else {
        		return Response.status(Response.Status.CONFLICT).entity(REST_CONFLICT).build();
        	}
        }
    }

    @GET
    @Path("update/")
    public Response updateUser(@QueryParam("id") String id,
    		                   @QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles) {

    	if(email == null || id == null || email.isEmpty() || id.isEmpty() || !checkRoles(roles)){
			return Response.status(Response.Status.BAD_REQUEST).entity(REST_BAD_REQ).build();
		}
    	
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRoles(roles);

        synchronized(userDao){
        	User sameEmail = userDao.findUser(email);
        	if(sameEmail == null || id.equals(email)){
        		userDao.updateUser(id, user);
        		return Response.ok().entity(user).build();
        	} else {
        		return Response.status(Response.Status.CONFLICT).entity(REST_CONFLICT).build();
        	}
        }
    }

    @GET
    @Path("delete/")
    public Response deleteUser(@QueryParam("email") String email) {
    	
        User user = userDao.deletedUser(email);
        return Response.ok().entity(user).build();
    }

    @GET
    @Path("find/")
    public Response getUsers() {
    	
    	List<User> users = userDao.getUsers();
    	if (users == null) {
    		users = new ArrayList<User>();
    	}

        GenericEntity<List<User>> usersEntity = new GenericEntity<List<User>>(users) {};
        return Response.ok().entity(usersEntity).build();
    }

    @GET
    @Path("search/")
    public Response findUser(@QueryParam("email") String email) {

        User user = userDao.findUser(email);
        return Response.ok().entity(user).build();
    }

	private boolean checkRoles(List<String> roles){
		
		if(roles == null || roles.isEmpty()){
			return false;
		}
		for(String role : roles){
			if(role != null && !role.isEmpty()){
				return true;
			}
		}
		return false;
	}
}
