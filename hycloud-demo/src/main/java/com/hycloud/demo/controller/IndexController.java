package com.hycloud.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.hycloud.common.result.ListResult;
import com.hycloud.demo.service.UserService;

@RestController
public class IndexController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "Users";
	}
	
	@GetMapping("/users")
	public ListResult<?> getUsers(@RequestHeader HttpHeaders headers) {
		return userService.getUsers(1, 10);
	}
}
