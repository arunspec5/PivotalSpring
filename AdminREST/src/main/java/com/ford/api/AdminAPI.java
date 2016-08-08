package com.ford.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pivotal.spring.REST.User;

@RestController
public class AdminAPI {
	
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/admin")
	public @ResponseBody String getUser() {
        User user = restTemplate.getForObject("http://SIMPLE-REST/user/1", User.class);
		return user.getUserName();
	}
}
