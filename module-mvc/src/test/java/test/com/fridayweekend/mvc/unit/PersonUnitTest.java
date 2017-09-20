package test.com.fridayweekend.mvc.unit;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.fridayweekend.mvc.model.Person;

public class PersonUnitTest {
	
	private Person person;

	@Before 
	public void before(){
		person = new Person();
		person.setCountry("the country");
		person.setName("the name");
		person.setId(1);
	}

    @Test
    public void testPerson() {
    	
    	Assert.assertEquals(person.getCountry(), "the country");
    	Assert.assertEquals(person.getId(), 1);
    	Assert.assertEquals(person.getName(), "the name");
    }
}