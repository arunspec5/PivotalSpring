package com.pivotal.spring.REST;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {
	
	@RequestMapping(path="/user/{userId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable("userId") int userId){
		return new User(userId,"Hello!!!");
	}
}
