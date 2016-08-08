package com.ford.api;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.JsonMessageConverter;

import com.pivotal.spring.REST.User;


public class Receiver implements MessageListener{
	@Override
	public void onMessage(Message message) {
		JsonMessageConverter jmc = new JsonMessageConverter();
    	User user = (User)jmc.fromMessage(message);
		System.out.println("Received <" + user.getUserId() + "    "+ user.getUserName() + ">");
		
	}
}
