package com.pivotal.spring.REST;

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
	@RequestMapping(path="/user/{userId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public AppUser getUser(@PathVariable("userId") int userId){
		AppUser user = userRepository.findOne(userId);
		return user;
	}
	
	@RequestMapping(path="/user", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AppUser addUser(@RequestBody AppUser user){
		System.out.println(user.getUserId()+"     "+user.getUserName());
		user = userRepository.save(user);
		return user;
	}
}
