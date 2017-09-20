package test.com.fridayweekend.chat.unit;

import junit.framework.Assert;


import org.junit.Before;
import org.junit.Test;

import com.fridayweekend.chat.Greeting;
import com.fridayweekend.chat.HelloMessage;

public class GreetingUnitTest {
	
	private HelloMessage helloMessage;

	@Before 
	public void before(){
		helloMessage = new HelloMessage();
		helloMessage.setColor("the color");
		helloMessage.setContent("the content");
		helloMessage.setName("the name"); 
	}

    @Test
    public void testGreeting() {
    	
    	Greeting greeting = new Greeting(helloMessage);
    	
    	Assert.assertEquals(greeting.getColor(), helloMessage.getColor());
    	Assert.assertEquals(greeting.getContent(), helloMessage.getContent());
    	Assert.assertEquals(greeting.getName(), helloMessage.getName());
    }
}