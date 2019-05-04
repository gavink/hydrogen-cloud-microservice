package com.hycloud.auth.sso.controller;

import com.hycloud.auth.sso.service.UserService;
import com.hycloud.auth.sso.storage.entity.User;
import com.hycloud.auth.sso.storage.entity.UserInfo;
import com.hycloud.common.result.CommonResult;
import com.hycloud.common.result.ItemResult;
import com.hycloud.common.result.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public ListResult<?> getUsers(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
		return userService.getUsers(pageNum, pageSize);
	}
	
	@GetMapping("/users/{username}")
	public ItemResult<?> getUserInfo(@PathVariable("username") String username){
		return userService.getUserInfo(username);
	}
	
	@PostMapping("/users")
	public CommonResult addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@PutMapping("/users")
	public CommonResult saveUserInfo(@RequestBody UserInfo userInfo) {
		return userService.saveUserInfo(userInfo);
	}
	
	@DeleteMapping("/users/{username}")
	public CommonResult deleteUser(@PathVariable("username") String username) {
		return userService.deleteUser(username);
	}

}
