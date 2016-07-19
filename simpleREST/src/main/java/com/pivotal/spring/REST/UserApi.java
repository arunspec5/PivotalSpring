package com.pivotal.spring.REST;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	private Receiver receiver;
	
	@RequestMapping(path="/user/{userId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public AppUser getUser(@PathVariable("userId") int userId) throws InterruptedException{
		AppUser user = userRepository.findOne(userId);
		rabbitTemplate.convertAndSend("spring-boot", "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		return user;
	}
	
	@RequestMapping(path="/user", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AppUser addUser(@RequestBody AppUser user){
		System.out.println(user.getUserId()+"     "+user.getUserName());
		user = userRepository.save(user);
		return user;
	}
}
