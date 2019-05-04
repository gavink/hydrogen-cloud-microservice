package com.hycloud.auth.sso.controller;

import com.hycloud.auth.sso.service.UserService;
import com.hycloud.auth.sso.storage.entity.UserInfo;
import com.hycloud.common.result.CommonResult;
import com.hycloud.common.result.ItemResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/personal")
public class PersonalCenterController {

	@Autowired
	UserService userService;

	@GetMapping("/profile")
	public ItemResult<?> getMyInfo(@AuthenticationPrincipal UserDetails user) {
		String username = user.getUsername();
		return userService.getUserInfo(username);
	}
	
	@PutMapping("/profile")
	public CommonResult saveMyInfo(@RequestBody UserInfo userInfo,
			@AuthenticationPrincipal UserDetails user) {
		userInfo.setUsername(user.getUsername());
		return userService.saveUserInfo(userInfo);
	}
	
	@PutMapping("/password")
	public CommonResult changePassword(@RequestBody Map<String, String> params, 
			@AuthenticationPrincipal UserDetails user) {
		String username = user.getUsername();
		return userService.changePassword(username, params.get("oldPassword"), params.get("newPassword"));
	}

}
