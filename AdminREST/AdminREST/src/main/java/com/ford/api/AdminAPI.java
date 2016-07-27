package com.ford.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pivotal.spring.REST.User;

@RestController
public class AdminAPI {
	

	@RequestMapping("/admin")
	public @ResponseBody String getUser() {
		RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject("http://simplerest.cfapps.io/user/1", User.class);
		return user.getUserName();
	}
}
